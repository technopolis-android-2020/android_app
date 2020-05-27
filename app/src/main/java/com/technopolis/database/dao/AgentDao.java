package com.technopolis.database.dao;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.technopolis.database.entity.Agent;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface AgentDao {

    @Query("SELECT MAX(id) FROM agent")
    int getMaxAgentId();

    @Query("SELECT * FROM agent")
    Observable<List<Agent>> getAll();

    @Query("SELECT * FROM agent")
    List<Agent> getAllNotObservable();

    @Query("SELECT * FROM agent WHERE name = :name")
    Agent getAgent(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Agent> agents);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Agent agent);

    @Query("SELECT is_shown from agent WHERE name = :name")
    boolean getIsShown(@NonNull final String name);

    @Query("UPDATE agent SET is_shown = :isShown WHERE name = :name")
    void setIsShown(@NonNull final String name, boolean isShown);

}
