package io.rra3b.linetvtest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.GsonBuilder;
import io.rra3b.linetvtest.data.remote.MockyService;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import io.rra3b.linetvtest.viewmodel.DramaViewModel;
import io.rra3b.linetvtest.viewmodel.DramaViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InjectorUtils {

  private static volatile MockyService sMockyService;

  public static MockyService provideMockyService() {
    if (sMockyService == null) {
      synchronized (InjectorUtils.class) {
        if (sMockyService == null) {
          sMockyService = new Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                  .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                  .create()))
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .baseUrl(MockyService.BASEURL)
              .build()
              .create(MockyService.class);
        }
      }
    }
    return sMockyService;
  }

  public static DramaViewModel provideDramaViewModel(Fragment fragment) {
    return ViewModelProviders.of(fragment, provideDramaViewModelFactory())
        .get(DramaViewModel.class);
  }

  public static DramaViewModel provideDramaViewModel(FragmentActivity fragmentActivity) {
    return ViewModelProviders.of(fragmentActivity, provideDramaViewModelFactory())
        .get(DramaViewModel.class);
  }

  private static DramaViewModelFactory provideDramaViewModelFactory() {
    return new DramaViewModelFactory(provideDramasRepository());
  }

  private static DramasRepository provideDramasRepository() {
    return new DramasRepository(provideMockyService());
  }

}
