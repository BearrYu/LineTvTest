package io.rra3b.linetvtest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.processors.PublishProcessor;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.ui.adapter.DramaOverviewAdapter;
import io.rra3b.linetvtest.ui.base.BaseFragment;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;
import io.rra3b.linetvtest.util.reactivex.SimpleObserverImpl;
import java.util.concurrent.TimeUnit;

public class DramasFragment extends BaseFragment {

  public static final String TAG = "DramasFragment";

  @BindView(R.id.a_dramas_fab_search)
  protected FloatingActionButton fabSearch;

  @BindView(R.id.a_dramas_rv_dramas)
  protected RecyclerView rvDramas;
  private GridLayoutManager lmDramas;
  private DramaOverviewAdapter adapterDramas;

  private DramasViewModel mViewModel;

  private PublishProcessor<Integer> scrollStatePublishProcessor;

  public static DramasFragment newInstance() {
    return new DramasFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dramas_fragment, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // setup rvDramas.
    lmDramas = new GridLayoutManager(rvDramas.getContext(), 2);
    adapterDramas = new DramaOverviewAdapter(R.layout.item_drama_overview);
    rvDramas.setLayoutManager(lmDramas);
    rvDramas.setAdapter(adapterDramas);

    // dispatch scroll state to scrollStatePublishProcessor.
    rvDramas.addOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        scrollStatePublishProcessor.offer(newState);
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = InjectorUtils.provideDramasViewModel(getActivity());

    scrollStatePublishProcessor = PublishProcessor.create();

    // setup drama data flow.
    mViewModel.getLiveDramasOverview()
        .observe(this, adapterDramas::setNewData);
    mViewModel.refreshDramas();

    // hideLoading fabSearch on rvDramas scrolling.
    scrollStatePublishProcessor
        .toObservable()
        // hideLoading fab once scrolling.
        .doOnNext(scrollState -> {
          if (scrollState != RecyclerView.SCROLL_STATE_IDLE) {
            fabSearch.hide();
          }
        })
        .filter(scrollState -> scrollState == RecyclerView.SCROLL_STATE_IDLE)
        // and debounce 1500ms when idle.
        .debounce(800, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(scrollState -> fabSearch.show())
        .subscribe(new SimpleObserverImpl<>());
  }

  @OnClick(R.id.a_dramas_fab_search)
  public void dispatchActionSearch() {
    mViewModel.setFragmentAction(DramasViewModel.FRAGMENT_ACTION_SEARCH);
  }

}
