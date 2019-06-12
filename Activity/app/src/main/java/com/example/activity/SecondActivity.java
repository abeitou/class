package com.example.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("test", "Create SecondActivity");
        setContentView(R.layout.activity_second);
        Button toast = (Button) findViewById(R.id.Toast);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不会pause
                //dialog();

                Intent intent = new Intent(SecondActivity.this, DialogActivity.class);
                startActivity(intent);

            }
        });



    }

    //不会pause
//    private void dialog(){
//        final ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        dialog.setMessage("正在加载中");
//        dialog.setMax(100);
//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            int progress = 0;
//
//            @Override
//            public void run() {
//                dialog.setProgress(progress += 5);
//                if (progress == 100) {
//                    timer.cancel();
//                }
//            }
//        }, 0, 1000);
//        dialog.show();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.e("test", "Start SecondActivity");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.e("test", "Resume SecondActivity");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.e("test", "Pause SecondActivity");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.e("test", "Stop SecondActivity");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.e("test", "Destroy SecondActivity");
    }
}
