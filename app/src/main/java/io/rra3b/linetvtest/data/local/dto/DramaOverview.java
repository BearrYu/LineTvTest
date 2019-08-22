package io.rra3b.linetvtest.data.local.dto;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import java.util.Date;

public class DramaOverview {

  @PrimaryKey
  @ColumnInfo(name = "drama_id")
  private int dramaId;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "date_created")
  private Date dateCreated;

  @ColumnInfo(name = "thumb")
  private String thumbUrl;

  @ColumnInfo(name = "rating")
  private float rating;

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

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getThumbUrl() {
    return thumbUrl;
  }

  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

//endregion
}
