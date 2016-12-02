package com.kenos.group_face_2_face.widget.keybroad;



import android.content.Context;
import android.os.Vibrator;



public class KeyBoardPromptUtil {
    private Context context;
    private static KeyBoardPromptUtil instance;

    public static KeyBoardPromptUtil getInstance(Context context) {
        if (instance == null) {
            instance = new KeyBoardPromptUtil(context);
        }
        return instance;
    }

    public KeyBoardPromptUtil(Context context) {
        this.context = context;
    }

    /**
     * 声音和震动提示用户点击事件
     */
    public void promptClick() {
            // 短暂震动
            Vibrator vibrator = (Vibrator) context
                    .getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(50);
        }
}
