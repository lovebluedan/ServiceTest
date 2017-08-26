package com.example.textapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.nfc.Tag;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

/**
 *
 *
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }


    /**
     * 子线程中运行
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Thread id is " + Thread.currentThread(). getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("MyIntentService", "onDestroy executed");
        Toast.makeText(this,"MyIntentService销毁！",Toast.LENGTH_LONG).show();
    }
}
