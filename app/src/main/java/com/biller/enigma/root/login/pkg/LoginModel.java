package com.biller.enigma.root.login.pkg;

import android.content.Context;
import android.util.Log;

import com.biller.enigma.root.network_layer.NetworkResponse;
import com.biller.enigma.root.datalayer.pkg.SharePrefer;
import com.biller.enigma.root.network_layer.NetworkManager;
import com.biller.enigma.root.network_layer.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class LoginModel implements NetworkResponse {

    private String password;
    private String phone;
    private LoginPresenter loginPresenter;
    private JSONObject loginJson;

    private String json_key_phone = "phone";
    private String json_key_password = "password";

    private SharePrefer sharePrefer;

    public LoginModel(LoginPresenter loginPresenter, Context context) {
        this.loginPresenter = loginPresenter;
        sharePrefer = SharePrefer.getInstance();
        sharePrefer.setContext(context);
    }

    public void setProperties(String phone, String password) {
        this.phone = phone;
        this.password = password;
        packLoginJson();
    }

    public void loginRequest() {
        NetworkManager networkManager = new NetworkManager();
        networkManager.setDataObject(loginJson);
        networkManager.setRequestURL(NetworkUtility.LoginURL);
        networkManager.run(this);
    }

    private void packLoginJson() {
        loginJson = new JSONObject();
        try {
            loginJson.put(json_key_phone, this.phone);
            loginJson.put(json_key_password, this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String token = jsonObject.getString("access_token");
            Log.i(this.getClass().getName(), "onSuccessResponse: " + response);
            sharePrefer.saveToken(token);
            loginPresenter.loginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailedResponse(String response) {
            loginPresenter.loginFailed(response);
    }
}
