package com.biller.enigma.root.network_layer;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkManager {

    private String dataObject;
    private String URL;
    private NetworkResponse callback;

    public void setDataObject(JSONObject dataObject) {
        this.dataObject = dataObject.toString();
    }

    public void run(NetworkResponse callback) {
        this.callback = callback;
        new HttpRequest().start();
    }

    public void setRequestURL(String url) {
        this.URL = url;
    }

    class HttpRequest extends Thread {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON,dataObject);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            try
            {
                Thread.sleep(100);
                Response response = client.newCall(request).execute();
                String resp = response.body().string().toString();
                Thread.sleep(1000);
                if (response.code()>=200 && response.code()<300)
                {
                    callback.onSuccessResponse(resp);
                }
                else
                {
                    callback.onFailedResponse(resp);
                }

            } catch (Exception e) {
                e.printStackTrace();
                callback.onFailedResponse(null);
            }

        }

        private void sendBack(final Response response){

        }
    }
}
