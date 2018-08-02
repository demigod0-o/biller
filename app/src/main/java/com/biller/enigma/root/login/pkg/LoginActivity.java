package com.biller.enigma.root.login.pkg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.biller.enigma.root.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.biller.enigma.root.home.pkg.HomeActivity;
import com.biller.enigma.root.login.pkg.loginContract.loginView;
import com.biller.enigma.root.register.pkg.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements loginView {

    private String inputPhoneNumber, inputPassword;
    private LoginPresenter loginPresenter;
    private AwesomeProgressDialog awesomeProgressDialog;

    @BindView(R.id.input_phone_edt)
    EditText phoneEDT;
    @BindView(R.id.input_password_edt)
    EditText passwordEDT;
    @BindView(R.id.login_btn)
    Button loginBTN;
    @BindView(R.id.sign_up_btn)
    Button signUpBTN;
    @BindView(R.id.company_logo_imgv)
    ImageView companyLogoIMGV;

    Unbinder butterKnife;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        butterKnife = ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        loginPresenter.setContext(this);
        awesomeProgressDialog = getAwesomeProgressDialog();

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputPhoneNumber = phoneEDT.getText().toString().trim();
                inputPassword = passwordEDT.getText().toString().trim();
                loginPresenter.onLoginClick(inputPhoneNumber, inputPassword);
            }
        });

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.goToSignUpClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        butterKnife.unbind();
    }

    @Override
    public void loginError(String e) {
        AwesomeErrorDialog awesomeErrorDialog = new AwesomeErrorDialog(this)
                .setTitle("login failed")
                .setMessage(e)
                .setCancelable(true);
        awesomeErrorDialog.show();
    }

    @Override
    public void missingRequireField(String e) {
        loginError(e);
    }

    @Override
    public void pendingLogin(boolean is) {
        if (is) {
            awesomeProgressDialog.show();
        } else {
            awesomeProgressDialog.hide();
        }

    }

    private AwesomeProgressDialog getAwesomeProgressDialog() {
        return new AwesomeProgressDialog(this)
                .setTitle(R.string.app_name)
                .setMessage(R.string.app_name)
                .setColoredCircle(R.color.dialogInfoBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(false);
    }

    @Override
    public void makeIntent() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void intentToRegister() {
        Toast.makeText(this, "create a new account", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
