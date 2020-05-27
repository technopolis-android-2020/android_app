package com.technopolis.adapter;

import android.os.Parcelable;

import com.technopolis.database.entity.Agent;
import com.technopolis.fragments.StoriesFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class StoriesAdapter extends FragmentPagerAdapter {

    private List<Agent> agents;

    public StoriesAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        StoriesFragment fragment = new StoriesFragment();
        fragment.setAgent(agents.get(position));
        return fragment;
    }


    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getCount() {
        return agents.size();
    }

    public void updateAdapter(List<Agent> newsWithAgents) {
        this.agents = newsWithAgents;
    }
}
