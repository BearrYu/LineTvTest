package io.rra3b.linetvtest.ui.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.ui.adapter.viewholder.DramaViewHolder;

public class DramasAdapter extends BaseQuickAdapter<Drama, DramaViewHolder> {

  public DramasAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(@NonNull DramaViewHolder vh, Drama item) {
    vh.setDrama(item);
  }
}
