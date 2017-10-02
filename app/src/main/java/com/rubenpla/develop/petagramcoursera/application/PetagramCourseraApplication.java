package com.rubenpla.develop.petagramcoursera.application;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.petagramcoursera.util.preferences.SharedPreferencesAgent;

public class PetagramCourseraApplication extends Application {

    private static SharedPreferencesAgent sharedPreferencesAgent;
    private static PetagramCourseraApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initSharedPreferencesAgent(this);
    }

    private void initSharedPreferencesAgent(Context context) {
        sharedPreferencesAgent = new SharedPreferencesAgent(context);
    }

    public static PetagramCourseraApplication getInstance() {
        return instance;
    }

    public static SharedPreferencesAgent getSharedPreferencesAgent() {
        return sharedPreferencesAgent;
    }
}
