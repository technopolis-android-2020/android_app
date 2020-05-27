package com.technopolis.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.ListOfAgentsAdapter;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.database.repositories.NewsRepository;
import com.technopolis.fragments.SettingsFragment;
import com.technopolis.network.retrofit.HttpClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView listOfAgents;
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    final NewsAdapter adapter = new NewsAdapter();
    final ListOfAgentsAdapter listOfAgentsAdapter = new ListOfAgentsAdapter();
    @Inject
    HttpClient httpClient;
    @Inject
    AgentRepository agentRepository;
    @Inject
    NewsRepository newsRepository;
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
        ListOfAgentsAdapter.ViewHolder.mainActivityFragmentManager = getSupportFragmentManager();

        NewsAdapter.NewsViewHolder.fragmentManager = getSupportFragmentManager();

        // refresh list
        swipeContainer.setOnRefreshListener(this::fetchDataFromServer);

        // configure refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light);

        fetchDataFromBD();
        fetchDataFromServer();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    private void fetchDataFromBD() {
        compositeDisposable.add(
                agentRepository.getAgents()
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::displayAgents));

        compositeDisposable.add(
                newsRepository.getAllNews()
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::displayNews));
    }

    private void fetchDataFromServer() {
        Single<Boolean> checkInternetConnection = ReactiveNetwork.checkInternetConnectivity();

        compositeDisposable.add(checkInternetConnection
                .subscribeOn(Schedulers.io())
                .subscribe(isConnectedToInternet -> {
                    if (isConnectedToInternet) {
                        compositeDisposable.add(
                                httpClient.getAgentsResponse()
                                        //запрос агентов с сервера, добавление их в бд и отрисовка из бд
                                        .doOnNext(agentsResponses -> agentRepository.insertAgents(agentsResponses))
                                        .flatMap(agent -> agentRepository.getAgents())
                                        .doOnNext(this::preDisplayAgent)
                                        //запрос новостей с сервера, добавление их в бд и отрисовка из бд
                                        .flatMap(newsResponse -> httpClient.getNewsByDate(getLatestDate()))
                                        .doOnNext(listNews -> newsRepository.insertAllNews(listNews))
                                        .flatMap(news -> newsRepository.getAllNews())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(this::displayNews));
                        Log.d(LOG_TAG, "News are updated!");
                    } else {
                        Log.d(LOG_TAG, "No internet connection");
                    }
                }));
        swipeContainer.setRefreshing(false);
    }

    private void preDisplayAgent(List<Agent> agents) {
        compositeDisposable.add(Observable.fromArray(agents)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayAgents));
    }

    private Long getLatestDate() {
        Long result = newsRepository.getLatestDate();
        return result == null ? 0 : result;
    }

    private void displayNews(List<NewsWithAgent> news) {
        adapter.updateAdapter(news);
        recyclerView.setAdapter(adapter);
    }

    private void displayAgents(List<Agent> agents) {
        listOfAgentsAdapter.updateAdapter(agents);
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
            placeSettingFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void placeSettingFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .addToBackStack("placeSettingsFragment")
                .commit();
    }

}
