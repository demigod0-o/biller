package com.biller.enigma.root.fragment.pkg.account.fragment.pkg;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.biller.enigma.root.fragment.pkg.account.fragment.pkg.AccountContract.*;


public class AccountFragmentPresenter implements AccountPresenter {

    private AccountView view;
    private AccountModel accountInteractor;

    public AccountFragmentPresenter(AccountView view, Context context) {
        this.view = view;
        accountInteractor = new AccountModel(this,context);
    }

    @Override
    public void onCreateViewListener() {
        accountInteractor.fetchAccountInfo();
    }

    @Override
    public void onLocalResponse(AccountDataModel accountModel) {
        view.setAccountValues(accountModel);
    }

    @Override
    public void onServerResponse(final AccountDataModel accountModel) {
       Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                view.setAccountValues(accountModel);
            }
        };
        handler.post(runnable);
    }

    @Override
    public void updateWalletAmount() {

    }

}
