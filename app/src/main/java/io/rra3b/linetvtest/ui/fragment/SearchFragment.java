package io.rra3b.linetvtest.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.ui.adapter.SearchResultAdapter;
import io.rra3b.linetvtest.ui.base.BaseFragment;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;

public class SearchFragment extends BaseFragment {

  public static final String TAG = "SearchFragment";

  @BindView(R.id.f_search_rv_search_results)
  protected RecyclerView rvSearchResults;

  @BindView(R.id.f_search_tv_clipboard)
  protected TextView tvClipboard;

  @BindView(R.id.f_search_sv_search_drama)
  protected SearchView svSearchDrama;

  private SearchResultAdapter adapterSearchResult;
  private LinearLayoutManager lmSearchResult;

  private DramasViewModel mViewModel;

  private SearchFragment() {
  }

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.search_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // setup rvSearchResults.
    lmSearchResult = new LinearLayoutManager(getActivity());
    adapterSearchResult = new SearchResultAdapter(R.layout.item_search_result);
    rvSearchResults.setLayoutManager(lmSearchResult);
    rvSearchResults.setAdapter(adapterSearchResult);

    // show keyboard as default.
    svSearchDrama
        .findViewById(androidx.appcompat.R.id.search_button)
        .performClick();

    // leave this page when close searchView.
    svSearchDrama.setOnCloseListener(() -> {
      mViewModel.setFragmentAction(DramasViewModel.FRAGMENT_ACTION_BACK_TO_LIST);
      return false;
    });

    // query
    svSearchDrama.setOnQueryTextListener(new OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
          showClipboard();
        } else {
          tvClipboard.setVisibility(View.GONE);
        }
        mViewModel.queryDramas(newText);
        return false;
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = InjectorUtils.provideDramasViewModel(getActivity());

    mViewModel.getLiveSearchResult()
        .observe(this, adapterSearchResult::setNewData);
  }

  @Override
  public void onResume() {
    super.onResume();
    mViewModel.cacheClipboardText();
    showClipboard();
  }

  private void showClipboard() {
    String clipboardText = mViewModel.getClipboardText();
    if (clipboardText != null && !clipboardText.equals(svSearchDrama.getQuery().toString())) {
      tvClipboard.setText(clipboardText);

      tvClipboard.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.f_search_tv_clipboard)
  public void searchWithClipText() {
    svSearchDrama.setQuery(tvClipboard.getText().toString(), false);
    tvClipboard.setVisibility(View.GONE);
  }
}
