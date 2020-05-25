package com.example.administrator.lsn7_demo;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean){
                            ImageCache.getInstance().init(MainActivity.this,
                                    Environment.getExternalStorageDirectory()+"/dn");

                            ListView listView = findViewById(R.id.listView);
                            listView.setAdapter(new MyAdapter(MainActivity.this));
                        }
                    }
                });

        //假设是从网络上来的
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        //如果要复用，需要设计成异变
//        options.inMutable=true;
//        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.wyz_p,options);
//        for(int i=0;i<100;i++){
//            options.inBitmap=bitmap;
//            bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.wyz_p,options);
//        }

    }

    void i(Bitmap bitmap){
        Log.i("jett","图片"+bitmap.getWidth()+"x"+bitmap.getHeight()+" 内存大小是:"+bitmap.getByteCount());
    }

}
