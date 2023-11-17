package com.bayutb123.dansapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    final String PREF_NAME = "sharedPreference";
    final String KEY_USERNAME = "username";
    final String KEY_PASSWORD = "password";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(String username, String password) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
