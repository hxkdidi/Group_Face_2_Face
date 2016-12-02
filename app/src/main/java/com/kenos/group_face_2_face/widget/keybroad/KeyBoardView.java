package com.kenos.group_face_2_face.widget.keybroad;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.kenos.group_face_2_face.R;
import com.kenos.group_face_2_face.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardView extends LinearLayout implements View.OnClickListener {

    // 上下文
    private Context context;

    private TextView[] textViews;

    // 删除、添加、回车等按钮
    private TextView[] imageViews;

    // 保存所有输入的值
    private List<String> inputKeys = new ArrayList<>();

    // 点击回调事件
    private SoftKeyCallBack softKey;

    public KeyBoardView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public KeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    /**
     * View初始化
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_keyboard_page, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        this.addView(view);

        bindView(view);
    }

    /**
     * 注册回调
     *
     * @param mySoftKeys
     */
    public void registerCallBack(SoftKeyCallBack mySoftKeys) {
        this.softKey = mySoftKeys;
    }

    /**
     * FindViewById
     */
    private void bindView(View view) {
        textViews = new TextView[10];
        textViews[0] = (TextView) view.findViewById(R.id.key_1);
        textViews[1] = (TextView) view.findViewById(R.id.key_2);
        textViews[2] = (TextView) view.findViewById(R.id.key_3);
        textViews[3] = (TextView) view.findViewById(R.id.key_4);
        textViews[4] = (TextView) view.findViewById(R.id.key_5);
        textViews[5] = (TextView) view.findViewById(R.id.key_6);
        textViews[6] = (TextView) view.findViewById(R.id.key_7);
        textViews[7] = (TextView) view.findViewById(R.id.key_8);
        textViews[8] = (TextView) view.findViewById(R.id.key_9);
        textViews[9] = (TextView) view.findViewById(R.id.key_0);
        imageViews = new TextView[2];
        imageViews[0] = (TextView) view.findViewById(R.id.key_space);
        imageViews[1] = (TextView) view.findViewById(R.id.key_delete);

        for (int i = 0; i < 10; i++) {
            textViews[i].setOnClickListener(this);
        }

        for (int i = 0; i < 2; i++) {
            imageViews[i].setOnClickListener(this);
        }

        setOnClick();
    }

    /**
     * 添加点击事件
     */
    private void setOnClick() {
        imageViews[0].setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeAllKeyFromList();
                softKey.inputValues("", "");
                return false;
            }
        });
    }

    /**
     * 往List中添加
     *
     * @param str
     */
    private void addKey2List(String str) {
        if (inputKeys == null) {
            inputKeys = new ArrayList<>();
        }
        if (inputKeys.size() < 4)
            inputKeys.add(str);
        else
            ToastUtil.getInstance(getContext()).showToast(getResources().getString(R.string.activity_buy_luck_number_limit));

        softKey.inputValues(str, getAllInputStr());
    }

    /**
     * 删除最后一个值
     */
    private void delKey2List() {
        if (inputKeys == null || inputKeys.size() <= 0) {
            return;
        }
        inputKeys.remove(inputKeys.size() - 1);

        // 回调
        softKey.inputValues("", getAllInputStr());
    }

    /**
     * 获取所有的输入内容
     *
     * @return
     */
    private String getAllInputStr() {
        if (inputKeys == null || inputKeys.equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String str : inputKeys) {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * 清空输入
     */
    public void removeAllKeyFromList() {
        inputKeys.clear();
        // 回调
        softKey.inputValues("", getAllInputStr());
    }


    @Override
    public void onClick(View view) {
        KeyBoardPromptUtil.getInstance(context).promptClick();
        switch (view.getId()) {
            case R.id.key_1:
                addKey2List("1");
                break;
            case R.id.key_2:
                addKey2List("2");
                break;
            case R.id.key_3:
                addKey2List("3");
                break;
            case R.id.key_4:
                addKey2List("4");
                break;
            case R.id.key_5:
                addKey2List("5");
                break;
            case R.id.key_6:
                addKey2List("6");
                break;
            case R.id.key_7:
                addKey2List("7");
                break;
            case R.id.key_8:
                addKey2List("8");
                break;
            case R.id.key_9:
                addKey2List("9");
                break;
            case R.id.key_0:
                addKey2List("0");
                break;
            case R.id.key_space:
                softKey.ensure(getAllInputStr());
                break;
            case R.id.key_delete:
                delKey2List();
                break;
        }
    }
}
