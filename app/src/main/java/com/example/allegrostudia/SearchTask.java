package com.example.allegrostudia;

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
//    String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1NzkzODQ4MTAsImp0aSI6IjY3NDU2ZjEwLWU4ZWEtNDNhNy05ZmVmLWU0YTRjMTNiMzI4YSIsImNsaWVudF9pZCI6IjBkOGIyYzRhOTZjMzQ3Mjc5YTc5MTNmYThmOWI0YzNlIn0.jRwgqU_u1RBV_TVcU48hQWz6hSERibACl-Ih_w-kioRBzBpm5ZHV0RCbvy0t3cW_tkCoclm64fjBuVbb3Wvq59DfRyOBinlWrveweqiVLWJ76wFirm4GNPtPC9J9OaHgK_YhAyPfgQD-iSJzq1FaP2YDiLBSXvNQJWRn2ShZDK8FCQ6ui9slrhQOjIrqZOBHeRCoOe_66WcDPF_YMQ67nNlf64exI-FKNKN_ucbExMW7Qb5V2NyRLRvF3lYLWPTOrZvijVokAogvK_SyIlvAlMfKFsZxdE4jYJ-tJS9_PCwraFVRdtBYACU8mgopKVNyhTeO26xpnx1ailzCSp61aA";
    String jsonResponse;

    public SearchTask(String productName, String categoryID, OnLoopjCompleted listener, String TOKEN) {
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
