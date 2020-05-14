package com.technopolis.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestService {

    private static RequestService instance;
    private RequestQueue requestQueue;

    private static Context context;

    private RequestService(Context context) {
        RequestService.context = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized RequestService getInstance(Context context) {
        if (instance == null) {
            instance = new RequestService(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> Request<T> addToRequestQueue(Request<T> req) {
        return getRequestQueue().add(req);
    }

}
