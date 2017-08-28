package com.rubenpla.develop.petagramcoursera;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.IRegisterUserActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterUserActivity extends AppCompatActivity implements IRegisterUserActivityPresenter {
    @BindView(R.id.btnAcceder)
    Button btnSave;
    @BindView(R.id.etCuenta)
    TextInputEditText tietAccount;
    private Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        ButterKnife.bind(this);


        res = getResources();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserAccount();
            }
        });

    }

    @Override
    public void setUserAccount() {
        //TODO register user
    }
}
