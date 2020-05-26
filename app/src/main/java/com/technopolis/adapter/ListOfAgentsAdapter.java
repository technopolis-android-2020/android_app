package com.technopolis.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technopolis.R;
import com.technopolis.database.entity.Agent;
import com.technopolis.listener.OnAgentClickListener;
import com.technopolis.network.model.AgentsResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfAgentsAdapter extends RecyclerView.Adapter<ListOfAgentsAdapter.ViewHolder> {

    private List<AgentsResponse> agents;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static FragmentManager mainActivityFragmentManager;
        public TextView textView;
        public ImageView imageView;
        public ConstraintLayout mainFrame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_of_agents_item_tv);
            imageView = itemView.findViewById((R.id.list_of_agents_item_iv));
            mainFrame = itemView.findViewById(R.id.list_of_agents_frame);
        }

        public void bind(AgentsResponse agent) {
            this.textView.setText(agent.title);
            Glide.with(this.imageView.getContext())
                    .load(Uri.parse(agent.previewImageUrl))
                    .into(this.imageView);
            mainFrame.setOnClickListener(
                    new OnAgentClickListener(mainActivityFragmentManager, new Agent(agent.title))
            );
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
