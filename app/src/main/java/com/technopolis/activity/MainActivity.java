package com.technopolis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.technopolis.App;
import com.technopolis.R;
import com.technopolis.adapter.ListOfAgentsAdapter;
import com.technopolis.adapter.NewsAdapter;
import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.database.repositories.NewsRepository;
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
    @Inject
    NewsRepository newsRepository;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);
/*
        agentRepository.insertAgent(new Agent("RBC", "http://pics.rbc.ru/img/fp_v4/skin/img/v6-logo.png", true));
        agentRepository.insertAgent(new Agent("Nplus1", "https://nplus1.ru/i/logo.png", true));

        newsRepository.insertNews(new NewsResponse(
                "date",
                "Nplus1",
                "https://nplus1.ru/images/2020/05/23/c7266b9be54e8e8b9c7fd5cb43f0974f.jpg",
                1,
                "Первую попытку забора грунта с астероида Бенну перенесли на два месяца",
                "Астрономы зарегистрировали квазипериодические мерцания Стрельца А* — сверхмассивной черной дыры в центре Млечного пути. По мнению исследователей, колебания излучения, наблюдавшиеся в миллиметровом диапазоне, могут быть связаны с возникновением горячих пятен в аккреционном диске вокруг компактного источника. Статья опубликована в The Astrophysical Journal Letters.  В центральной части Млечного Пути, на расстоянии около 26 тысяч световых лет от Солнца, находится компактный радиоисточник Стрелец A*, который, скорее всего, представляет собой сверхмассивную черную дыру с массой 4,2 миллиона масс Солнца. Это ближайший к нам объект такого типа, что делает его крайне привлекательным для исследований. Более чем за 20 лет наблюдений ученым удалось узнать, что черная дыра окружена аккреционным диском из горячего газа, вещество которого падает по спирали на черную дыру, и диском из более холодного молекулярного газа, а также массивными горячими звездами. Кроме того, исследователи регистрируют исходящие от Стрельца A* вспышки в радио, ближнем инфракрасном и рентгеновском диапазоне, однако вопрос о том, периодичны ли они, долгое время оставался открытым.  Юхэй Ивата (Yuhei Iwata) из Университета Кэйо вместе с коллегами наблюдали Стрелец А* в миллиметровом диапазоне электромагнитных волн с помощью комплекса телескопов Atacama Large Millimeter Array. В течение 10 дней, 70 минут в день, астрономы регистрировали, как меняется плотность потока излучения, исходящего от источника в центре нашей галактики. На полученных в результате кривых блеска ученые заметили два феномена: квазипериодические колебания, возникающие примерно раз в полчаса, и более медленные, часовые вариации. Авторы работы сосредоточились на коротких временных колебаниях и обнаружили, что 30-минутный период изменения потока излучения сопоставим с периодом обращения внутреннего края аккреционного диска с радиусом 0,2 астрономической единицы. Для сравнения, Меркурий вращается вокруг Солнца на среднем расстоянии 0,4 астрономической единицы. По мнению группы Итавы, колебания на кривой блеска могли вызвать горячие пятна, образующиеся из-за магнитных возмущений в горячем газе, движущимся по круговой орбите вблизи сверхмассивной черной дыры. Астрономы надеются, что полученные данные смогут больше рассказать нам о поведении черной дыры и газа вокруг нее. С другой стороны, исследователи опасаются, что столь быстрое вращение внутренней части аккреционного диска может помешать проекту Телескоп горизонта событий (EHT) получить изображение ближайших окрестностей Стрельца А*. «Чем быстрее движение, тем сложнее заснять объект», — говорит Томохару Ока, профессор Университета Кейо и один из авторов работы. В 2019 году проекту EHT вперые удалось разглядеть тень сверхмассивной черной дыры в центре активной галактики M87. Это стало знаковым событием для всей астрономии. Подробнее о контексте подобных исследований можно прочитать в материале «Взгляд в бездну».",
                "https://nplus1.ru/news/2020/05/23/OSIRIS-REx-october-tag"
        ));

        newsRepository.insertNews(new NewsResponse(
                "date",
                "RBC",
                "http://pics.rbc.ru/img/fp_v4/skin/img/v6-logo.png",
                2,
                "Захарова осудила позицию США по Договору по открытому небу",
                "Астрономы зарегистрировали квазипериодические мерцания Стрельца А* — сверхмассивной черной дыры в центре Млечного пути. По мнению исследователей, колебания излучения, наблюдавшиеся в миллиметровом диапазоне, могут быть связаны с возникновением горячих пятен в аккреционном диске вокруг компактного источника. Статья опубликована в The Astrophysical Journal Letters.  В центральной части Млечного Пути, на расстоянии около 26 тысяч световых лет от Солнца, находится компактный радиоисточник Стрелец A*, который, скорее всего, представляет собой сверхмассивную черную дыру с массой 4,2 миллиона масс Солнца. Это ближайший к нам объект такого типа, что делает его крайне привлекательным для исследований. Более чем за 20 лет наблюдений ученым удалось узнать, что черная дыра окружена аккреционным диском из горячего газа, вещество которого падает по спирали на черную дыру, и диском из более холодного молекулярного газа, а также массивными горячими звездами. Кроме того, исследователи регистрируют исходящие от Стрельца A* вспышки в радио, ближнем инфракрасном и рентгеновском диапазоне, однако вопрос о том, периодичны ли они, долгое время оставался открытым.  Юхэй Ивата (Yuhei Iwata) из Университета Кэйо вместе с коллегами наблюдали Стрелец А* в миллиметровом диапазоне электромагнитных волн с помощью комплекса телескопов Atacama Large Millimeter Array. В течение 10 дней, 70 минут в день, астрономы регистрировали, как меняется плотность потока излучения, исходящего от источника в центре нашей галактики. На полученных в результате кривых блеска ученые заметили два феномена: квазипериодические колебания, возникающие примерно раз в полчаса, и более медленные, часовые вариации. Авторы работы сосредоточились на коротких временных колебаниях и обнаружили, что 30-минутный период изменения потока излучения сопоставим с периодом обращения внутреннего края аккреционного диска с радиусом 0,2 астрономической единицы. Для сравнения, Меркурий вращается вокруг Солнца на среднем расстоянии 0,4 астрономической единицы. По мнению группы Итавы, колебания на кривой блеска могли вызвать горячие пятна, образующиеся из-за магнитных возмущений в горячем газе, движущимся по круговой орбите вблизи сверхмассивной черной дыры. Астрономы надеются, что полученные данные смогут больше рассказать нам о поведении черной дыры и газа вокруг нее. С другой стороны, исследователи опасаются, что столь быстрое вращение внутренней части аккреционного диска может помешать проекту Телескоп горизонта событий (EHT) получить изображение ближайших окрестностей Стрельца А*. «Чем быстрее движение, тем сложнее заснять объект», — говорит Томохару Ока, профессор Университета Кейо и один из авторов работы. В 2019 году проекту EHT вперые удалось разглядеть тень сверхмассивной черной дыры в центре активной галактики M87. Это стало знаковым событием для всей астрономии. Подробнее о контексте подобных исследований можно прочитать в материале «Взгляд в бездну».",
                "https://www.rbc.ru/rbcfreenews/5ec9bb499a79476d950ab83e"
        ));
*/
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

        // refresh list
        swipeContainer.setOnRefreshListener(this::fetchData);

        // configure refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
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

}
