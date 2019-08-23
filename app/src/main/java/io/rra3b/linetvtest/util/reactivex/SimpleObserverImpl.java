package io.rra3b.linetvtest.util.reactivex;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SimpleObserverImpl<T> implements Observer<T> {

  private CompositeDisposable disposable;

  public SimpleObserverImpl() {
    disposable = new CompositeDisposable();
  }

  @Override
  public void onSubscribe(Disposable d) {
    disposable.add(d);
  }

  @Override
  public void onNext(T t) {

  }

  @Override
  public void onError(Throwable e) {
    e.printStackTrace();
  }

  @Override
  public void onComplete() {

  }
}
