package com.biller.enigma.root.fragment.pkg.topup.fragment.pkg;

public interface TopUpContract {

    interface TopUpView{
        void showConfirmDialog();

        void showSuccessDialog();

        void showBalanceError();

        void showPhoneNoError();
    }

    interface TopUpPresenter{

        void onConfirmClick(String phone,String balance);

        void onConfirm();
    }
}
