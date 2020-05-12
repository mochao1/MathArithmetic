package com.demo.amt.operation;

/**
 * @author Messi.Mo
 * @date 2020/5/12
 * description:
 */

public class MathArithmetic {

    /**
     * 加法
     *
     * @param number
     * @return
     */
    public static float add(float... number) {
        float result = 0;
        for (float n : number) {
            result += n;
        }
        return result;
    }

    /**
     * 减法
     *
     * @param o
     * @param t
     * @return
     */
    public static float subtraction(float o, float t) {
        return o - t;
    }

    /**
     * 乘法
     *
     * @param number
     * @return
     */
    public static float multiplication(float... number) {
        float result = 0;
        for (float n : number) {
            result *= n;
        }
        return result;
    }

    /**
     * 除法
     *
     * @param o
     * @param t
     * @return
     */
    public static float division(float o, float t) {
        return o / t;
    }

    /**
     * 求余
     *
     * @param o
     * @param t
     * @return
     */
    public static int complementation(int o, int t) {
        return o % t;
    }
}
