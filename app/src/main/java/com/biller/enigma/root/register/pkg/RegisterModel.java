package com.biller.enigma.root.register.pkg;

import android.util.Log;

import com.biller.enigma.root.network_layer.NetworkManager;
import com.biller.enigma.root.network_layer.NetworkResponse;
import com.biller.enigma.root.network_layer.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class RegisterModel implements NetworkResponse {
    private RegisterPresenter presenter;
    private String store_address;
    private String store_phone;
    private String store_mail;
    private String password;
    private String store_type;
    private JSONObject jsonObject;
    private String store_name;

    public RegisterModel(RegisterPresenter registerPresenter) {
        this.presenter = registerPresenter;
    }

    public void setProperties(String name,
                              String store_address,
                              String store_phone,
                              String store_mail,
                              String password,
                              String store_type
    ) {
        this.store_name = name;
        this.store_address = store_address;
        this.store_phone = store_phone;
        this.store_mail = store_mail;
        this.password = password;
        this.store_type = store_type;
    }

    public void packJson() {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("store_name", store_name);
            jsonObject.put("store_address", store_address);
            jsonObject.put("phone", store_phone);
            jsonObject.put("store_mail", store_mail);
            jsonObject.put("password", password);
            jsonObject.put("store_type", "cc store");
            Log.i(TAG, "packJson: " + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void registerRequest() {
        NetworkManager networkManager = new NetworkManager();
        networkManager.setDataObject(jsonObject);
        networkManager.setRequestURL(NetworkUtility.RegisterURL);
        networkManager.run(this);
    }

    @Override
    public void onSuccessResponse(String response) {
        Log.i(TAG, "onSuccessResponse: " + response);
    }

    @Override
    public void onFailedResponse(String response) {
        Log.i(TAG, "onFailedResponse: " + response);
    }
}
