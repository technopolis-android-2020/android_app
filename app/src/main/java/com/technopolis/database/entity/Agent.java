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

    @ColumnInfo(name = "prev_img")
    public String prevImgUrl;

    @ColumnInfo(name = "is_shown")
    public boolean isShown;


    @Ignore
    public Agent(@NonNull final String name) {
        this.name = name;
        this.isShown = true;
    }

    public Agent(@NonNull final String name, final boolean isShown, @NonNull final String prevImgUrl) {
        this.name = name;
        this.isShown = isShown;
        this.prevImgUrl = prevImgUrl;
    }

    public String getPrevImgUrl() {
        return prevImgUrl;
    }

    public void setPrevImgUrl(String prevImg) {
        this.prevImgUrl = prevImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }
}
