package com.biller.enigma.root.login.pkg;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.biller.enigma.root.login.pkg.loginContract.loginView;
import com.biller.enigma.root.login.pkg.loginContract.loginPresenter;


public class LoginPresenter implements loginPresenter {

    private loginView lView;
    private String phone, password;
    private String error = null;
    private LoginModel loginModel;
    private Context context;

    public LoginPresenter(loginView l) {
        this.lView = l;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Override
    public void onLoginClick(String phone, String password) {
        this.phone = phone;
        this.password = password;
        lView.pendingLogin(true);
        if (checkValidation()) {
            loginModel = new LoginModel(this,context);
            loginModel.setProperties(phone,password);
            loginModel.loginRequest();
        }else {
            lView.pendingLogin(false);
            lView.missingRequireField(error);
        }
    }

    @Override
    public boolean checkValidation() {

        if (this.phone.isEmpty() && this.password.isEmpty()) {
            this.error = "please fill the require field";
        }

        if (this.phone.isEmpty()) {
            this.error = "phone no is require \n";
        }

        if (this.password.isEmpty()) {
            this.error = "password is empty";
        }

        return this.error==null;

    }

    @Override
    public void loginSuccess() {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lView.pendingLogin(false);
                lView.makeIntent();
            }
        };
        handler.post(runnable);
    }

    @Override
    public void loginFailed(final String e) {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lView.pendingLogin(false);
                lView.loginError(e);
            }
        };
        handler.post(runnable);
    }

    @Override
    public void goToSignUpClick() {
        lView.intentToRegister();
    }


}
