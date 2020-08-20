package com.example.amt.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.demo.amt.demoapp.IBinderPool;

import java.util.concurrent.CountDownLatch;

/**
 * @author Messi.Mo
 * @date 2020/8/19
 * description: binder连接池工具
 */

public class BinderPool {
    public static final int NONE_CODE = 99;
    public static final int ENCRYPTION_CODE = 100;
    public static final int MATH_OPERATION_CODE = 101;
    private static final String TAG = BinderPool.class.getSimpleName();
    private Context mContext;
    private IBinderPool iBinderPool;
    private static volatile BinderPool binderPool;
    private CountDownLatch binderPoolCountDownLatch;
    private boolean isConnect;
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binder is die");
            iBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            iBinderPool = null;
            connectBinderPoolService();
        }
    };


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                iBinderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            binderPoolCountDownLatch.countDown();
            Log.d(TAG, "binder is 连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "binder is 失去连接");
        }
    };

    private BinderPool(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (binderPool == null) {
            synchronized (BinderPool.class) {
                if (binderPool == null) {
                    binderPool = new BinderPool(context);
                }
            }
        }
        return binderPool;
    }

    /**
     * 连接binder连接池
     */
    private synchronized void connectBinderPoolService() {
        binderPoolCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setClassName("com.demo.amt.demoapp", "com.demo.amt.demoapp.BinderPoolService");
        isConnect = mContext.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        try {
            binderPoolCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询业务binder
     * @param binderCode
     * @return
     */
    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        try {
            if (iBinderPool != null) {
                binder = iBinderPool.queryBinder(binderCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binder;
    }

    public boolean isConnect() {
        return isConnect;
    }

    /**
     * 解绑服务
     */
    public void unbindService() {
        if (isConnect) {
            mContext.unbindService(serviceConnection);
        }
    }


}
