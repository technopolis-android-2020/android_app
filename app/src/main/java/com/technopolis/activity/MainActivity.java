package com.technopolis.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technopolis.R;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.network.model.NewsResponse;
import com.technopolis.network.retrofit.NewsServerAPI;
import com.technopolis.network.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    NewsServerAPI newsServerAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init Retrofit
        Retrofit retrofit = RetrofitClient.getInstance();
        newsServerAPI = retrofit.create(NewsServerAPI.class);

        //view
        recyclerView = findViewById(R.id.main_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void fetchData() {
        compositeDisposable.add(newsServerAPI.getNews()
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
        NewsAdapter adapter = new NewsAdapter(this, newsResponses);
        recyclerView.setAdapter(adapter);
    }
}
