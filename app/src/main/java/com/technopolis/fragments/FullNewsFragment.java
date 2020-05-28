package com.technopolis.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.activity.MainActivity;
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.database.repositories.AgentRepository;

import javax.inject.Inject;

public class FullNewsFragment extends Fragment {
    private NewsWithAgent news;
    private ImageView agentLogo;
    private TextView agentName;
    private Button closeButton;
    private ImageView newsImage;
    private TextView newsTitle;
    private TextView newsBody;
    private static final String LOG_TAG = FullNewsFragment.class.getSimpleName();

    @Inject
    AgentRepository agentRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getAppComponent().injectFullNewsFragment(this);
        hideMainActivityActionBar();
        return inflater.inflate(R.layout.news_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        fillContent();
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void findViews() {
        View view = getView();
        agentLogo = view.findViewById(R.id.icon_news);
        agentName = view.findViewById(R.id.agent_name);
        newsImage = view.findViewById(R.id.image_news);
        closeButton = view.findViewById(R.id.close_button);
        newsTitle = view.findViewById(R.id.news_title);
        newsBody = view.findViewById(R.id.news_body);
    }

    private void fillContent() {
        agentName.setText(news.agent.name);
        newsTitle.setText(news.news.getTitle());
        newsBody.setText(news.news.getBody());

        Glide.with(agentLogo.getContext())
                .load(Uri.parse(news.agent.previewImageUrl))
                .into(agentLogo);

        Glide.with(newsImage.getContext())
                .load(news.news.getPreviewImgUrl())
                .into(newsImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showMainActivityActionBar();
    }

    private void hideMainActivityActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    private void showMainActivityActionBar() {
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }

    public void setNews(NewsWithAgent news) {
        this.news = news;
    }
}
