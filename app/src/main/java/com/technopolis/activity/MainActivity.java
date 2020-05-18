package com.technopolis.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.R;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.network.model.NewsResponse;
import com.technopolis.network.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    final NewsAdapter adapter = new NewsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view
        recyclerView = findViewById(R.id.main_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void fetchData() {
        compositeDisposable.add(RetrofitClient.getNewsResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayData));
    }

    private void displayData(List<NewsResponse> newsResponses) {
        adapter.updateAdapter(newsResponses);
        recyclerView.setAdapter(adapter);
    }
}
