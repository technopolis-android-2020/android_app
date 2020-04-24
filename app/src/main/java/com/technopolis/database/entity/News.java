package com.technopolis.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "preview_img_url")
    public String previewImgUrl;

    @ColumnInfo(name = "body")
    public String body;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "publication_date")
    public long publicationDate;
}
