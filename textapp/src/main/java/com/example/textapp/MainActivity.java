package com.example.textapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button mStartServiceBtn;
    private Button mStopServiceBtn;
    private Button mBindServiceBtn;
    private Button mUnBindServiceBtn;
    private Button mStartIntentServiceBtn;
    private MyService.DownloadBinder downloadBinder ;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            //要先将iBinder赋值给downloadBinder
            downloadBinder = (MyService.DownloadBinder) iBinder;
            Log.i(TAG, "onServiceConnected: 连接成功！！！");
            downloadBinder.startDownload();
            downloadBinder.getProgess();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartServiceBtn = (Button) findViewById(R.id.btn_start_server);
        mStopServiceBtn = (Button) findViewById(R.id.btn_stop_server);
        mBindServiceBtn = (Button) findViewById(R.id.btn_bind_server);
        mUnBindServiceBtn = (Button) findViewById(R.id.btn_unbind_server);
        mStartIntentServiceBtn = (Button) findViewById(R.id.btn_start_intentserver);

        mStartServiceBtn.setOnClickListener(this);
        mStopServiceBtn.setOnClickListener(this);
        mBindServiceBtn.setOnClickListener(this);
        mUnBindServiceBtn.setOnClickListener(this);
        mStartIntentServiceBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_server://单纯打开服务
                Intent intent = new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_server:
                Intent intent1 = new Intent(this,MyService.class);
                stopService(intent1);
                break;
            case R.id.btn_bind_server://绑定服务
                Intent intent2 = new Intent(this,MyService.class);
                bindService(intent2,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_server:
                Intent intent3 = new Intent(this,MyService.class);
                unbindService(serviceConnection);
                break;
            case R.id.btn_start_intentserver:
                // 打印主线程的id
                Log.d("MainActivity", "Thread id is " + Thread.currentThread(). getId());
                Intent intent4 = new Intent(this,MyIntentService.class);
                startService(intent4);
                break;
            default:
                break;
        }
    }



}
