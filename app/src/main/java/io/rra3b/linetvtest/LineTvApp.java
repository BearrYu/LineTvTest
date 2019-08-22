package io.rra3b.linetvtest;

import android.app.Application;

public class LineTvApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    InjectorUtils.initialize(this);
  }
}
