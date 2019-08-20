package io.rra3b.linetvtest.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.rra3b.linetvtest.data.repo.DramasRepository;

public class DramaViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private DramasRepository dramasRepository;

  public DramaViewModelFactory(DramasRepository dramasRepository) {
    this.dramasRepository = dramasRepository;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    //noinspection unchecked
    return (T) new DramaViewModel(dramasRepository);
  }
}
