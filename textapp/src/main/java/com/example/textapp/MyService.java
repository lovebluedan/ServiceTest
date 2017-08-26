package com.example.textapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private DownloadBinder mDownloadBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mDownloadBinder;
    }

    /**
     * 编写绑定的类 在onBind中返回；
     */
    class DownloadBinder extends android.os.Binder{
        public void startDownload(){
            Log.i(TAG, "startDownload: 开始下载！！！");
        }
        public void getProgess(){
            Log.i(TAG, "getProgess: 获取下载进度");
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 服务创建了！");
    }

    /**
     * 在不绑定服务时则是自动调用此方法开始服务
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand: 服务启动！");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 取消服务
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: 取消绑定！");
        return super.onUnbind(intent);
    }

    /**
     * 销毁服务 （如果同时调用了绑定服务跟开启服务，则需要重新调用停止服务跟绑定服务才会销毁服务）
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: 服务销毁了！");

    }
}
