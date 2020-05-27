package com.technopolis.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.technopolis.R;
import com.technopolis.database.entity.Agent;

import java.util.List;

public class ListOfAgentsAdapter extends RecyclerView.Adapter<ListOfAgentsAdapter.ViewHolder> {

    private List<Agent> agents;

    public ListOfAgentsAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_of_agents_item_tv);
            imageView = itemView.findViewById((R.id.list_of_agents_item_iv));
        }

        public void bind(Agent agent) {
            this.textView.setText(agent.getName());
            Glide.with(this.imageView.getContext())
                    .load(Uri.parse(agent.getPrevImgUrl()))
                    .into(this.imageView);
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

    public void updateAdapter(List<Agent> agents) {
        this.agents = agents;
    }
}
