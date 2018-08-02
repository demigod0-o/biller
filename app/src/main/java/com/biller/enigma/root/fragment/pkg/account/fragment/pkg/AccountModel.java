package com.biller.enigma.root.fragment.pkg.account.fragment.pkg;

import android.content.Context;
import android.util.Log;

import com.biller.enigma.root.network_layer.HttpGetRequest;
import com.biller.enigma.root.network_layer.NetworkResponse;
import com.biller.enigma.root.datalayer.pkg.SharePrefer;
import com.biller.enigma.root.network_layer.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

import com.biller.enigma.root.fragment.pkg.account.fragment.pkg.AccountContract.*;

public class AccountModel implements NetworkResponse {

    private Context context;
    private SharePrefer sharePrefer;
    private AccountPresenter presenter;

    public AccountModel(AccountPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        sharePrefer = SharePrefer.getInstance();
        sharePrefer.setContext(this.context);
    }

    public void fetchAccountInfo() {
        AccountDataModel accountModel = sharePrefer.getAccount();

        if (accountModel != null) {
            presenter.onLocalResponse(accountModel);
            return;
        }

        String token = sharePrefer.getToken();

        if (token != null && !token.isEmpty()) {
            String url = NetworkUtility.AccountURL + "/" + token;
            Log.i(TAG, "fetchAccountInfo: " + url);
            HttpGetRequest getRequest = new HttpGetRequest(this, url);
            getRequest.run();
        }
    }

    public void saveAccountData(JSONObject dataJson) throws JSONException {
        AccountDataModel accountModel = new AccountDataModel(
                dataJson.getString("store_name"),
                dataJson.getString("store_address"),
                dataJson.getString("phone"),
                dataJson.getString("store_mail"),
                dataJson.getString("store_type"),
                dataJson.getString("wallet_amount")
        );
        sharePrefer.saveAccount(accountModel);
        presenter.onServerResponse(accountModel);
    }

    @Override
    public void onSuccessResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject dataJson = jsonObject.getJSONObject("data");
            saveAccountData(dataJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailedResponse(String response) {

    }
}
