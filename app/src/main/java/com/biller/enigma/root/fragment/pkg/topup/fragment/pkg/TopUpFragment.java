package com.biller.enigma.root.fragment.pkg.topup.fragment.pkg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeWarningDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.biller.enigma.root.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.biller.enigma.root.fragment.pkg.topup.fragment.pkg.TopUpContract.*;

public class TopUpFragment extends Fragment implements TopUpView{
    @BindView(R.id.top_up_phone_tiedt)
    TextInputEditText topUpPhoneTIEDT;

    @BindView(R.id.top_up_balance)
    TextInputEditText topUpBalanceTIEDT;

    @BindView(R.id.topUpConfirmBTN)
    Button topUpBTN;

    private Unbinder unbinder;

    private TopUpPresenter topUpPresenter;

    private String phone;
    private String balance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_up_fragment_layout,container,false);
        unbinder = ButterKnife.bind(this,view);
        topUpPresenter = new TopUpPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        topUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = topUpPhoneTIEDT.getText().toString();
                balance = topUpBalanceTIEDT.getText().toString();

                topUpPresenter.onConfirmClick(phone,balance);
            }
        });
    }

    @Override
    public void showConfirmDialog() {
        new AwesomeWarningDialog(getContext())
                .setTitle("Top Up to "+phone)
                .setMessage("Are you ready to transfer bill "+balance+" MMK "+"to "+phone)
                .setColoredCircle(R.color.dialogNoticeBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_notice, R.color.white)
                .setCancelable(true)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setButtonBackgroundColor(R.color.dialogNoticeBackgroundColor)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setWarningButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        topUpPresenter.onConfirm();
                    }
                })
                .show();
    }

    @Override
    public void showSuccessDialog() {

    }

    @Override
    public void showBalanceError() {
        new AwesomeErrorDialog(getContext())
                .setTitle("balance error")
                .setMessage("balance should be 1000,3000,10000 ...")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setErrorButtonClick(new Closure() {
                    @Override
                    public void exec() {

                    }
                })
                .show();
        topUpBalanceTIEDT.setError("invalid");
    }

    @Override
    public void showPhoneNoError() {
        new AwesomeErrorDialog(getContext())
                .setTitle("phone no error")
                .setMessage("your phone number is something wrong")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                .setButtonText(getString(R.string.dialog_ok_button))
                .setErrorButtonClick(new Closure() {
                    @Override
                    public void exec() {

                    }
                })
                .show();
        topUpPhoneTIEDT.setError("invalid phone");
    }

}
