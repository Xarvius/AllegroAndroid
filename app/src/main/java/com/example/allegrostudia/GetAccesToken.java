package com.example.allegrostudia;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class GetAccesToken {

    private static final String TAG = "MOVIE_TRIVIA";
    private final OnLoopjCompleted loopjListener;
    AsyncHttpClient asyncHttpClient;
//    RequestParams requestParams;

    String BASE_URL = "https://allegro.pl/auth/oauth/token?grant_type=client_credentials";
    String BASE64 = "MGQ4YjJjNGE5NmMzNDcyNzlhNzkxM2ZhOGY5YjRjM2U6RzV0bkVsdHd4OFpXNnNkQ1JGaXliRzc3MW5KYW0yVXdsSm13YWFGZ1R2UXZuODg3VkJUOFhpVTl4S01VWFRabA==";
    String jsonResponse;

    public GetAccesToken(OnLoopjCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Authorization", "Basic " + BASE64);
        this.loopjListener = listener;
    }

    public void executeLoopjCall() {
        asyncHttpClient.post(BASE_URL, new JsonHttpResponseHandler() {
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
