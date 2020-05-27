package com.technopolis.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technopolis.R;
import com.technopolis.activity.MainActivity;
import com.technopolis.adapter.StoriesAdapter;
import com.technopolis.database.entity.Agent;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class StoriesRecyclerFragment extends Fragment {

    private int startItem;
    private List<Agent> agents;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        hideMainActivityActionBar();
        return inflater.inflate(R.layout.stories_recycler_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager v = getView().findViewById(R.id.stories_recycler_fragment_pager);
        StoriesAdapter storiesAdapter = new StoriesAdapter(
                getChildFragmentManager(),
                FragmentStatePagerAdapter.POSITION_UNCHANGED
        );
        storiesAdapter.updateAdapter(agents);
        v.setAdapter(storiesAdapter);
        v.setCurrentItem(startItem);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showMainActivityActionBar();
    }

    private void hideMainActivityActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    private void showMainActivityActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
