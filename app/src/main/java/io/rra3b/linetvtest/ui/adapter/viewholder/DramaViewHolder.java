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
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.util.DateUtils;

public class DramaViewHolder extends BaseViewHolder {

  @BindView(R.id.i_drama_iv_thumb)
  protected ImageView ivThumb;

  @BindView(R.id.i_drama_tv_name)
  protected TextView tvName;

  @BindView(R.id.i_drama_tv_date_created)
  protected TextView tvDateCreated;

  public DramaViewHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }

  public void setDrama(Drama drama) {
    Glide.with(itemView)
        .load(drama.getThumbUrl())
        .apply(new RequestOptions().centerCrop())
        .into(ivThumb);
    tvName.setText(drama.getName());
    tvDateCreated.setText(DateUtils.toDateString(drama.getDateCreated()));
  }
}
