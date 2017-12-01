package com.lodz.android.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhouL on 2017/12/1.
 */

public class TestService extends Service{

    public static final String TAG = "ServiceTag";

    private TestBinder mBinder = new TestBinder();

    int i = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() executed");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // 通过Binder和activity交互
    class TestBinder extends Binder {

        public void startDownload() {
            Log.i(TAG, "startDownload() executed");
        }

    }
}
