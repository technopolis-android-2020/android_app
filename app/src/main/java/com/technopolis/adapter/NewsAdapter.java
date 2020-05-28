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
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.listener.OnNewsClickListener;

import java.util.Calendar;
import java.util.Date;
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
        TextView publicationDate;
        TextView textAgent;
        ImageView newsImage;
        CardView card;

        public NewsViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.txt_title);
            publicationDate = view.findViewById(R.id.publication_date);
            textAgent = view.findViewById(R.id.txt_agent);
            newsImage = view.findViewById(R.id.rec_item_image_view);
            card = view.findViewById(R.id.card_item);
        }

        void bind(NewsWithAgent news) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(news.news.getPublicationDate()));

            this.textTitle.setText(news.news.getTitle());
            this.textAgent.setText(news.agent.name);
            this.publicationDate.setText(publicationDate.getContext().getString(
                    R.string.news_publication_date,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR))
            );

            card.setOnClickListener(new OnNewsClickListener(fragmentManager, news));
            Glide.with(this.newsImage.getContext())
                    .load(Uri.parse(news.news.getPreviewImgUrl()))
                    .into(this.newsImage);
        }
    }
}
