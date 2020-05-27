package com.technopolis.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.technopolis.R;
import com.technopolis.database.entity.News;
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.listener.OnNewsClickListener;
import com.technopolis.network.model.NewsResponse;
import com.technopolis.database.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsWithAgent> newsList;

    public NewsAdapter() {
    }

    public void updateAdapter(List<NewsWithAgent> newsList) {
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
        public static FragmentManager fragmentManager;
        TextView textTitle;
        TextView textContent;
        TextView textAgent;
        ImageView newsImage;
        CardView card;

        public NewsViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.txt_title);
            textContent = view.findViewById(R.id.txt_content);
            textAgent = view.findViewById(R.id.txt_agent);
            newsImage = view.findViewById(R.id.rec_item_image_view);
            card = view.findViewById(R.id.card_item);
        }

        void bind(NewsWithAgent news) {
            this.textTitle.setText(news.news.getTitle());
            this.textContent.setText(news.news.getBody().substring(0, 15));
            this.textAgent.setText(news.agent.name);

            card.setOnClickListener(new OnNewsClickListener(fragmentManager, news));
            Glide.with(this.newsImage.getContext())
                    .load(Uri.parse(news.news.getPreviewImgUrl()))
                    .into(this.newsImage);
        }
    }
}
