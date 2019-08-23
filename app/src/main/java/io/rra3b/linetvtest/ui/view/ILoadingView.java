package io.rra3b.linetvtest.ui.view;

import androidx.annotation.Nullable;

public interface ILoadingView {

  void showLoading(@Nullable String loadingText);

  void hideLoading();
}
