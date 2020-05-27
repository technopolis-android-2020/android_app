package com.technopolis.listener;

import android.view.View;

import com.technopolis.database.entity.Agent;
import com.technopolis.fragments.StoriesFragment;
import com.technopolis.fragments.StoriesRecyclerFragment;

import java.util.List;

import androidx.fragment.app.FragmentManager;

public class OnAgentClickListener implements View.OnClickListener {
    private FragmentManager fragmentManager;

    private List<Agent> agents;
    private int agentPosition;
    private StoriesRecyclerFragment storiesRecyclerFragment;

    public OnAgentClickListener(FragmentManager fragmentManager, int agentPosition, List<Agent> agents) {
        this.fragmentManager = fragmentManager;
        this.agents = agents;
        this.agentPosition = agentPosition;
        this.storiesRecyclerFragment = new StoriesRecyclerFragment();
    }

    @Override
    public void onClick(View v) {
        storiesRecyclerFragment.setStartItem(agentPosition);
        storiesRecyclerFragment.setAgents(agents);
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, storiesRecyclerFragment)
                .addToBackStack(StoriesFragment.BACK_STACK_NAME)
                .commit();
    }

}
