package com.biller.enigma.root.register.pkg;

public interface RegisterContract {

    interface registerView {

        void InvalidName();

        void InvalidPhone();

        void InvalidEmail();

        void InvalidPassword();

        void InvalidAddress();

        void showConfirmDialog();
    }

    interface registerPresenter {

        void onRegisterClick(String name, String phone, String address, String email, String type, String password);

        void onBackClick();

        void checkValidation();

        boolean mailValidation();

        void registerConfirm();

        void RegisterSuccess();

        void RegisterFailed();

    }
}
