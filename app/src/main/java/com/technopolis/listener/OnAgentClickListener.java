package com.technopolis.listener;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.technopolis.database.entity.Agent;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.fragments.StoriesFragment;
import com.technopolis.network.model.AgentsResponse;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class OnAgentClickListener implements View.OnClickListener {
    private StoriesFragment storiesFragment;
    private FragmentManager fragmentManager;
    private Agent agent;

    public OnAgentClickListener(FragmentManager fragmentManager, Agent agent) {
        this.agent = agent;
        this.fragmentManager = fragmentManager;
        this.storiesFragment = new StoriesFragment();
    }

    @Override
    public void onClick(View v) {
        storiesFragment.setAgent(agent);
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, storiesFragment)
                .addToBackStack("placeStoriesFragment")
                .commit();
    }

}
