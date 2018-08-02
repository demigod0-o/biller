package com.biller.enigma.root.login.pkg;

public interface loginContract {

    interface loginView {

        void loginError(String e);

        void pendingLogin(boolean show);

        void missingRequireField(String e);

        void makeIntent();

        void intentToRegister();

    }

    interface loginPresenter {

        void onLoginClick(String phone, String password);

        boolean checkValidation();

        void loginSuccess();

        void loginFailed(String e);

        void goToSignUpClick();

    }
}
