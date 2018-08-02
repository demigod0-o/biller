package com.biller.enigma.root.flashscreen.pkg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.biller.enigma.root.R;
import com.biller.enigma.root.home.pkg.HomeActivity;
import com.biller.enigma.root.login.pkg.LoginActivity;
import com.biller.enigma.root.flashscreen.pkg.FlashContract.*;
public class FlashActivity extends AppCompatActivity implements flashView
{

    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private flashPresenter fPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen);
        fPresenter = new FlashPresenter(this);
        intentDelay();
    }

    private void intentDelay() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                 fPresenter.intentDecision(FlashActivity.this);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void intentLogin() {
        Intent loginIntent = new Intent(FlashActivity.this, LoginActivity.class);
        FlashActivity.this.startActivity(loginIntent);
        FlashActivity.this.finish();
    }

    @Override
    public void intentHome() {
        Intent mainIntent = new Intent(FlashActivity.this, HomeActivity.class);
        FlashActivity.this.startActivity(mainIntent);
        FlashActivity.this.finish();
    }
}
