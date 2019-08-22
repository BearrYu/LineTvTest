package io.rra3b.linetvtest.data.repo;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.rra3b.linetvtest.data.local.dao.DramaModel;
import io.rra3b.linetvtest.data.local.dto.DramaOverview;
import io.rra3b.linetvtest.data.local.dto.DramaSearchResult;
import io.rra3b.linetvtest.data.local.entity.DramaEntity;
import io.rra3b.linetvtest.data.remote.MockyService;
import io.rra3b.linetvtest.data.remote.response.Drama;
import java.util.List;
import retrofit2.HttpException;

public class DramasRepository {

  private MockyService mockyService;
  private DramaModel dramaModel;

  public DramasRepository(MockyService mockyService, DramaModel dramaModel) {
    this.mockyService = mockyService;
    this.dramaModel = dramaModel;
  }

  public Maybe<List<DramaOverview>> getDramasOverview() {
    return dramaModel.getDramasOverviewSortBy(DramaModel.COLUMN_DATE);
  }

  public Maybe<List<DramaOverview>> fetchDramas(final boolean fetchOnly) {
    return mockyService
        .fetchDramaList()
        .flatMapCompletable(response -> {
          if (!response.isSuccessful() || response.body() == null) {
            throw new HttpException(response);
          }

          return saveDramas(response.body().getData());
        })
        .andThen(Maybe.defer(() -> fetchOnly
            ? Maybe.empty()
            : getDramasOverview())
        );
  }

  public Maybe<List<DramaSearchResult>> getDramasNameContains(String keyword) {
    return dramaModel.getDramasNameLike("%" + keyword + "%");
  }

  private Completable saveDramas(List<Drama> dramas) {
    List<DramaEntity> dramaEntities = Observable.fromIterable(dramas)
        .map(Drama::toEntity)
        .toList()
        .blockingGet();

    return dramaModel.insert(dramaEntities);
  }
}
