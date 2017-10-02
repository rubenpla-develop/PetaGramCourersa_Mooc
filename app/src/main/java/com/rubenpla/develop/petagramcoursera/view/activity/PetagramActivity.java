package com.rubenpla.develop.petagramcoursera.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rubenpla.develop.petagramcoursera.util.preferences.SharedPreferencesAgent;

public class PetagramActivity extends AppCompatActivity {

    protected SharedPreferencesAgent preferencesAgent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadPreferencesAgent(this);
    }

    private void loadPreferencesAgent(Context context) {
        preferencesAgent = new SharedPreferencesAgent(context);
    }

    public SharedPreferencesAgent getPreferencesAgent() {
        return preferencesAgent;
    }
}
