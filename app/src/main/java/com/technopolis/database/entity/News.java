package com.technopolis.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    @NonNull
    private String title;

    @ColumnInfo(name = "preview_img_url")
    @NonNull
    private String previewImgUrl;

    @ColumnInfo(name = "body")
    @NonNull
    private String body;

    @ColumnInfo(name = "url")
    @NonNull
    private String url;

    @ColumnInfo(name = "publication_date")
    private long publicationDate;

    public News(int id, @NonNull String title, @NonNull String previewImgUrl, @NonNull String body, @NonNull String url, long publicationDate) {
        this.id = id;
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    @Ignore
    public News(@NonNull String title, @NonNull String previewImgUrl, @NonNull String body, @NonNull String url, long publicationDate) {
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(@NonNull String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(@NonNull String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(long publicationDate) {
        this.publicationDate = publicationDate;
    }
}
