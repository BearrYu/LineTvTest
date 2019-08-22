package io.rra3b.linetvtest.data.remote;

import io.reactivex.Single;
import io.rra3b.linetvtest.data.remote.response.Data;
import io.rra3b.linetvtest.data.remote.response.Drama;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;

public interface MockyService {

  String BASEURL = "http://www.mocky.io/v2/";

  @GET("5a97c59c30000047005c1ed2")
  Single<Response<Data<List<Drama>>>> fetchDramaList();
}
