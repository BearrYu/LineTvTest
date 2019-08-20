package io.rra3b.linetvtest.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.rra3b.linetvtest.data.repo.DramasRepository;

public class DramasViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private DramasRepository dramasRepository;

  public DramasViewModelFactory(DramasRepository dramasRepository) {
    this.dramasRepository = dramasRepository;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    //noinspection unchecked
    return (T) new DramasViewModel(dramasRepository);
  }
}
