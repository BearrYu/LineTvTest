package io.rra3b.linetvtest.util.reactivex;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.ui.view.ILoadingView;

public class ObservableTransformers {

  //region switchSchedulers();

  /**
   * do upstream in io thread, observe on main thread, and handle the loading view showing.
   */
  public static <T> ObservableTransformer<T, T> switchSchedulers() {
    return switchSchedulers(null, null);
  }

  public static <T> ObservableTransformer<T, T> switchSchedulers(final ILoadingView loadingView) {
    return switchSchedulers(loadingView, null);
  }

  public static <T> ObservableTransformer<T, T> switchSchedulers(
      final ILoadingView loadingView,
      final String loadingText) {
    return upstream -> upstream
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .doOnSubscribe(disposable -> {
          if (loadingView != null) {
            loadingView.showLoading(loadingText);
          }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally(new Action() {
          @Override
          public void run() throws Exception {
            if (loadingView != null) {
              loadingView.hideLoading();
            }
          }
        });
  }

  //endregion
}
