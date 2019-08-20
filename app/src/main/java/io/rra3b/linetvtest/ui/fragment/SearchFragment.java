package io.rra3b.linetvtest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.ui.base.BaseFragment;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;

public class SearchFragment extends BaseFragment {

  public static final String TAG = "SearchFragment";

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
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = InjectorUtils.provideDramasViewModel(getActivity());
  }

}
