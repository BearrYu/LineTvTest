package io.rra3b.linetvtest.ui.activity;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import butterknife.BindView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.processors.PublishProcessor;
import io.rra3b.linetvtest.InjectorUtils;
import io.rra3b.linetvtest.R;
import io.rra3b.linetvtest.ui.adapter.DramasAdapter;
import io.rra3b.linetvtest.ui.base.BaseActivity;
import io.rra3b.linetvtest.util.reactivex.SimpleObserverImpl;
import io.rra3b.linetvtest.viewmodel.DramaViewModel;
import java.util.concurrent.TimeUnit;

public class DramasActivity extends BaseActivity {

  private static final String TAG = "DramasActivity";

  @BindView(R.id.a_dramas_fab_search)
  protected FloatingActionButton fabSearch;

  @BindView(R.id.a_dramas_rv_dramas)
  protected RecyclerView rvDramas;
  private GridLayoutManager lmDramas;
  private DramasAdapter adapterDramas;

  private DramaViewModel dramaViewModel;

  private PublishProcessor<Integer> scrollStatePublishProcessor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dramas);

    setupViews();
    initData();
  }

  private void initData() {
    dramaViewModel = InjectorUtils.provideDramaViewModel(this);
    scrollStatePublishProcessor = PublishProcessor.create();

    dramaViewModel.getLiveDramas()
        .observe(this, adapterDramas::setNewData);

    dramaViewModel.refreshDramas();

    scrollStatePublishProcessor
        .toObservable()
        // hide fab once scrolling.
        .doOnNext(scrollState -> {
          Log.d(TAG, "isScrolling: " + (scrollState != RecyclerView.SCROLL_STATE_IDLE));
          if (scrollState != RecyclerView.SCROLL_STATE_IDLE) {
            fabSearch.hide();
          }
        })
        .filter(scrollState -> scrollState == RecyclerView.SCROLL_STATE_IDLE)
        // and debounce 1500ms when idle.
        .debounce(1500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(scrollState -> fabSearch.show())
        .subscribe(new SimpleObserverImpl<>());
  }

  private void setupViews() {
    lmDramas = new GridLayoutManager(this, 2);
    adapterDramas = new DramasAdapter(R.layout.item_drama);
    rvDramas.setLayoutManager(lmDramas);
    rvDramas.setAdapter(adapterDramas);
    rvDramas.addOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        scrollStatePublishProcessor.offer(newState);
      }
    });
  }
}
