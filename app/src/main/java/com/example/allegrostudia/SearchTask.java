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
    String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1Nzg5NTEyMTQsImp0aSI6IjFlMTNlYzU3LWJmMGYtNDkzNC1hMjFmLTYzNDA2OGJkNTAzOSIsImNsaWVudF9pZCI6IjBkOGIyYzRhOTZjMzQ3Mjc5YTc5MTNmYThmOWI0YzNlIn0.aIkWvoE1NpwzxZrtNd8RbxcHMhPxh6eddinnScKI5ABfLopMwbJWJUF1d7adwouozFqW7Ilma9jcfdGEvH5K77ZaMAlzm4wJAj8zceeydZKzJS-FsW7ewq0UjiX1blhRxHowzoTc8ZH0y06j7-1b6XXGNvqIr4VTnV_wT_j2Ccr2a-IMKo3oDdxPnp0MgnxQ1c-uCQ-yzULLIn1RFILBsdigD-S2XsBNWjnlGeUyHhRDValEOp45lFRLsKS678bScHk8UrUirhFX8Jeg8C1WqOTuMCvrVxNfXPPkc0hAma2344hyKs_R9cevArJPkxCDgiFgpQErREjF_zadWSa1Cw";
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
//                Log.i(TAG, "onSuccess: " + jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "onFailure: " + errorResponse);
            }
        });
    }
}
