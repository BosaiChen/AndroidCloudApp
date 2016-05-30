package com.future.bosaichen.androidcloudapp;

import android.content.Context;

import com.firebase.client.Firebase;

public class FirebaseDB {
    private static final String FIREBASE_APP_ID = "project-4259215482031418109";
    private static final String BASE_URL = "https://" + FIREBASE_APP_ID + ".firebaseio.com/";

    public static final String FB_URL_APP_COUNT = "appCount";
    public static final String FB_URL_APP_LOCK = "lock";
    public static final String FB_URL_APP_UNLOCK = "unlock";

    private boolean initialized;

    public void init(final Context context) {
        if (!initialized) {
            Firebase.setAndroidContext(context);
            initialized = true;
        }
    }

    public static Firebase getInstance(String url) {
        return new Firebase(BASE_URL + url);
    }
}
