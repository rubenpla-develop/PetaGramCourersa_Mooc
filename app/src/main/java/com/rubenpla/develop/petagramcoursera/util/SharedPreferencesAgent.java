package com.rubenpla.develop.petagramcoursera.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesAgent {

    private final String fileName = "luck2prefagent";
    private final String defaultUser = "rubenpla.develop";

    private Context context;
    private static SharedPreferencesAgent preferenceAgentInstance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferenceEditor;

    public SharedPreferencesAgent(Context context) {
        this.context = context;
        setSharedPreferencesAgent();
    }

    public static SharedPreferencesAgent getInstance(Context context) {
        if (preferenceAgentInstance == null) {
            preferenceAgentInstance = new SharedPreferencesAgent(context);
        }

        return preferenceAgentInstance;
    }

    private void setSharedPreferencesAgent() {
        preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();
    }

    public String getCurrentUserId(String prefKey) {
        String resultPreference = preferences.getString(prefKey, defaultUser);

        return resultPreference;
    }

    public void setCurrentUserId(String prefKey, String userId) {
        preferenceEditor.putString(prefKey, userId);
    }
}
