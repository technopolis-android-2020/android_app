package com.technopolis.database.repositories;

import android.content.Context;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.AgentDao;
import com.technopolis.database.entity.Agent;

import java.util.List;

import androidx.annotation.NonNull;

public class AgentRepository {

    private AgentDao agentDao;

    public AgentRepository(final Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        this.agentDao = database.agentDao();
    }

    public int getAgentId(@NonNull final String name) {
        return getAgent(name).id;
    }

    public Agent getAgent(@NonNull final String name) {
        return agentDao.getAgent(name);
    }
    public List<Agent> getAgents() {
        return agentDao.getAll();
    }

    public void insertAgents(List<Agent> agents) {
        agentDao.insert(agents);
    }

    public void insertAgent(Agent agent) {
        agentDao.insert(agent);
    }

}
