package com.technopolis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.R;
import com.technopolis.network.model.NewsResponse;

import java.util.List;

/*
 * Этот адаптер для работы с сервером, временная мера
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsResponse> newsList;

    public NewsAdapter() {
    }

    public void updateAdapter(List<NewsResponse> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.textTitle.setText(newsList.get(position).title);
        holder.textContent.setText(new StringBuilder(newsList.get(position).body.
                substring(0, 15)));
        holder.textAgent.setText(newsList.get(position).agent);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textContent;
        TextView textAgent;

        public NewsViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.txt_title);
            textContent = view.findViewById(R.id.txt_content);
            textAgent = view.findViewById(R.id.txt_agent);
        }
    }
}
