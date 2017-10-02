package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.model.SearchByUserModel;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.RegisterUserActivityPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRegisterUserActivityView;
import com.rubenpla.develop.petagramcoursera.util.preferences.PreferencesKeys;
import com.rubenpla.develop.petagramcoursera.view.activity.PetagramActivity;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterUserActivity extends PetagramActivity implements IRegisterUserActivityView {
   
    @BindView(R.id.btn_save_user) Button btnSave;
    @BindView(R.id.user_switch_edit_text) TextInputEditText accountUserTxtField;

    private RegisterUserActivityPresenter presenter;
    private ProfileInfo profileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        ButterKnife.bind(this);

        presenter = new RegisterUserActivityPresenter(this);
    }

    @OnClick(R.id.btn_save_user)
    void onClickRegisterUser(View view)  {
        try {
            presenter.setUserAccount(accountUserTxtField.getText().toString());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
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
    public void switchToNewUserAccount(SearchByUserModel user) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        ProfileInfo profileInfo = new ProfileInfo(user);
        getPreferencesAgent().saveProfile(PreferencesKeys.KEY_POST_CURRENT_USER, profileInfo);
        presenter.registerUser(profileInfo);
        //Launch intent to start activity MainActivity and refresh content with new user
        // registered
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
