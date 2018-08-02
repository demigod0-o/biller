package com.biller.enigma.root.network_layer;

import android.os.Handler;
import android.os.Looper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGetRequest {

    private NetworkResponse callback;
    private String URL;

    public HttpGetRequest(NetworkResponse callback, String URL) {
        this.callback = callback;
        this.URL = URL;
    }

    public void run(){
        new GetRequest().start();
    }

    class GetRequest extends Thread {

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.code()>=200 && response.code()<300){
                    callback.onSuccessResponse(response.body().string().toString());
                }else {
                    callback.onFailedResponse(response.body().string().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private void sendBack(final Response response) {
            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            handler.post(runnable);
        }
    }
}
