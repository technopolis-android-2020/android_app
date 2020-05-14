package com.technopolis.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.technopolis.database.entity.News;
import com.android.volley.toolbox.JsonArrayRequest;
import com.technopolis.database.repositories.NewsRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class RequestBuilder {

    public static JsonArrayRequest loadAllNewsRequest(String url, final NewsRepository newsRepository) {
        return new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        handleNewRequestResponse(response, newsRepository);
                        Log.i("NewsLoading", "News handling has finished");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("NewsLoading", "Error delivered while loading news");
                    }
                });
    }

    private static void handleNewRequestResponse(JSONArray response, NewsRepository newsRepository) {
        int totalNumberOfNews = response.length();

        for (int i = 0; i < totalNumberOfNews; i++) {
            try {
                JSONObject object = response.getJSONObject(i);
                newsRepository.insertProduct(
                        new News(
                                object.getString("title"),
                                object.getString("logo"),
                                object.getString("body"),
                                object.getString("url"),
                                object.getString("date")
                        )
                );
            } catch (JSONException e) {
                Log.e("NewsLoading", "Error handling news");
            }
        }
    }

}
