package com.biller.enigma.root.datalayer.pkg;

import android.content.Context;
import android.content.SharedPreferences;

import com.biller.enigma.root.fragment.pkg.account.fragment.pkg.AccountDataModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SharePrefer {
    private static SharePrefer sharePrefer = null;
    private Context context;

    private String account_info = "account_info";
    private String auth0 = "auth0";


    private SharePrefer() {
    }

    public static SharePrefer getInstance(){
        if (sharePrefer == null){
            sharePrefer = new SharePrefer();
        }
        return sharePrefer;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void saveAccount(AccountDataModel accountModel){
        SharedPreferences sharedPreferences = context.getSharedPreferences(account_info,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(accountModel);
        editor.putString("account",json);
        editor.commit();
    }

    public void saveToken(String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(auth0,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
        editor.commit();
    }

    public String getToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(auth0,Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        return token;
    }


    public AccountDataModel getAccount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(account_info,Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("account","");
        Gson json = new Gson();
        Type type = new TypeToken<AccountDataModel>(){}.getType();
        AccountDataModel accountModel = json.fromJson(account,type);
        return accountModel;
    }
}
