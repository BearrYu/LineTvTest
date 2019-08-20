package io.rra3b.linetvtest.ui.viewmodel;

import androidx.annotation.IntDef;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DramasViewModel extends ViewModel {

  public static final int FRAGMENT_ACTION_SEARCH = 1;
  public static final int FRAGMENT_ACTION_BACK_TO_LIST = 2;

  @IntDef({FRAGMENT_ACTION_SEARCH, FRAGMENT_ACTION_BACK_TO_LIST})
  @Retention(RetentionPolicy.SOURCE)
  public @interface FragmentAction {

  }

  private DramasRepository dramasRepository;

  private MutableLiveData<List<Drama>> liveDramas;

  private MutableLiveData<Integer> liveFragmentAction;

  public DramasViewModel(DramasRepository dramasRepository) {
    this.dramasRepository = dramasRepository;
    this.liveDramas = new MutableLiveData<>();
    this.liveFragmentAction = new MutableLiveData<>();
  }

  public void refreshDramas() {
    dramasRepository.fetchDramas()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(liveDramas::setValue)
        .subscribe();
  }

  public void setFragmentAction(@FragmentAction int action) {
    liveFragmentAction.setValue(action);
  }

  //region Getters.
  public MutableLiveData<List<Drama>> getLiveDramas() {
    return liveDramas;
  }

  public MutableLiveData<Integer> getLiveFragmentAction() {
    return liveFragmentAction;
  }
  //endregion
}
