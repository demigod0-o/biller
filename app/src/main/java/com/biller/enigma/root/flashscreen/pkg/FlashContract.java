package com.biller.enigma.root.flashscreen.pkg;

import android.content.Context;

public interface FlashContract {

    interface flashView{

        void intentLogin();

        void intentHome();

    }

    interface flashPresenter{
        void intentDecision(Context context);
    }
}
