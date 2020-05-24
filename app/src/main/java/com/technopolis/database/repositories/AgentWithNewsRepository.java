package com.technopolis.database.repositories;

import android.content.Context;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.AgentWithNewsDao;
import com.technopolis.database.pojo.AgentWithNews;

import java.util.List;

public class AgentWithNewsRepository {

    private AgentWithNewsDao agentWithNewsDao;

    public AgentWithNewsRepository(final Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        this.agentWithNewsDao = database.agentWithNewsDao();
    }

    public List<AgentWithNews> loadAgentWithNews() {
        return agentWithNewsDao.loadAgentWithNews();
    }
}
