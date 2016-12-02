package com.kenos.group_face_2_face.widget.keybroad;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kenos.group_face_2_face.R;


public class NumDisplayView extends LinearLayout {

    // 展示当前输入数字
    private TextView[] textViews;


    public NumDisplayView(Context context) {
        super(context);
        initView();
    }

    public NumDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_luck_num_display_page, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        this.addView(view);
        bindView(view);
    }

    /**
     * BindView
     *
     * @param view
     */
    private void bindView(View view) {
        textViews = new TextView[4];
        textViews[0] = (TextView) view.findViewById(R.id.view_display_luck_num_1);
        textViews[1] = (TextView) view.findViewById(R.id.view_display_luck_num_2);
        textViews[2] = (TextView) view.findViewById(R.id.view_display_luck_num_3);
        textViews[3] = (TextView) view.findViewById(R.id.view_display_luck_num_4);
    }

    /**
     * 更新LuckNum界面显示
     *
     * @param luckNum
     */
    public void updateLuckNum(String luckNum) {

        if (luckNum == null || luckNum.equals("")) {
            for (TextView textView : textViews) {
                textView.setText("-");
            }
            return;
        }
        for (TextView textView : textViews) {
            textView.setText("-");
        }
        char[] luckNumChar = luckNum.toCharArray();
        for (int i = 0; i < luckNumChar.length; i++) {
            textViews[i].setText(luckNumChar[i] + "");
        }

    }
}
