package com.technopolis.database.dao;

import com.technopolis.database.entity.Agent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AgentDao {

    @Query("SELECT * FROM agent")
    List<Agent> getAll();

    @Query("SELECT * FROM agent WHERE name = :name")
    Agent getAgent(String name);

    @Insert
    void insert(List<Agent> agents);

    @Insert
    void insert(Agent agent);

    @Query("SELECT is_shown from agent WHERE name = :name")
    boolean getIsShown(@NonNull final String name);

    @Query("UPDATE agent SET is_shown = :isShown WHERE name = :name")
    void setIsShown(@NonNull final String name, boolean isShown);

}
