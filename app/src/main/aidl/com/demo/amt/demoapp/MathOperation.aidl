// Operation.aidl
package com.demo.amt.demoapp;

// Declare any non-default types here with import statements

interface MathOperation {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    float add(float one,float two);
    float sub(float one,float two);
    float mul(float one,float two);
    float div(float one,float two);
}
