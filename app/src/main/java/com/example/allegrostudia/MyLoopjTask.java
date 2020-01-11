package com.example.allegrostudia;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {

    private static final String TAG = "MOVIE_TRIVIA";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    String BASE_URL = "https://api.allegro.pl/sale/categories/";
    String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdLCJleHAiOjE1Nzg3ODA2NjEsImp0aSI6ImM0MzViMWU5LWU0Y2YtNDA4OS1iNDU1LTgwMjIzOGM1YmQ1NyIsImNsaWVudF9pZCI6IjBkOGIyYzRhOTZjMzQ3Mjc5YTc5MTNmYThmOWI0YzNlIn0.gg6nS3Y0mkh0yXq68i44Plxj-HbLQk7NdkJTYn0MkyNEspe2GJQO3EnPAj4x6ddeIHf8XQtrP9KwlcBgtz4CQ1pIBWD4XEpzGMJS9H1dBx9rp2Ti3A4Fms_7612xpHqzwkR8V8iKpUrS_PKDNHNIsdG56cq9zvPmBNVRJSdReUeZYAr2lrBenB7IHm9UZevJo-JDw2ZA65cGK4mhELXIE7SoxpmNsnsMi21AE7tAg1L5SFBWP4RxfaRbOFqFJhhit7o_XlEOb4Q7juKn4n0WVUI3841eJKuRvDM4vulAy6x5zjXCOnGGXv93IcTN7EpkV7s_9cnenrIsXLd_DQvjNw";
    String jsonResponse;
    String JD;

    public MyLoopjTask() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("Authorization", "Bearer " + TOKEN);
        asyncHttpClient.addHeader("Accept", "application/vnd.allegro.public.v1+json");
        requestParams = new RequestParams();
    }

    public void executeLoopjCall(final String queryTerm) {
        asyncHttpClient.get(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
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
