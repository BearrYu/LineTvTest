package io.rra3b.linetvtest.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class Data<T> {

  @SerializedName("data")
  private T data;

  //region Getter and Setter.
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
  //endregion
}