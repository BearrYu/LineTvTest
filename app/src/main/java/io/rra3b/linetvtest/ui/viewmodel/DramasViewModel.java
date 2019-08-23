package io.rra3b.linetvtest.ui.viewmodel;

import android.app.Application;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.data.local.dto.DramaOverview;
import io.rra3b.linetvtest.data.local.dto.DramaSearchResult;
import io.rra3b.linetvtest.data.local.entity.DramaEntity;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import io.rra3b.linetvtest.util.reactivex.MaybeTransformers;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DramasViewModel extends AndroidViewModel {

  public static final int FRAGMENT_ACTION_SEARCH = 1;
  public static final int FRAGMENT_ACTION_BACK_TO_LIST = 2;

  @IntDef({FRAGMENT_ACTION_SEARCH, FRAGMENT_ACTION_BACK_TO_LIST})
  @Retention(RetentionPolicy.SOURCE)
  public @interface FragmentAction {

  }

  private DramasRepository dramasRepository;

  private MutableLiveData<List<DramaOverview>> liveDramasOverview;
  private MutableLiveData<List<DramaSearchResult>> liveDramaSearchResult;

  private MutableLiveData<Integer> liveDramaToShow;
  private MutableLiveData<Integer> liveFragmentAction;

  private String cachedClipboardText;

  public DramasViewModel(Application application, DramasRepository dramasRepository) {
    super(application);
    this.dramasRepository = dramasRepository;
    this.liveDramasOverview = new MutableLiveData<>();
    this.liveDramaSearchResult = new MutableLiveData<>();
    this.liveDramaToShow = new MutableLiveData<>();
    this.liveFragmentAction = new MutableLiveData<>();
  }

  public void refreshDramas() {
    dramasRepository.getDramasOverview()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess(liveDramasOverview::setValue)
        .subscribe();
  }

  public void setFragmentAction(@FragmentAction int action) {
    liveFragmentAction.setValue(action);
  }

  @SuppressWarnings("ConstantConditions")
  public void cacheClipboardText() {

    ClipboardManager clipboard = (ClipboardManager) getApplication()
        .getSystemService(Context.CLIPBOARD_SERVICE);

    boolean hasDataForPasting = clipboard.hasPrimaryClip()
        && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);

    cachedClipboardText = hasDataForPasting
        ? clipboard.getPrimaryClip().getItemAt(0).getText().toString()
        : null;
  }

  @Nullable
  public String getClipboardText() {
    return cachedClipboardText;
  }

  public void queryDramas(String queryText) {
    if (TextUtils.isEmpty(queryText)) {
      liveDramaSearchResult.setValue(null);
      return;
    }

    dramasRepository.getDramasNameContains(queryText)
        .doOnSuccess(liveDramaSearchResult::postValue)
        .compose(MaybeTransformers.switchSchedulers())
        .subscribe();
  }

  public Maybe<DramaEntity> getDrama(int id) {
    return dramasRepository.getDramaById(id)
        .compose(MaybeTransformers.switchSchedulers());
  }

  public void showDramaDetail(int dramaId) {
    liveDramaToShow.setValue(dramaId);
  }

  //region Getters.
  public MutableLiveData<List<DramaOverview>> getLiveDramasOverview() {
    return liveDramasOverview;
  }

  public MutableLiveData<List<DramaSearchResult>> getLiveSearchResult() {
    return liveDramaSearchResult;
  }

  public MutableLiveData<Integer> getLiveDramaToShow() {
    return liveDramaToShow;
  }

  public MutableLiveData<Integer> getLiveFragmentAction() {
    return liveFragmentAction;
  }
  //endregion
}
