package io.rra3b.linetvtest.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import io.rra3b.linetvtest.data.local.converter.DateConverter;
import io.rra3b.linetvtest.data.local.dao.DramaModel;
import io.rra3b.linetvtest.data.local.entity.DramaEntity;

@Database(entities = {DramaEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class LineTvDatabase extends RoomDatabase {

  public abstract DramaModel dramaModel();

  private static volatile LineTvDatabase INSTANCE;

  public static LineTvDatabase newInstance(Context context) {
    return Room
        .databaseBuilder(context.getApplicationContext(), LineTvDatabase.class, "linetv.db")
        .build();
  }

  public static LineTvDatabase getDatabase(Context context) {
    if (INSTANCE == null) {
      synchronized (LineTvDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room
              .databaseBuilder(context.getApplicationContext(), LineTvDatabase.class, "linetv.db")
              .build();
        }
      }
    }
    return INSTANCE;
  }
}
