package com.technopolis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.ListOfAgentsAdapter;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.network.model.AgentsResponse;
import com.technopolis.network.model.NewsResponse;
import com.technopolis.network.retrofit.HttpClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int settingsActivityRequestCode = 1;

    private RecyclerView recyclerView;
    private RecyclerView listOfAgents;
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    final NewsAdapter adapter = new NewsAdapter();
    final ListOfAgentsAdapter listOfAgentsAdapter = new ListOfAgentsAdapter();
    @Inject
    HttpClient httpClient;
    @Inject
    AgentRepository agentRepository;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);

        //view
        recyclerView = findViewById(R.id.main_rv);
        listOfAgents = findViewById(R.id.list_of_agents_rv);
        swipeContainer = findViewById(R.id.swipeContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfAgents.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        );

//        agentRepository.insertAgent(new Agent("Vedomosti", "http://pics.rbc.ru/img/fp_v4/skin/img/v6-logo.png", true));
//        agentRepository.insertAgent(new Agent("Habr", "https://nplus1.ru/i/logo.png", true));
//        agentRepository.insertAgent(new Agent("Nplus1", "http://pics.rbc.ru/img/fp_v4/skin/img/v6-logo.png", true));
//        agentRepository.insertAgent(new Agent("RBC", "https://nplus1.ru/i/logo.png", true));

        NewsAdapter.NewsViewHolder.fragmentManager = getSupportFragmentManager();

        // refresh list
        swipeContainer.setOnRefreshListener(this::fetchData);

        // configure refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light);

        fetchData();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void fetchData() {
        compositeDisposable.addAll(
                httpClient.getNewsResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::displayNews),
                httpClient.getAgentsResponse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::displayAgents)
        );

        swipeContainer.setRefreshing(false);
        Log.d(LOG_TAG, "News are updated!");
    }

    private void displayNews(List<NewsResponse> newsResponses) {
        adapter.updateAdapter(newsResponses);
        recyclerView.setAdapter(adapter);
    }

    private void displayAgents(List<AgentsResponse> agentsResponses) {
        listOfAgentsAdapter.updateAdapter(agentsResponses);
        listOfAgents.setAdapter(listOfAgentsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {

            Intent openSettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(openSettings, settingsActivityRequestCode);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (settingsActivityRequestCode) {
            case RESULT_CANCELED:
                // nastroiki menyalis'
                break;
            case RESULT_OK:
                // nastroiki ne menyalis'
                break;
        }
    }
}
