package com.technopolis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.R;
import com.technopolis.activity.MainActivity;
import com.technopolis.database.entity.News;
import com.technopolis.network.model.NewsResponse;

import java.util.List;

/*
 * Этот адаптер для работы с сервером, временная мера
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.NewsViewHolder> {

    private List<News> newsList;

    public MainActivityAdapter() {
    }

    public void updateAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public MainActivityAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.textTitle.setText(newsList.get(position).getTitle());
        holder.textContent.setText(new StringBuilder(newsList.get(position).getBody().
                substring(0, 15)));
        holder.textAgent.setText(newsList.get(position).getAgent());
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
