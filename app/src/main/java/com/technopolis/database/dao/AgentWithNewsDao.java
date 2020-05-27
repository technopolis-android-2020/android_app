package com.technopolis.database.dao;

import com.technopolis.database.pojo.AgentWithNews;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface AgentWithNewsDao {

    @Transaction
    @Query("SELECT id, name, preview_image_url, is_shown FROM agent")
    List<AgentWithNews> loadAgentWithNews();
}
