package io.rra3b.linetvtest.ui.adapter;

import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import io.rra3b.linetvtest.data.local.dto.DramaSearchResult;
import io.rra3b.linetvtest.ui.adapter.viewholder.SearchResultViewHolder;

public class SearchResultAdapter extends BaseQuickAdapter<DramaSearchResult, SearchResultViewHolder> {

  public SearchResultAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(@NonNull SearchResultViewHolder vh, DramaSearchResult item) {
    vh.setDrama(item);
  }
}
