package io.rra3b.linetvtest.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.data.local.dto.DramaOverview;
import io.rra3b.linetvtest.util.DateUtils;
import java.util.Locale;

/**
 * ViewHolder for @{@link io.rra3b.linetvtest.R.layout#item_drama_overview}
 */
public class DramaOverviewViewHolder extends BaseViewHolder {

  @BindView(R.id.i_drama_iv_thumb)
  protected ImageView ivThumb;

  @BindView(R.id.i_drama_tv_rating)
  protected TextView tvRating;

  @BindView(R.id.i_drama_tv_name)
  protected TextView tvName;

  @BindView(R.id.i_drama_tv_date_created)
  protected TextView tvDateCreated;

  public DramaOverviewViewHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }

  public void setDrama(DramaOverview drama) {
    Glide.with(itemView)
        .asBitmap()
        .load(drama.getThumbUrl())
        .apply(new RequestOptions()
            .centerCrop()
            .override(ivThumb.getWidth(), ivThumb.getHeight()))
        .into(ivThumb);

    tvRating.setText(String.format(Locale.TAIWAN, "%.1f", drama.getRating()));

    tvName.setText(drama.getName());
    tvDateCreated.setText(DateUtils.toDateString(drama.getDateCreated()));
  }
}
