package com.demo.amt.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.demo.amt.operation.FileProvider7;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main2Activity.this,Main4Activity.class);
//                intent.setClassName("com.sh.util", "com.sh.util.MainActivity");
//                intent.putExtra("external","http://www.baidu.com?a=12$b=111");
//                intent.putExtra("packageName","com.sh.util");
                startActivity(intent);

                startApp();
            }
        });
    }

    private void startApp() {


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Main2Activity.this,Main8Activity.class);
//                intent.setClassName("com.sh.util", "com.sh.util.MainActivity");
//                intent.putExtra("external","http://www.baidu.com?a=12$b=111");
//                intent.putExtra("packageName","com.sh.util");
                startActivity(intent);
            }
        },2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "可见", Toast.LENGTH_SHORT).show();
    }
}
