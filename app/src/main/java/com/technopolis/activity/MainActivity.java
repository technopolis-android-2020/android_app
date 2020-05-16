package com.technopolis.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.os.Bundle;
import android.os.AsyncTask;
import android.content.Context;
import android.widget.ProgressBar;

import com.technopolis.R;
import com.technopolis.adapter.MainActivityAdapter;
import com.android.volley.Request;
import com.technopolis.components.DaggerDatabaseComponent;
import com.technopolis.components.DatabaseComponent;
import com.technopolis.modules.ContextModule;
import com.technopolis.request.RequestBuilder;
import com.technopolis.request.RequestService;
import com.technopolis.database.repositories.NewsRepository;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    NewsRepository newsRepository;

    private static String newsUrl = "https://guarded-gorge-91889.herokuapp.com/api/v1/news/getAll";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseComponent databaseComponent = DaggerDatabaseComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        context = this;
        recyclerView = findViewById(R.id.main_rv);
        progressBar = findViewById(R.id.main_progress);
        newsRepository = databaseComponent.getNewsRepository();

        new DownloadNewsAsyncTask().execute(newsUrl);

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

            while (!newsRequest.hasHadResponseDelivered()) {

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
