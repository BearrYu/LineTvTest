package io.rra3b.linetvtest.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import io.rra3b.linetvtest.data.repo.DramasRepository;

public class DramasViewModelFactory extends AndroidViewModelFactory {

  private Application application;
  private DramasRepository dramasRepository;

  public DramasViewModelFactory(@NonNull Application application,
      DramasRepository dramasRepository) {
    super(application);
    this.application = application;
    this.dramasRepository = dramasRepository;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    //noinspection unchecked
    return (T) new DramasViewModel(application, dramasRepository);
  }
}
