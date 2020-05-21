package com.technopolis.activity;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.ListOfAgentsAdapter;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.network.model.NewsResponse;
import com.technopolis.network.retrofit.HttpClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView listOfAgents;
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    final NewsAdapter adapter = new NewsAdapter();
    @Inject
    HttpClient httpClient;
    @Inject
    AgentRepository agentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);


        //view
        recyclerView = findViewById(R.id.main_rv);
        listOfAgents = findViewById(R.id.list_of_agents_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfAgents.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        );
        listOfAgents.setAdapter(new ListOfAgentsAdapter(agentRepository.getAgents()));

        fetchData();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void fetchData() {
        compositeDisposable.add(httpClient.getNewsResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsResponse>>() {
                    @Override
                    public void accept(List<NewsResponse> newsResponses) throws Exception {
                        displayData(newsResponses);
                    }
                }));
    }

    private void displayData(List<NewsResponse> newsResponses) {
        adapter.updateAdapter(newsResponses);
        recyclerView.setAdapter(adapter);
    }
}
