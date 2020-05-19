package com.technopolis.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.technopolis.R;
import com.technopolis.activity.MainActivity;
import com.technopolis.database.entity.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>  {
    private List<News> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.rec_item_text_view);
            imageView = view.findViewById(R.id.rec_item_image_view);
        }

        void bind(News news) {
            textView.setText(news.getTitle());
            Glide.with(imageView.getContext()).load(news.getPreviewImgUrl()).into(imageView);
        }
    }

    public MainActivityAdapter(List<News> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MainActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
