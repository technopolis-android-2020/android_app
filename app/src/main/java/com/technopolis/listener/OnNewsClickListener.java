package com.technopolis.listener;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.technopolis.database.entity.News;
import com.technopolis.fragments.FullNewsFragment;

public class OnNewsClickListener implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private News news;
    private FullNewsFragment fullNewsFragment;

    public OnNewsClickListener(FragmentManager fragmentManager, News news) {
        this.fragmentManager = fragmentManager;
        this.news = news;
        this.fullNewsFragment = new FullNewsFragment();
    }

    @Override
    public void onClick(View v) {
        fullNewsFragment.setNews(news);
        fragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fullNewsFragment)
                .addToBackStack("fullNewsScreen")
                .commit();
    }
}
