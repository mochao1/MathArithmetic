package com.demo.amt.demoapp;

import android.os.RemoteException;

/**
 * @author Messi.Mo
 * @date 2020/8/19
 * description:
 */

public class OperationImpl extends MathOperation.Stub {

    @Override
    public float add(float one, float two) throws RemoteException {
        return one+two;
    }

    @Override
    public float sub(float one, float two) throws RemoteException {
        return one-two;
    }

    @Override
    public float mul(float one, float two) throws RemoteException {
        return one*two;
    }

    @Override
    public float div(float one, float two) throws RemoteException {
        return one/two;
    }
}
