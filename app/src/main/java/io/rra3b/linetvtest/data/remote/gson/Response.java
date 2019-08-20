package io.rra3b.linetvtest.data.remote.gson;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response<T> {

  @SerializedName("data")
  private List<T> data;

  //region Getters and Setters
  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
  //endregion
}
