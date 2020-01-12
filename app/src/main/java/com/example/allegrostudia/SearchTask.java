package com.example.allegrostudia;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class SearchTask {

    private static final String TAG = "MOVIE_TRIVIA";
    private final OnLoopjCompleted loopjListener;
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    String BASE_URL = "https://api.allegro.pl/offers/listing";
    String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1Nzg4Nzg2MzcsImp0aSI6ImI5MzU5Mjc1LTAxZGQtNDdjMS04NGQ2LTg5ZTgyM2FlZmYwOCIsImNsaWVudF9pZCI6IjBkOGIyYzRhOTZjMzQ3Mjc5YTc5MTNmYThmOWI0YzNlIn0.fi5OYcym4Uqtp-xzKsupKuYp4HmO9eQF8jLMAKMmZ1xbGjT-zyPAUOJyRyintU-S3rnpmUbaAnAOhUlHWjJPVY41KGaX-VEjMovr41q2KggwCzuSXVYuoGuf7vzvBRRKaILPQUMg3ecp9T9UoS46AirS1TtxbZO-FvBrU6vk-2hFgBiMPbwJErFpRM1v49GpQSzwTJYvIYos7s9Pkz5QFpkXYshzD9wZfREzzxjkXqypnZ4VXidfRHzl-roqCMeXBLonSsOEoGUynEwMbOtL6UekYHL3Jh8LeKUu2yehocYGVv9My2EoUc0Lj-CzzG5xMSXfxPW_MGP00nvkT-RXKw";
    String jsonResponse;

    public SearchTask(String productName, String categoryID, OnLoopjCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Authorization", "Bearer " + TOKEN);
        asyncHttpClient.addHeader("Accept", "application/vnd.allegro.public.v1+json");
        requestParams = new RequestParams();
        requestParams.put("phrase", productName);
        requestParams.put("category.id", categoryID);
        this.loopjListener = listener;
    }

    public void executeLoopjCall() {
        asyncHttpClient.get(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.taskCompleted(jsonResponse);
                Log.i(TAG, "onSuccess: " + jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "onFailure: " + errorResponse);
            }
        });
    }
}
