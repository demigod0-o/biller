package com.biller.enigma.root.fragment.pkg.account.fragment.pkg;


public interface AccountContract {

    interface AccountView {
        void setAccountValues(AccountDataModel accountModel);
    }

    interface AccountPresenter {

        void onCreateViewListener();

        void onLocalResponse(AccountDataModel accountModel);

        void onServerResponse(AccountDataModel accountModel);

        void updateWalletAmount();
    }
}
