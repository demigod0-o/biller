package com.biller.enigma.root.flashscreen.pkg;

import android.content.Context;

import com.biller.enigma.root.datalayer.pkg.SharePrefer;
import com.biller.enigma.root.flashscreen.pkg.FlashContract.*;

public class FlashPresenter implements flashPresenter {

    private flashView fView;

    private SharePrefer sharePrefer;

    public FlashPresenter(flashView fView) {
        this.fView = fView;
        sharePrefer = SharePrefer.getInstance();
    }

    @Override
    public void intentDecision(Context context) {
        sharePrefer.setContext(context);
        String token = sharePrefer.getToken();
        if (token.isEmpty()||token.equals(null)||token.equals("")){
            fView.intentLogin();
        }else {
            fView.intentHome();
        }
    }
}
