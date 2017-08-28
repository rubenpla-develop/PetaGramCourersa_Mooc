package com.rubenpla.develop.petagramcoursera;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rubenpla.develop.petagramcoursera.adapter.PageAdapter;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.MainActivityPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.MainActivityView;
import com.rubenpla.develop.petagramcoursera.view.ProfileFragment;
import com.rubenpla.develop.petagramcoursera.view.RecyclerViewFragment;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String KEY_EXTRA_NAME = "name";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        presenter = new MainActivityPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_contact:
                showSnackBarSuccesMessage("R.id.menu_contact");
                break;
            case R.id.menu_about:
                showSnackBarSuccesMessage("R.id.menu_about");
                break;
            case R.id.menu_account:
                Bundle bundle = new Bundle();
                bundle.putString("value1", "This is a string");

                Intent intent = new Intent(this, RegisterUserActivity.class);

                if (bundle != null) {
                    intent.putExtras(bundle);
                }

                startActivity(intent);
                break;
            case R.id.menu_notifications :
                try {
                    presenter.getProfileInfo();
                } catch (ClassNotFoundException|NoSuchMethodException|InvocationTargetException
                        |InstantiationException| IllegalAccessException e) {
                    e.printStackTrace();
                }
            default:
                break;
        }

        return true;
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new ProfileFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    @Override
    public void showSnackBarSuccesMessage(String succesMessage) {
        Snackbar.make(findViewById(android.R.id.content), R.string.snackbar_register_user_success,
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSnackBarErrorMessage(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), R.string.snackbar_register_user_error,
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showUserProfile() {

    }

    @Override
    public void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        presenter.registerUser();
    }
}
