package io.rra3b.linetvtest.data.local.dao;

import androidx.annotation.StringDef;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.rra3b.linetvtest.data.local.dto.DramaOverview;
import io.rra3b.linetvtest.data.local.dto.DramaSearchResult;
import io.rra3b.linetvtest.data.local.entity.DramaEntity;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Dao
public abstract class DramaModel implements BaseModel<DramaEntity> {

  public static final String COLUMN_DATE = "date_created";
  public static final String COLUMN_RATING = "rating";
  public static final String COLUMN_TOTAL_VIEWS = "total_views";

  @StringDef({COLUMN_DATE, COLUMN_RATING, COLUMN_TOTAL_VIEWS})
  @Retention(RetentionPolicy.SOURCE)
  public @interface SortColumns {

  }

  @Query("SELECT "
      + "    drama_id, "
      + "    name, "
      + "    date_created, "
      + "    thumb, "
      + "    rating "
      + "FROM "
      + "  dramas "
      + "ORDER "
      + "  BY :column ASC"
  )
  public abstract Maybe<List<DramaOverview>> getDramasOverviewSortBy(@SortColumns String column);

  @Query("SELECT "
      + "    drama_id, "
      + "    name, "
      + "    thumb "
      + "FROM "
      + "    dramas "
      + "WHERE "
      + "    name LIKE :like"
  )
  public abstract Maybe<List<DramaSearchResult>> getDramasNameLike(String like);

  // insert or update.
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract Completable insert(DramaEntity obj);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract Completable insert(List<DramaEntity> obj);
}
