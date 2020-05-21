package com.technopolis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technopolis.R;
import com.technopolis.database.entity.Agent;
import com.technopolis.network.model.AgentsResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfAgentsAdapter extends RecyclerView.Adapter<ListOfAgentsAdapter.ViewHolder> {

    private List<AgentsResponse> agents;

    public ListOfAgentsAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_of_agents_item_tv);
        }

        public void bind(AgentsResponse agent) {
            this.textView.setText(agent.title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_of_agents_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(agents.get(position));
    }

    @Override
    public int getItemCount() {
        return agents.size();
    }

    public void updateAdapter(List<AgentsResponse> agents) {
        this.agents = agents;
    }
}
