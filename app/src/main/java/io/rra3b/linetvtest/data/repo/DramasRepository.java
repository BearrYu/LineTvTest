package io.rra3b.linetvtest.data.repo;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rra3b.linetvtest.data.remote.MockyService;
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.data.remote.gson.Response;
import java.util.List;

public class DramasRepository {

  private MockyService mockyService;

  public DramasRepository(MockyService mockyService) {
    this.mockyService = mockyService;
  }

  public Single<List<Drama>> fetchDramas() {
    return mockyService
        .fetchDramaList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map(Response::getData);
  }
}
