package com.biller.enigma.root.fragment.pkg.account.fragment.pkg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biller.enigma.root.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AccountFragment extends Fragment implements AccountContract.AccountView {
    @BindView(R.id.account_name_tiedt)
    TextInputEditText accountNameTIEDT;

    @BindView(R.id.account_address_tiedt)
    TextInputEditText storeAddressTIEDT;

    @BindView(R.id.account_phone_numbertiedt)
    TextInputEditText phoneNoTIEDT;

    @BindView(R.id.account_mail_tiedt)
    TextInputEditText mailTIEDT;

    @BindView(R.id.account_store_type_tiedt)
    TextInputEditText storeTypeTIEDT;

    @BindView(R.id.account_wallet_amount_tiedt)
    TextInputEditText walletAmountTIEDT;

    private Unbinder unbinder;

    private AccountFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this,view);
        presenter = new AccountFragmentPresenter(this,getContext());
        presenter.onCreateViewListener();
        return view;
    }

    @Override
    public void setAccountValues(AccountDataModel accountModel) {
        accountNameTIEDT.setText(accountModel.getAccountName());
        storeAddressTIEDT.setText(accountModel.getAccountAddress());
        phoneNoTIEDT.setText(accountModel.getPhoneNumber());
        mailTIEDT.setText(accountModel.getMailAddress());
        storeTypeTIEDT.setText(accountModel.getStoreType());
        walletAmountTIEDT.setText(accountModel.getWalletAmountMMK());
    }
}
