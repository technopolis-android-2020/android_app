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
    private String publicationDate;

    public News(final int id, @NonNull final String title,
                @NonNull final String previewImgUrl, @NonNull final String body,
                @NonNull final String url, final String publicationDate) {
        this.id = id;
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    @Ignore
    public News(@NonNull final String title, @NonNull final String previewImgUrl,
                @NonNull final String body, @NonNull final String url,
                final String publicationDate) {
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

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull final String title) {
        this.title = title;
    }

    @NonNull
    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(@NonNull final String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }

    @NonNull
    public String getBody() {
        return body;
    }

    public void setBody(@NonNull final String body) {
        this.body = body;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull final String url) {
        this.url = url;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
