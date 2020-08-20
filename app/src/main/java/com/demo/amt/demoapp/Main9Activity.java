package com.demo.amt.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plattysoft.leonids.ParticleSystem;

public class Main9Activity extends AppCompatActivity {

    private Button mButton0;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Main9Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main9);
        intView();
    }


    private void intView() {
        mButton0 = (Button) findViewById(R.id.btn0);
        mButton1 = (Button) findViewById(R.id.btn1);
        mButton2 = (Button) findViewById(R.id.btn2);
        mButton3 = (Button) findViewById(R.id.btn3);

//        new ParticleSystem(mActivity, 10, R.mipmap.star1, 10000)//第一个参数是上下文对象（context），第二个参数（最大粒子数目），第三个参数（粒子资源），第四个参数（粒子存留时间）
//                .setSpeedModuleAndAngleRange(0.0f, 0.0f, 0, 110)//第一个，第二个参数，是一个速度区间，这个速度是粒子的发射速度（如果没有设定重力，那么粒子的速度是恒定的，如果设定了重力，粒子的速度会根据重力方向改变），粒子会在这个速度区间随机，每个粒子都是独立的第三个，第四个参数，是指发射粒子的角度，0-360，的任意区间，这个参数的作用在于。我们操控发射粒子的方向，同样会在这个范围内随机
//                .setRotationSpeed(30)//图片元素的自转，粒子元素自身的旋转速度
//                .setAcceleration(0.0005f, 220)//重力加速度，第一个为重力加速度的值，第二个为重力加速度的方向，理论上讲我们这个设置任意方向为重力方向
//                .emit(0, 0, 1, 10000);//第一个参数，和第二个参数，是屏幕的像素坐标，粒子动画的发射点，第三个参数是每秒发射多少粒子第四个参数是持续发射多少秒时间到了之后，粒子会自动停止发射
//                .oneShot(mButton1, 400);//第一个参数是发射的位置，第二个参数是发射的粒子数量

        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ParticleSystem(mActivity, 50, R.mipmap.fulldiscount, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 150)
                        .setRotationSpeed(30)
                        .setAcceleration(0.0003f, 80)
                        .emit(100, -50, 5, 10000);

                new ParticleSystem(mActivity, 40, R.mipmap.red_envelope, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.4f, 0, 150)
                        .setRotationSpeed(30)
                        .setAcceleration(0.0003f, 80)
                        .emit(300, -50, 5, 10000);

                new ParticleSystem(mActivity, 30, R.mipmap.fulldiscount, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 150)
                        .setRotationSpeed(30)
                        .setAcceleration(0.0003f, 80)
                        .emit(600, -50, 5, 10000);

                new ParticleSystem(mActivity, 20, R.mipmap.red_envelope, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 150)
                        .setRotationSpeed(30)
                        .setAcceleration(0.0003f, 80)
                        .emit(900, -50, 5, 10000);
//                mButton0.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 180)
//                                .setRotationSpeed(30)
//                                .setAcceleration(0.0003f, 80)
//                                .emit(100, -50, 5, 10000);
//
//                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                .setSpeedModuleAndAngleRange(0.05f, 0.4f, 0, 180)
//                                .setRotationSpeed(30)
//                                .setAcceleration(0.0003f, 80)
//                                .emit(300, -50, 5, 10000);
//
//                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 180)
//                                .setRotationSpeed(30)
//                                .setAcceleration(0.0003f, 80)
//                                .emit(600, -50, 5, 10000);
//
//                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 360)
//                                .setRotationSpeed(30)
//                                .setAcceleration(0.0003f, 80)
//                                .emit(900, -50, 5, 10000);
//
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(800);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 180)
//                                                .setRotationSpeed(30)
//                                                .setAcceleration(0.0001f, 80)
//                                                .emit(100, -50, 5, 10000);
//
//                                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                                .setSpeedModuleAndAngleRange(0.05f, 0.4f, 0, 180)
//                                                .setRotationSpeed(30)
//                                                .setAcceleration(0.0001f, 80)
//                                                .emit(300, -50, 5, 10000);
//
//                                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 180)
//                                                .setRotationSpeed(30)
//                                                .setAcceleration(0.0001f, 80)
//                                                .emit(600, -50, 5, 10000);
//
//                                        new ParticleSystem(mActivity, 100, R.drawable.red_envelope, 10000)
//                                                .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 360)
//                                                .setRotationSpeed(30)
//                                                .setAcceleration(0.0001f, 80)
//                                                .emit(900, -50, 5, 10000);
//                                    }
//                                });
//                            }
//                        }).start();
//                    }
//                });
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ParticleSystem(mActivity, 1000, R.mipmap.flower0, 3000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 360)
                        .setRotationSpeed(30)
                        .setAcceleration(0, 90)
                        .oneShot(mButton1, 200);
            }
        });


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ParticleSystem(mActivity, 1000, R.mipmap.flower0, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(60)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
                new ParticleSystem(mActivity, 1000, R.mipmap.flower1, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(30)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
                new ParticleSystem(mActivity, 1000, R.mipmap.flower3, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(60)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
                new ParticleSystem(mActivity, 1000, R.mipmap.flower4, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(80)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
                new ParticleSystem(mActivity, 1000, R.mipmap.flower5, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(10)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
                new ParticleSystem(mActivity, 1000, R.mipmap.flower6, 10000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.2f, 0, 90)
                        .setRotationSpeed(50)
                        .setAcceleration(0.00005f, 90)
                        .emit(0, -100, 30, 10000);
            }
        });


        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ParticleSystem(mActivity, 1000, R.mipmap.flower1, 5000)
                        .setSpeedModuleAndAngleRange(0.05f, 0.05f, 0, 360)
                        .setRotationSpeed(30)
                        .setAcceleration(0, 90)
                        .oneShot(mButton3, 300);
            }
        });

    }
    public void viewVit() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mButton0.setVisibility(View.VISIBLE);
                            mButton1.setVisibility(View.VISIBLE);
                            mButton2.setVisibility(View.VISIBLE);
                            mButton3.setVisibility(View.VISIBLE);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

}
