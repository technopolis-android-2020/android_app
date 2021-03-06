package com.technopolis.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "news",
        indices = {@Index(value = {"id"}, unique = true)},
        foreignKeys =
        @ForeignKey(entity =
                Agent.class,
                parentColumns = "id",
                childColumns = "agent_id"))
public class News {

    @PrimaryKey(autoGenerate = true)
    private long id;

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
    private Long publicationDate;

    @ColumnInfo(name = "agent_id")
    private int agentId;

    @Ignore
    private String agentName;

    public News(final long id, @NonNull final String title,
                @NonNull final String previewImgUrl, @NonNull final String body,
                @NonNull final String url, final Long publicationDate, final int agentId) {
        this.id = id;
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
        this.agentId = agentId;
    }

    public News(final long id, @NonNull final String title,
                @NonNull final String previewImgUrl, @NonNull final String body,
                @NonNull final String url, final Long publicationDate, final String agentName) {
        this.id = id;
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
        this.agentName = agentName;
    }

    @Ignore
    public News(@NonNull final String title, @NonNull final String previewImgUrl,
                @NonNull final String body, @NonNull final String url,
                final Long publicationDate, final String agentName) {
        this.title = title;
        this.previewImgUrl = previewImgUrl;
        this.body = body;
        this.url = url;
        this.publicationDate = publicationDate;
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Long publicationDate) {
        this.publicationDate = publicationDate;
    }
}
