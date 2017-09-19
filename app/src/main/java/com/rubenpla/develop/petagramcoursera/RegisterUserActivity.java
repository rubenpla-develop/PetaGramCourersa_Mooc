package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.RegisterUserActivityPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRegisterUserActivityView;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserActivity extends AppCompatActivity implements IRegisterUserActivityView {
   
    @BindView(R.id.btnAcceder) Button btnSave;
    @BindView(R.id.etCuenta) TextInputEditText accountUserTxtField;

    private RegisterUserActivityPresenter presenter;
    private ProfileInfo profileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAcceder)
    void registeruser(View v) {
        presenter.setUserAccount(accountUserTxtField.getText().toString());
    }

    @Override
    public void showSnackBarSuccesMessage(String succesMessage) {
        Snackbar.make(findViewById(android.R.id.content), succesMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSnackBarErrorMessage(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void switchToNewUserAccount(String userId) {
        //Presenter : take user profile from instagram api

    }

    @Override
    public void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {

        presenter.registerUser();

    }

    @Override
    public void finishAndReturn() {
        this.finish();
    }
}
