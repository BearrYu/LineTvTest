package io.rra3b.linetvtest.data.local.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import io.reactivex.Completable;
import java.util.List;

public interface BaseModel<T> {

  @Insert
  Completable insert(T obj);

  @Insert
  Completable insert(List<T> obj);

  @Update
  Completable update(T obj);

  @Update
  Completable update(List<T> obj);

  @Delete
  Completable delete(T obj);

  @Delete
  Completable delete(List<T> obj);
}
