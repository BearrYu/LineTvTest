package io.rra3b.linetvtest.ui.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import io.rra3b.linetvtest.data.local.dto.DramaOverview;
import io.rra3b.linetvtest.ui.adapter.viewholder.DramaOverviewViewHolder;

public class DramaOverviewAdapter extends BaseQuickAdapter<DramaOverview, DramaOverviewViewHolder> {

  public DramaOverviewAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(@NonNull DramaOverviewViewHolder vh, DramaOverview item) {
    vh.setDrama(item);
  }
}
