package io.rra3b.linetvtest;

import android.app.Application;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.GsonBuilder;
import io.rra3b.linetvtest.data.local.LineTvDatabase;
import io.rra3b.linetvtest.data.local.dao.DramaModel;
import io.rra3b.linetvtest.data.remote.MockyService;
import io.rra3b.linetvtest.data.repo.DramasRepository;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModel;
import io.rra3b.linetvtest.ui.viewmodel.DramasViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("WeakerAccess")
public class InjectorUtils {

  private static final String APPLICATION_CONTEXT_IS_NULL = "Application context is null. Maybe you has not called InjectorUtils.initialize(Context) method.";

  private static volatile MockyService sMockyService;
  private static volatile LineTvDatabase sDatabase;

  private static Application sApp;

  public static void initialize(Application appContext) {
    InjectorUtils.sApp = appContext;
  }

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

  private static LineTvDatabase provideLineTvDatabase() {
    if (sDatabase == null) {
      synchronized (InjectorUtils.class) {
        if (sDatabase == null) {
          if (sApp == null) {
            throw new RuntimeException(APPLICATION_CONTEXT_IS_NULL);
          }
          sDatabase = LineTvDatabase.newInstance(sApp);
        }
      }
    }
    return sDatabase;
  }

  public static DramaModel provideDramaModel() {
    return provideLineTvDatabase().dramaModel();
  }

  public static DramasViewModel provideDramasViewModel(FragmentActivity fragmentActivity) {
    return ViewModelProviders.of(fragmentActivity, provideDramasViewModelFactory())
        .get(DramasViewModel.class);
  }

  private static DramasViewModelFactory provideDramasViewModelFactory() {
    return new DramasViewModelFactory(sApp, provideDramasRepository());
  }

  private static DramasRepository provideDramasRepository() {
    return new DramasRepository(provideMockyService(), provideDramaModel());
  }
}
