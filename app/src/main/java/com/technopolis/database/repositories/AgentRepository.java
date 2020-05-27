package com.technopolis.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.AgentDao;
import com.technopolis.database.entity.Agent;
import com.technopolis.network.model.AgentsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class AgentRepository {

    private AgentDao agentDao;

    public AgentRepository(final Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        this.agentDao = database.agentDao();
    }

    public int getMaxAgentId() {
        return agentDao.getMaxAgentId();
    }

    public int getAgentId(@NonNull final String name) {
        return getAgent(name).id;
    }

    public Agent getAgent(@NonNull final String name) {
        return agentDao.getAgent(name);
    }

    public Observable<List<Agent>> getAgents() {
        return agentDao.getAll();
    }

    public void insertAgents(List<AgentsResponse> agents) {
        agentDao.insert(castToAgents(agents));
    }

    private List<Agent> castToAgents(final List<AgentsResponse> agentResponses) {
        List<Agent> agents = new ArrayList<>();
        for (AgentsResponse response : agentResponses) {
            agents.add(new Agent(response.title, response.previewImageUrl, true));
        }
        return agents;
    }

    public void insertAgent(AgentsResponse agent) {
        agentDao.insert(convertAgentsResponseToAgent(agent));
    }

    private Agent convertAgentsResponseToAgent(AgentsResponse agentsResponse) {
        return new Agent(agentsResponse.title, agentsResponse.previewImageUrl, true);
    }

    public boolean getIsShown(@NonNull final String name) {
        return agentDao.getIsShown(name);
    }

    public void setIsShown(@NonNull final String name, boolean isShown) {
        agentDao.setIsShown(name, isShown);
    }
}
