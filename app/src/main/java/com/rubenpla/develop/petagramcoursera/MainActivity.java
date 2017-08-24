package com.rubenpla.develop.petagramcoursera;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        presenter = new MainActivityPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    //NEW
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_contact:

                break;
            case R.id.menu_about:

                break;
            case R.id.menu_account:
                Toast.makeText(this, "CLICK!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_notifications :
                try {
                    presenter.registerUser();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
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
    public void showUserProfile() {

    }

    @Override
    public void registerUser() {

    }
}
