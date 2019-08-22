package io.rra3b.linetvtest.data.local.dto;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class DramaSearchResult {

  @PrimaryKey
  @ColumnInfo(name = "drama_id")
  private int dramaId;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "thumb")
  private String thumbUrl;

  //region Getters and Setters
  public int getDramaId() {
    return dramaId;
  }

  public void setDramaId(int dramaId) {
    this.dramaId = dramaId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getThumbUrl() {
    return thumbUrl;
  }

  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
  }
  //endregion
}
