package io.rra3b.linetvtest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import java.util.List;

public class DramaViewModel extends ViewModel {

  private DramasRepository dramasRepository;

  private MutableLiveData<List<Drama>> liveDramas;

  public DramaViewModel(DramasRepository dramasRepository) {
    this.dramasRepository = dramasRepository;
    this.liveDramas = new MutableLiveData<>();
  }

  public MutableLiveData<List<Drama>> getLiveDramas() {
    return liveDramas;
  }

  public void refreshDramas() {
    dramasRepository.fetchDramas()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(liveDramas::setValue)
        .subscribe();
  }
}
