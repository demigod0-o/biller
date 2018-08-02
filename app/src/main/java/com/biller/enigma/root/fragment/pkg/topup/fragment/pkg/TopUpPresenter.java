package com.biller.enigma.root.fragment.pkg.topup.fragment.pkg;

import com.biller.enigma.root.fragment.pkg.topup.fragment.pkg.TopUpContract.*;

import java.util.regex.Pattern;

public class TopUpPresenter implements TopUpContract.TopUpPresenter {
    private TopUpView topUpView;
    private String phone;
    private int balance;

    public static final int
            MPT = 1,
            OOREDOO = 2,
            TELENOR = 3,
            MYTEL = 4;

    private TopUpModel topUpModel;


    public TopUpPresenter(TopUpView topUpView) {
        this.topUpView = topUpView;
        topUpModel = new TopUpModel();
    }

    @Override
    public void onConfirmClick(String phone, String balance) {
        if (!phone.isEmpty() && !balance.isEmpty()) {
            this.phone = phone;
            this.balance = Integer.parseInt(balance);
            if (Validate()) {
                topUpView.showConfirmDialog();
            }
        }
    }

    @Override
    public void onConfirm() {
        int Operator = findOperator();
        if (Operator==0){
            return;
        }
        topUpModel.packJson(Operator,phone);
    }


    private boolean Validate() {

        if (!phoneNumberValidation()) {
            topUpView.showPhoneNoError();
            return false;
        }
        if ((balance % 1000) != 0) {
            topUpView.showBalanceError();
            return false;
        }
        return true;
    }

    private int findOperator() {
        Pattern mptPattern = Pattern.compile("^([09]{2})(2|4|8)\\w{8}");
        Pattern ooredooPattern = Pattern.compile("^([09]{2})(9)\\w{8}");
        Pattern telenorPattern = Pattern.compile("^([09]{2})(7)\\w{8}");
        Pattern mytelPattern = Pattern.compile("^([09]{2})(6)\\w{8}");
        if (mptPattern.matcher(phone).matches()){
            return MPT;
        }
        if (ooredooPattern.matcher(phone).matches()){
            return OOREDOO;
        }
        if (telenorPattern.matcher(phone).matches()){
            return TELENOR;
        }
        if (mytelPattern.matcher(phone).matches()){
            return MYTEL;
        }
        return 0;
    }

    private boolean phoneNumberValidation() {
        Pattern pattern = Pattern.compile("^[09]{2}(2|4|[6-9])\\w{8}");
        return pattern.matcher(phone).matches();
    }
}
