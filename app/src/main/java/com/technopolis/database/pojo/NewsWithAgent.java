package com.technopolis.database.pojo;

import com.technopolis.database.entity.Agent;
import com.technopolis.database.entity.News;

import androidx.room.Embedded;
import androidx.room.Relation;

public class NewsWithAgent {

    @Embedded
    public News news;

    @Relation(parentColumn = "agent_id", entityColumn = "id")
    public Agent agent;
}
