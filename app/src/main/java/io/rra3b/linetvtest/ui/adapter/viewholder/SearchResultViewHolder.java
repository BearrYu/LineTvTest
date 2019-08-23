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
import io.rra3b.linetvtest.data.local.dto.DramaSearchResult;

/**
 * ViewHolder for @{@link io.rra3b.linetvtest.R.layout#item_search_result}
 */
public class SearchResultViewHolder extends BaseViewHolder {

  @BindView(R.id.i_drama_iv_thumb)
  protected ImageView ivThumb;

  @BindView(R.id.i_drama_tv_name)
  protected TextView tvName;


  public SearchResultViewHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }

  public void setDrama(DramaSearchResult drama) {
    Glide.with(itemView)
        .asBitmap()
        .load(drama.getThumbUrl())
        .apply(new RequestOptions()
            .centerCrop()
            .override(ivThumb.getWidth(), ivThumb.getHeight()))
        .into(ivThumb);

    tvName.setText(drama.getName());
  }
}
