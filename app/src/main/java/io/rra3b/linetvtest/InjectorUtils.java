package io.rra3b.linetvtest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.GsonBuilder;
import io.rra3b.linetvtest.data.remote.MockyService;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InjectorUtils {

  private static volatile MockyService sMockyService;

  private static MockyService provideMockyService() {
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

  public static DramasViewModel provideDramasViewModel(Fragment fragment) {
    return ViewModelProviders.of(fragment, provideDramasViewModelFactory())
        .get(DramasViewModel.class);
  }

  public static DramasViewModel provideDramasViewModel(FragmentActivity fragmentActivity) {
    return ViewModelProviders.of(fragmentActivity, provideDramasViewModelFactory())
        .get(DramasViewModel.class);
  }

  private static DramasViewModelFactory provideDramasViewModelFactory() {
    return new DramasViewModelFactory(provideDramasRepository());
  }

  private static DramasRepository provideDramasRepository() {
    return new DramasRepository(provideMockyService());
  }

}
