package com.technopolis.database.pojo;

import com.technopolis.database.entity.Agent;
import com.technopolis.database.entity.News;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class AgentWithNews {

    @Embedded
    public Agent agent;

    @Relation(parentColumn = "id", entityColumn = "agent_id")
    public List<News> news;
}
