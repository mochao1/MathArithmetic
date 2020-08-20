package com.demo.amt.demoapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main5Activity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn4, btn3, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;
    TextView textView;
    public static final String url = "http://124.224.242.243:9296/UserOrder?transactionID=spaj018120200703160657680223714627287175&SPID=spaj0181&returnUrl=http%3A%2F%2F124.224.222.217%3A9091%2Forder%2ForderReturn.html&INFO=4EJkgpgl6pAL1wmptnXi0tcX%2Bgw0z5p3NStVVLneors890SzknCSydNKX4I5gQiHH1WPoo%2FEdIgG%0Ay6e7bksOSu%2Fi%2FiQGdk6Qrk7r3pfCgM5Ti6aU5fL1y2bFHftsrY4CyNwuqrEXYwvBKzFqt42qhgXZ%0A9fPN0BTTscT2NsZcnrSdIiolEzIezL4l8DH1pJpsnEARmFoFbjiOwEQ%2FA2uaYatAAApy7b%2BNKKfn%0A5J6%2BkoXquFQuWSpuW%2Fvn3med2UuWJG%2FTjRfLgbnIxVpoFympYGO8e1Kf38Ep";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        textView = findViewById(R.id.modeName);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn12.setVisibility(View.GONE);
        textView.setText("盒子型号:" + Build.MODEL);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn1:
                intent = new Intent();
                intent.setAction("SKY_IPTV_LOAD_URL");
                intent.putExtra("loadURL", url);
                sendBroadcast(intent);
                return;
            case R.id.btn2:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction("stb.action.iptv");
                intent.putExtra("intentMsg", url);
                break;
            case R.id.btn3:
                intent = new Intent();
                intent.setAction("GX_SMART_IPTV_LOAD_URL");
                intent.putExtra("loadURL", url);
                sendBroadcast(intent);
                return;
            case R.id.btn4:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction("com.android.smart.terminal.iptv");
                intent.putExtra("intentMsg", url);
                break;
            case R.id.btn5:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.smart.terminal.iptv", "com.amt.app.IPTVActivity");
                intent.putExtra("intentMsg", url);
                break;
            case R.id.btn6:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction("com.android.ctc.iptv");
                intent.putExtra("intentMsg", url);
                break;
            case R.id.btn7:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction("CTC_IPTV_GUIDE");
                intent.putExtra("intentMsg", url);
                break;
            case R.id.btn8:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setAction("com.skyworth.iptv");
                intent.putExtra("loadURL", url);
                break;
            case R.id.btn9:
                intent = new Intent();
                intent.setAction("com.android.action.OPEN_ERL");
                intent.putExtra("jumpURL", url);
                intent.putExtra("playerFlag", "close");
                sendBroadcast(intent);
                return;
            case R.id.btn10:
                intent = new Intent();
                intent.setAction("com.android.action.START_IPTV");
                intent.putExtra("jumpURL", url);
                sendBroadcast(intent);
                return;
            case R.id.btn11:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.skyworth.iptv", "com.skyworth.iptv.Skiptv");
                intent.putExtra("loadURL", url);
                break;
            case R.id.btn12:

                break;
            default:
                break;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "跳转方式不对", Toast.LENGTH_SHORT).show();
        }

    }
}
