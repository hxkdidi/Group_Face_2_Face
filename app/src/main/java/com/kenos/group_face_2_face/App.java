package com.kenos.group_face_2_face;

import android.app.Application;

import com.kenos.group_face_2_face.widget.SimpleImageView;

/**
 * User: hxk(huangxikang@520bangxin.cn)
 * Date: 2016-12-02
 * Time: 16:02
 * Description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // SimpleImageView 初始化
        SimpleImageView.initialize(this);
    }
}
