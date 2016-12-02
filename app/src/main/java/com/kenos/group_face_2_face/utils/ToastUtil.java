package com.kenos.group_face_2_face.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kenos.group_face_2_face.R;


public class ToastUtil {

    // 单例
    private static ToastUtil instance;

    // 上下文
    private static Context myContext;

    // 显示
    private Toast toast;

    public static ToastUtil getInstance(Context context) {
        myContext = context;
        if (instance == null)
            instance = new ToastUtil();
        return instance;
    }

    /**
     * 调用系统Toast展示信息
     *
     * @param message
     */
    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = new Toast(myContext.getApplicationContext());
        LayoutInflater inflate = (LayoutInflater) myContext.getApplicationContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.cootalk_custom_view_toast, null);
        TextView toast_tv = (TextView) view.findViewById(R.id.cootalk_toast_content);
        toast_tv.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
