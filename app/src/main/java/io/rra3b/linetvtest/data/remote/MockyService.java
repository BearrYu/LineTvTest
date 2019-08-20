package io.rra3b.linetvtest.data.remote;

import io.reactivex.Single;
import io.rra3b.linetvtest.data.remote.gson.Drama;
import io.rra3b.linetvtest.data.remote.gson.Response;
import retrofit2.http.GET;

public interface MockyService {

  String BASEURL = "http://www.mocky.io/v2/";

  @GET("5a97c59c30000047005c1ed2")
  Single<Response<Drama>> fetchDramaList();
}
