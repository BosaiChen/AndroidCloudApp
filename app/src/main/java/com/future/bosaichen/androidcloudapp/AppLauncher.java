package com.future.bosaichen.androidcloudapp;

import android.app.Application;

public class AppLauncher extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        new FirebaseDB().init(getApplicationContext());

        /*new FirebaseDB().getInstance(FirebaseDB.FB_URL_APP_COUNT).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
    }
}
