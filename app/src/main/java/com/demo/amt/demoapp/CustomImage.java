package com.demo.amt.demoapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Messi.Mo
 * @date 2020/7/8
 * description:
 */

public class CustomImage extends AppCompatImageView {
    ColorMatrix colorMatrix;
    Paint paint=new Paint();
    public CustomImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        colorMatrix=new ColorMatrix();
        colorMatrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(null, paint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
