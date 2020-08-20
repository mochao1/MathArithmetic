package com.demo.amt.demoapp;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author Messi.Mo
 * @date 2020/7/14
 * description:
 */

public class RotateDownPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;
    private boolean isH = false;

    public boolean isH() {
        return isH;
    }

    public void setH(boolean h) {
        isH = h;
    }

    public RotateDownPageTransformer(boolean isH) {
        this.isH = isH;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        if (position < -1) {
            // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(mMaxRotate * -1);
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());

        } else if (position <= 1) {
            // [-1,1]
            if (position < 0)
            //[0，-1]
            {

                if (isH) {
                    view.setPivotX(view.getWidth());
                    view.setPivotY(view.getHeight() * (0.5f + 0.5f * (-position)));
                } else {
                    view.setPivotX(view.getWidth() * (0.5f + 0.5f * (-position)));
                    view.setPivotY(view.getHeight());
                }

                view.setRotation(mMaxRotate * position);
            } else {
                //[1,0]
                if (isH) {
                    view.setPivotX(view.getWidth());
                    view.setPivotY(view.getHeight() * 0.5f * (1 - position));
                } else {
                    view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                    view.setPivotY(view.getHeight());
                }
                view.setRotation(mMaxRotate * position);
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(mMaxRotate);
            if (isH) {
                view.setPivotX(view.getWidth());
                view.setPivotY(0);
            } else {
                view.setPivotX(0);
                view.setPivotY(view.getHeight());
            }
        }
    }
}
