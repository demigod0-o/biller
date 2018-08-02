package com.biller.enigma.root.register.pkg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeNoticeDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.biller.enigma.root.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.biller.enigma.root.register.pkg.RegisterContract.*;

public class RegisterActivity extends AppCompatActivity implements registerView {

    @BindView(R.id.backIMGBTN)
    ImageButton backIMGB;

    @BindView(R.id.register_confirm_btn)
    Button confirmBTN;

    @BindView(R.id.store_name_edt)
    EditText storeNameEDT;

    @BindView(R.id.store_phone_edt)
    EditText storePhoneEDT;

    @BindView(R.id.store_address_edt)
    EditText storeAddressEDT;

    @BindView(R.id.store_email_edt)
    EditText storeEmailEDT;

    @BindView(R.id.store_password_edt)
    EditText storePasswordEDT;

    @BindView(R.id.store_type_spinner)
    Spinner spinner;


    private Unbinder unbinder;
    private registerPresenter presenter;
    private String name, address, password, phone, email, type;
    private AwesomeNoticeDialog awesomeNoticeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_register_layout);
        unbinder = ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
        awesomeNoticeDialog = getDialog();

    }

    @OnClick(R.id.register_confirm_btn)
    void onRegisterConfirm() {
        name = storeNameEDT.getText().toString();
        address = storeAddressEDT.getText().toString();
        password = storePasswordEDT.getText().toString();
        phone = storePhoneEDT.getText().toString();
        email = storeEmailEDT.getText().toString();
        type = (String) spinner.getSelectedItem();
        Log.i("reg", "onClick: ");
        presenter.onRegisterClick(name, phone, address, email, type, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void InvalidName() {
        storeNameEDT.setError("invalid name");
    }

    @Override
    public void InvalidPhone() {
        storePhoneEDT.setError("invalid phone");
    }

    @Override
    public void InvalidEmail() {
        storeEmailEDT.setError("invalid mail address");
    }

    @Override
    public void InvalidPassword() {
        storePasswordEDT.setError("invalid password");
    }

    @Override
    public void InvalidAddress() {
        storeAddressEDT.setError("invalid Location Address");
    }

    @Override
    public void showConfirmDialog() {
        awesomeNoticeDialog.setNoticeButtonClick(new Closure() {
            @Override
            public void exec() {
               presenter.registerConfirm();
            }
        }).show();
    }

    private AwesomeNoticeDialog getDialog() {
        return new AwesomeNoticeDialog(this)
                .setTitle(R.string.app_name)
                .setMessage(R.string.app_name)
                .setColoredCircle(R.color.dialogNoticeBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_notice, R.color.white)
                .setCancelable(true)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setButtonBackgroundColor(R.color.dialogNoticeBackgroundColor)
                .setButtonText(getString(R.string.dialog_ok_button));

    }
}
