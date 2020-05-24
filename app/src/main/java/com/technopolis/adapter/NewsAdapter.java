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

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textContent;
        TextView textAgent;
        ImageView newsImage;

        public NewsViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.txt_title);
            textContent = view.findViewById(R.id.txt_content);
            textAgent = view.findViewById(R.id.txt_agent);
            newsImage = view.findViewById(R.id.rec_item_image_view);
        }

        void bind(NewsResponse newsResponse) {
            this.textTitle.setText(newsResponse.title);
            this.textContent.setText(newsResponse.body.substring(0, 15));
            this.textAgent.setText(newsResponse.agent);
            Glide.with(this.newsImage.getContext())
                    .load(Uri.parse(newsResponse.logo))
                    .into(this.newsImage);
        }
    }
}
