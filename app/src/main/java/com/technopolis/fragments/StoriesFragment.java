package com.technopolis.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.database.repositories.NewsRepository;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StoriesFragment extends Fragment {

    public static final String BACK_STACK_NAME = "placeStoriesFragment";

    private NewsWithAgent news;
    private Agent agent;

    @Inject
    NewsRepository newsRepository;

    private TextView agentName;
    private ImageView agentLogo;
    private ImageView backgroundImg;
    private TextView newsTitle;
    private TextView newsText;
    private Button closeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((App) getActivity().getApplication()).getAppComponent().injectStoriesFragment(this);
        //todo use javaRX
        news = newsRepository.loadOneLastNews(agent.name);

        return inflater.inflate(R.layout.stories_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
        fillContent();
    }

    private void findViews() {
        View view = getView();
        agentName = view.findViewById(R.id.stories_fragment_agent_name);
        agentLogo = view.findViewById(R.id.stories_fragment_agent_logo);
        backgroundImg = view.findViewById(R.id.stories_fragment_background_img);
        newsTitle = view.findViewById(R.id.stories_fragment_news_title);
        newsText = view.findViewById(R.id.stories_fragment_news_text);
        closeButton = view.findViewById(R.id.stories_fragment_close_button);
    }

    private void fillContent() {
        agentName.setText(news.agent.name);
        newsTitle.setText(news.news.getTitle());
        newsText.setText(news.news.getBody());

        Glide.with(agentLogo.getContext())
                .load(Uri.parse(news.agent.previewImageUrl))
                .into(agentLogo);

        Glide.with(backgroundImg.getContext())
                .load(Uri.parse(news.news.getPreviewImgUrl()))
                .into(backgroundImg);
    }

    private void setListeners() {
        closeButton.setOnClickListener(v -> {
            getParentFragment().getFragmentManager().popBackStackImmediate();
        });
        backgroundImg.setOnClickListener(v->{
            FullNewsFragment fullNewsFragment = new FullNewsFragment();
            fullNewsFragment.setNews(news);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, fullNewsFragment)
                    .addToBackStack("fullNewsScreen")
                    .commit();
        });
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

}
