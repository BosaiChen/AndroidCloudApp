package com.future.bosaichen.androidcloudapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String FIREBASE_APP_ID = "project-4259215482031418109";
    private static final String URI_APP_LOCK_COUNT = "applockcount";

    private long mTotalLock = 0;
    private long mTotalUnlock = 0;
    private double mLockPercentage = 0;

    private TextView mTotalLockTV;
    private TextView mTotalUnlockTV;
    private TextView mTotalLockPercentageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTotalLockTV = (TextView) findViewById(R.id.lock_count);
        mTotalUnlockTV = (TextView) findViewById(R.id.unlock_count);
        mTotalLockPercentageTV = (TextView) findViewById(R.id.lock_percentage);

        Button lockFB = (Button) findViewById(R.id.lock_facebook);
        Button unlockFB = (Button) findViewById(R.id.unlock_facebook);

        lockFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementAppLockCounter("facebook");
            }
        });

        unlockFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementAppNotLockCounter("facebook");
            }
        });

        FirebaseDB.getInstance(FirebaseDB.FB_URL_APP_COUNT + "/" + "facebook").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Toast.makeText(getApplicationContext(),"" + (Long)dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                long value = (Long) dataSnapshot.getValue();
                if (dataSnapshot.getKey().equalsIgnoreCase(FirebaseDB.FB_URL_APP_LOCK)) {
                    mTotalLock = value;
                    mTotalLockTV.setText("Total Facebook lock :" + mTotalLock);
                } else if (dataSnapshot.getKey().equalsIgnoreCase(FirebaseDB.FB_URL_APP_UNLOCK)) {
                    mTotalUnlock = value;
                    mTotalUnlockTV.setText("Total Facebook unlock :" + mTotalUnlock);
                }
                mLockPercentage = (double)mTotalLock / (mTotalLock + mTotalUnlock);
                mTotalLockPercentageTV.setText(Math.floor(mLockPercentage * 1000)/1000*100 + "% users lock Facebook");
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
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCounts();
    }

    private void incrementAppLockCounter(String appName) {
        FirebaseDB.getInstance(FirebaseDB.FB_URL_APP_COUNT + "/" + appName + "/" + FirebaseDB.FB_URL_APP_LOCK)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData currentData) {
                        if (currentData.getValue() == null) {
                            currentData.setValue(1);
                        } else {
                            currentData.setValue((Long) currentData.getValue() + 1);
                        }

                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
//                        if (firebaseError != null) {
//                            Toast.makeText(getApplicationContext(), "Firebase counter increment failed.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Firebase counter increment succeeded.", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
    }

    private void incrementAppNotLockCounter(String appName) {
        FirebaseDB.getInstance(FirebaseDB.FB_URL_APP_COUNT + "/" + appName + "/" + FirebaseDB.FB_URL_APP_UNLOCK)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData currentData) {
                        if (currentData.getValue() == null) {
                            currentData.setValue(1);
                        } else {
                            currentData.setValue((Long) currentData.getValue() + 1);
                        }

                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
//                        if (firebaseError != null) {
//                            Toast.makeText(getApplicationContext(), "Firebase counter increment failed.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Firebase counter increment succeeded.", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
    }

    private void updateCounts() {
        FirebaseDB.getInstance(FirebaseDB.FB_URL_APP_COUNT + "/facebook/" + FirebaseDB.FB_URL_APP_LOCK).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = (Long) dataSnapshot.getValue();
                mTotalLock = count;
                mTotalLockTV.setText("Total Facebook Lock " + mTotalLock);
                mLockPercentage = (double)mTotalLock / (mTotalLock + mTotalUnlock);
                mTotalLockPercentageTV.setText(Math.floor(mLockPercentage * 1000)/1000*100 + "% users lock Facebook");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        FirebaseDB.getInstance(FirebaseDB.FB_URL_APP_COUNT + "/facebook/" + FirebaseDB.FB_URL_APP_UNLOCK).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = (Long) dataSnapshot.getValue();
                mTotalUnlock = count;
                mTotalUnlockTV.setText("Total Facebook Unlock " + mTotalUnlock);
                mLockPercentage = (double)mTotalLock / (mTotalLock + mTotalUnlock);
                mTotalLockPercentageTV.setText(Math.floor(mLockPercentage * 1000)/1000*100 + "% users lock Facebook");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
