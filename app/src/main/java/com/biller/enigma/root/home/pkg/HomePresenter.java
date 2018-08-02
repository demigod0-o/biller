package com.biller.enigma.root.home.pkg;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;

import com.biller.enigma.root.R;
import com.biller.enigma.root.fragment.pkg.account.fragment.pkg.AccountFragment;
import com.biller.enigma.root.fragment.pkg.history.fragment.pkg.HistoryFragment;
import com.biller.enigma.root.fragment.pkg.notification.fragment.pkg.NotificationFragment;
import com.biller.enigma.root.fragment.pkg.topup.fragment.pkg.TopUpFragment;
import com.biller.enigma.root.home.pkg.HomeContract.homeView;
import com.biller.enigma.root.home.pkg.HomeContract.homePresenter;

import static android.content.ContentValues.TAG;

public class HomePresenter implements homePresenter {
    private homeView view;
    private Fragment fragment = null;

    public HomePresenter(homeView view) {
        this.view = view;
    }


    @Override
    public void onNavigationMenuListener(MenuItem item) {
        Log.i(TAG, "onNavigationItemSelected: ");
        switch (item.getItemId()) {
            case R.id.nav_account:
                fragment = new AccountFragment();
                break;
            case R.id.nav_top_up:
                fragment = new TopUpFragment();
                break;
            case R.id.nav_history:
                fragment = new HistoryFragment();
                break;
            case R.id.nav_notification:
                fragment = new NotificationFragment();
                break;

        }
        if (fragment != null) {
            view.setFragment(fragment);
        }

    }
}
