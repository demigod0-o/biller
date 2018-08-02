package com.biller.enigma.root.register.pkg;

import android.util.Log;

import com.biller.enigma.root.register.pkg.RegisterContract.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

class RegisterPresenter implements registerPresenter {
    private registerView view;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String type;
    private String password;

    private ArrayList<String> error;
    private RegisterModel registerModel;


    public RegisterPresenter(registerView view) {
        this.view = view;
        error = new ArrayList<>();
    }

    @Override
    public void onRegisterClick(String name, String phone, String address, String email, String type, String password) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.type = type;
        this.password = password;
        error.clear();
        Log.i("regp", "onRegisterClick: ");
        checkValidation();
        if (error.isEmpty()){
            Log.i(TAG, "onRegisterClick: no error");
            registerModel = new RegisterModel(this);
            registerModel.setProperties(this.name,this.address,this.phone,this.email,this.password,type);
            registerModel.packJson();
            view.showConfirmDialog();
        }
    }

    @Override
    public void checkValidation() {

        if ( name.equals("") || name.length() < 5) {
            view.InvalidName();
            error.add("name validaton error");
        }
        if (phone.equals("") || !phoneNumberValidation()) {
            view.InvalidPhone();
            error.add("name validaton error");
        }
        if (address.equals("")) {
            view.InvalidAddress();
            error.add("name validaton error");
        }
        if (email.equals("") || !mailValidation()) {
            view.InvalidEmail();
            error.add("name validaton error");
        }
        if (password.equals("")) {
            view.InvalidPassword();
            error.add("name validaton error");
        }
        if (address.equals("") || !addressValidation()) {
            view.InvalidAddress();
            error.add("name validaton error");
        }
    }

    @Override
    public boolean mailValidation() {
        return email.contains("@") && email.contains(".");
    }

    @Override
    public void registerConfirm() {
        registerModel.registerRequest();
    }

    @Override
    public void onBackClick() {

    }

    @Override
    public void RegisterSuccess() {

    }

    @Override
    public void RegisterFailed() {

    }

    private boolean phoneNumberValidation() {
        Pattern pattern = Pattern.compile("^[09]{2}(2|4|[6-9])\\w{8}");
        return pattern.matcher(phone).matches();
    }

    private boolean addressValidation(){
        Pattern pattern = Pattern.compile("^[a-z|A-Z]*[,]*[a-z|A-Z]*[,]*[a-z|A-Z]*");
        return pattern.matcher(address).matches();
    }
}
