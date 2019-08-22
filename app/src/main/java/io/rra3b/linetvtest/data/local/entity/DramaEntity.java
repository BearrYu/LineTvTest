package io.rra3b.linetvtest.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import io.rra3b.linetvtest.data.remote.response.Drama;
import java.util.Date;

/**
 * Gson entity of drama.
 *
 * <pre>
 *  {
 *    "drama_id": 1,
 *    "name": "致我們單純的小美好",
 *    "total_views": 23562274,
 *    "created_at": "2017-11-23T02:04:39.000Z",
 *    "thumb": "https://i.pinimg.com/originals/61/d4/be/61d4be8bfc29ab2b6d5cab02f72e8e3b.jpg",
 *    "rating": 4.4526
 *  }
 * </pre>
 */
@Entity(tableName = "dramas")
public class DramaEntity {

  @PrimaryKey
  @ColumnInfo(name = "drama_id")
  private int dramaId;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "total_views")
  private int totalViews;

  @ColumnInfo(name = "date_created")
  private Date dateCreated;

  @ColumnInfo(name = "thumb")
  private String thumbUrl;

  @ColumnInfo(name = "rating")
  private float rating;

  public DramaEntity() {
  }

  public DramaEntity(Drama drama) {
    this.dramaId = drama.getDramaId();
    this.name = drama.getName();
    this.totalViews = drama.getTotalViews();
    this.dateCreated = drama.getDateCreated();
    this.thumbUrl = drama.getThumbUrl();
    this.rating = drama.getRating();
  }

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

  public int getTotalViews() {
    return totalViews;
  }

  public void setTotalViews(int totalViews) {
    this.totalViews = totalViews;
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
