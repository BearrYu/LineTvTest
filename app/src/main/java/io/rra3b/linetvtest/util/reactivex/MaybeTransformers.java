package io.rra3b.linetvtest.util.reactivex;

import io.reactivex.MaybeTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.ui.view.ILoadingView;

public class MaybeTransformers {

  //region switchSchedulers();

  /**
   * do upstream in io thread, observe on main thread, and handle the loadingView showing.
   */
  public static <T> MaybeTransformer<T, T> switchSchedulers() {
    return switchSchedulers(null, null);
  }

  public static <T> MaybeTransformer<T, T> switchSchedulers(final ILoadingView loadingView) {
    return switchSchedulers(loadingView, null);
  }

  public static <T> MaybeTransformer<T, T> switchSchedulers(
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
        .doFinally((Action) () -> {
          if (loadingView != null) {
            loadingView.hideLoading();
          }
        });
  }

  //endregion
}
