package com.future.bosaichen.androidcloudapp;

import android.app.IntentService;
import android.content.Intent;

public class FirebaseDataChangeMonitor extends IntentService {
    public FirebaseDataChangeMonitor() {
        super("FirebaseDataChangeMonitor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
