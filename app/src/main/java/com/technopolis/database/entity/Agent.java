package com.technopolis.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "agent",
        indices = {@Index(value = {"id", "name"}, unique = true)})
public class Agent {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "preview_image_url")
    @NonNull
    public String previewImageUrl;

    @ColumnInfo(name = "is_shown")
    public boolean isShown;

    @Ignore
    public Agent(@NonNull final String name) {
        this(name, "", true);
    }

    public Agent(@NonNull final String name, @NonNull final String previewImageUrl, final boolean isShown) {
        this.name = name;
        this.previewImageUrl = previewImageUrl;
        this.isShown = isShown;
    }
}
