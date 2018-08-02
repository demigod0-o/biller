package com.biller.enigma.root.fragment.pkg.account.fragment.pkg;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountDataModel {

    private String accountName;
    private String accountAddress;
    private String phoneNumber;
    private String mailAddress;
    private String storeType;
    private String walletAmount;

    public AccountDataModel(String accountName, String accountAddress, String phoneNumber,
                            String mailAddress, String storeType, String walletAmount) {
        this.accountName = accountName;
        this.accountAddress = accountAddress;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.storeType = storeType;
        this.walletAmount = walletAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getStoreType() {
        return storeType;
    }

    public String getWalletAmount() {
        return walletAmount;
    }

    public String getWalletAmountMMK(){
        return walletAmount+" MMK";
    }
}
