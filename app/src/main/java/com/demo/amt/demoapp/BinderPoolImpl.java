package com.demo.amt.demoapp;

import android.os.IBinder;
import android.os.RemoteException;

public class BinderPoolImpl extends IBinderPool.Stub {
    private static final int NONE_CODE = 99;
    private static final int ENCRYPTION_CODE = 100;
    private static final int MATH_OPERATION_CODE = 101;

    public BinderPoolImpl() {
        super();
    }

    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder iBinder = null;
        switch (binderCode) {
            case ENCRYPTION_CODE:
                iBinder = new EncryptionImpl();
                break;
            case MATH_OPERATION_CODE:
                iBinder = new OperationImpl();
                break;
            default:
                break;
        }
        return iBinder;
    }
}
