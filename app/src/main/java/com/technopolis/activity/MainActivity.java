package com.technopolis.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.os.Bundle;
import android.os.AsyncTask;
import android.content.Context;
import android.widget.ProgressBar;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.ListOfAgentsAdapter;
import com.technopolis.adapter.MainActivityAdapter;
import com.android.volley.Request;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.request.RequestBuilder;
import com.technopolis.request.RequestService;
import com.technopolis.database.repositories.NewsRepository;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView listOfAgents;
    @Inject
    NewsRepository newsRepository;
    @Inject
    AgentRepository agentRepository;

    private static String newsUrl = "https://guarded-gorge-91889.herokuapp.com/api/v1/news/getAllNews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);

        context = this;
        recyclerView = findViewById(R.id.main_rv);
        progressBar = findViewById(R.id.main_progress);
        listOfAgents = findViewById(R.id.list_of_agents_rv);

        new DownloadNewsAsyncTask().execute(newsUrl);

        listOfAgents.setAdapter(new ListOfAgentsAdapter(agentRepository.getAgents()));
        listOfAgents.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        );

        recyclerView.setAdapter(
                new MainActivityAdapter(newsRepository.getAllProducts())
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class DownloadNewsAsyncTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            Request newsRequest = RequestService
                    .getInstance(context)
                    .addToRequestQueue(
                            RequestBuilder.loadAllNewsRequest(url, newsRepository)
                    );

            while (!newsRequest.hasHadResponseDelivered() && !newsRequest.isCanceled()) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}
