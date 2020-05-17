/*
package com.technopolis.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.technopolis.R;
import com.technopolis.database.entity.News;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder>  {
    private List<News> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.rec_item_text_view);
        }

        public void bind(News news) {
            textView.setText(news.getTitle());
        }
    }

    public MainActivityAdapter(List<News> mDataset) {
        this.mDataset = mDataset;
    }

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
*/
