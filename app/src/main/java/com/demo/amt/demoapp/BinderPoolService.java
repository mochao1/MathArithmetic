package com.demo.amt.demoapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderPoolService extends Service {
    private static final String TAG = BinderPoolService.class.getSimpleName();
    private Binder mBinder = new BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
