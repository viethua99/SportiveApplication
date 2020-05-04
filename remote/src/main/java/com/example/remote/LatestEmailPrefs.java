package com.example.remote;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Viet Hua on 05/04/2020.
 */
public class LatestEmailPrefs {
    public static final String KEY_LATEST_EMAIL = "KEY_LATEST_EMAIL";
    private static SharedPreferences sharedPreferences;

    public static void initSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("email_list", Context.MODE_PRIVATE);

    }

    public static SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public static void setLatestEmail(String email) {
        getEditor().putString(KEY_LATEST_EMAIL, email).commit();
    }

    public static String getLatestEmail() {
        return sharedPreferences.getString(KEY_LATEST_EMAIL, "");
    }

    public static void clearEditor() {
        getEditor().clear().commit();
    }

}
