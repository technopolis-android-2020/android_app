package com.technopolis.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.MainActivityAdapter;
import com.technopolis.database.entity.News;
import com.technopolis.database.repositories.NewsRepository;
import com.technopolis.network.retrofit.HttpClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    final MainActivityAdapter adapterMain = new MainActivityAdapter();
    @Inject
    NewsRepository newsRepository;
    @Inject
    HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);


        //view
        recyclerView = findViewById(R.id.main_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private Long getLatestDate() {
        Long result = newsRepository.getLatestDate();
        return result == null ? 0 : result;
    }

    private void fetchData() {
        compositeDisposable.add(drawNews());
        compositeDisposable.add(
                httpClient.getNewsByDate(getLatestDate())
                .doOnNext(listNews -> newsRepository.insertAllNews(listNews))
                .flatMap(newsResponses -> newsRepository.getAllNews())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayDBData));
    }

    private Disposable drawNews() {
        return newsRepository.getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayDBData);
    }

    private void displayDBData(List<News> newsList) {
        adapterMain.updateAdapter(newsList);
        recyclerView.setAdapter(adapterMain);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
