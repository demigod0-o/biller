package com.biller.enigma.root.home.pkg;


import android.support.v4.app.Fragment;
import android.view.MenuItem;

public interface HomeContract {
    interface homeView{
        void setFragment(Fragment fragment);
        void show(String s);
    }

    interface homePresenter{
        void onNavigationMenuListener(MenuItem item);
    }
}
