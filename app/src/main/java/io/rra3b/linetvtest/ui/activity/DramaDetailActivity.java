package io.rra3b.linetvtest.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.data.local.entity.DramaEntity;
import io.rra3b.linetvtest.ui.base.BaseActivity;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;
import io.rra3b.linetvtest.util.DateUtils;
import java.util.Locale;

public class DramaDetailActivity extends BaseActivity {

  public static final String EXT_DRAMA_ID = "EXTRA_DRAMA_ID";

  @BindView(R.id.a_drama_detail_iv_thumb)
  protected ImageView ivThumb;

  @BindView(R.id.a_drama_detail_tv_title_expanded)
  protected TextView tvTitleExpanded;

  @BindView(R.id.a_drama_detail_tv_date_and_views)
  protected TextView tvDateAndViews;

  @BindView(R.id.a_drama_detail_tv_rating)
  protected TextView tvRating;

  @BindView(R.id.a_drama_detail_appbar)
  protected AppBarLayout appBarLayout;

  @BindView(R.id.a_drama_detail_collapsing_toolbar_layout)
  protected CollapsingToolbarLayout collapsingToolbarLayout;

  @BindView(R.id.toolbar)
  protected Toolbar toolbar;

  private int mDramaId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drama_detail);

    mDramaId = getIntent().getIntExtra(EXT_DRAMA_ID, -1);
    if (mDramaId == -1) {
      finish();
      return;
    }

    appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
      int scrollRange = appBarLayout.getTotalScrollRange();

      boolean ctlTitleEnabled = verticalOffset * -1 == scrollRange;
      if (ctlTitleEnabled != collapsingToolbarLayout.isTitleEnabled()) {
        collapsingToolbarLayout.setTitleEnabled(ctlTitleEnabled);
      }

      boolean isNeedHideTitleExpanded = verticalOffset + scrollRange < scrollRange / 10;
      if ((tvTitleExpanded.getVisibility() == View.GONE) != isNeedHideTitleExpanded) {
        tvTitleExpanded.setVisibility(isNeedHideTitleExpanded ? View.GONE : View.VISIBLE);
      }
    });

    DramasViewModel viewModel = InjectorUtils.provideDramasViewModel(this);

    viewModel.getDrama(6)
        .doOnSuccess(this::setupViews)
        .doOnComplete(this::finish)
        .subscribe();
  }

  private void setupViews(DramaEntity drama) {
    Glide.with(this)
        .load(drama.getThumbUrl())
        .into(ivThumb);

    collapsingToolbarLayout.setTitle(drama.getName());
    tvTitleExpanded.setText(drama.getName());
    tvRating.setText(String.format(Locale.TAIWAN, "%.1f", drama.getRating()));

    tvDateAndViews.setText(getString(R.string.drama_date_and_views,
        DateUtils.toDateString(drama.getDateCreated()),
        drama.getTotalViews()));
  }
}
