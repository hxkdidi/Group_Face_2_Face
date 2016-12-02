package com.kenos.group_face_2_face.group;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;


import com.kenos.group_face_2_face.R;
import com.kenos.group_face_2_face.adapter.GroupMemberAvatarAdapter;
import com.kenos.group_face_2_face.model.FriendInfo;
import com.kenos.group_face_2_face.model.UserProfileInfo;
import com.kenos.group_face_2_face.utils.ToastUtil;
import com.kenos.group_face_2_face.widget.keybroad.KeyBoardView;
import com.kenos.group_face_2_face.widget.keybroad.NumDisplayView;
import com.kenos.group_face_2_face.widget.keybroad.SoftKeyCallBack;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class GroupFace2faceActivity extends FragmentActivity implements
        AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = "GroupFace2faceActivity";
    private GroupFace2faceActivity mActivity;
    private GridView gridView;
    private List<FriendInfo> datas = new ArrayList<>();
    private GroupMemberAvatarAdapter groupMemberAvatarAdapter;
    private LinearLayout ll_join_group_chat;
    private LinearLayout ll_success_input;
    private LinearLayout ll_input_number;
    private LinearLayout ll_input_tips;
    private SoftKeyCallBackImpl callBackImpl;
    private KeyBoardView keyBoardView;
    private NumDisplayView displayView;
    private LinearLayout keyBoardLinear;
    // 当前展示
    private static final int SHOW_SELECT = 10010;
    private static final int SHOW_KEY = 10011;

    // 显示和隐藏动画
    private Animation mHiddenAction, mShowAction;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_face_2_face);
        initView();
        setListener();
    }


    protected void initView() {
        mActivity = this;
        gridView = (GridView) findViewById(R.id.gv_group_avatar);
        ll_join_group_chat = (LinearLayout) findViewById(R.id.ll_join_group_chat);
        ll_success_input = (LinearLayout) findViewById(R.id.ll_success_input);
        ll_input_number = (LinearLayout) findViewById(R.id.ll_input_number);
        ll_input_tips = (LinearLayout) findViewById(R.id.ll_input_tips);


        for (int i = 0; i < 3; i++) {
            FriendInfo friendInfo = new FriendInfo();
            UserProfileInfo userProfileInfo = new UserProfileInfo();
            userProfileInfo.setNick("罗一笑" + i);
            userProfileInfo.setPicture("http://p1.so.qhmsg.com/sdr/675_1080_/t01e2df2f97c45e009b.jpg");
            friendInfo.setFriend(userProfileInfo);
            datas.add(friendInfo);
        }

        groupMemberAvatarAdapter = new GroupMemberAvatarAdapter(datas, mActivity);
        gridView.setAdapter(groupMemberAvatarAdapter);

        callBackImpl = new SoftKeyCallBackImpl();
        keyBoardView = (KeyBoardView) findViewById(R.id.view_buy_input_key_broad);
        keyBoardView.registerCallBack(callBackImpl);
        displayView = (NumDisplayView) findViewById(R.id.view_buy_input_luck_num_display);
        keyBoardLinear = (LinearLayout) findViewById(R.id.activity_main_key_linear);

        initAnimation();

        updatePage(SHOW_KEY);
    }

    protected void setListener() {
        ll_join_group_chat.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.getInstance(mActivity).showToast(" position = " + position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_join_group_chat:
                ToastUtil.getInstance(mActivity).showToast("加入群聊");
                break;
            default:
                break;
        }
    }

    /**
     * 内部类，实现键盘回调接口
     */
    private class SoftKeyCallBackImpl implements SoftKeyCallBack {
        @Override
        public void inputValues(String key, String allKey) {
            displayView.updateLuckNum(allKey);
            checkInput(allKey);
        }

        @Override
        public void ensure(String allKey) {
            checkInput(allKey);
        }
    }

    /**
     * 输入校验
     *
     * @param allKey
     */
    private void checkInput(String allKey) {
        if (allKey.equals("2236")) {
            ToastUtil.getInstance(mActivity).showToast(getResources().getString(R.string.activity_buy_luck_number_success));
            updatePage(SHOW_SELECT);
        }
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        mShowAction = AnimationUtils.loadAnimation(this, R.anim.anim_bottom_in);
        mHiddenAction = AnimationUtils.loadAnimation(this, R.anim.anim_bottom_out);
    }

    /**
     * 更新界面
     *
     * @param showType
     */
    private void updatePage(int showType) {
        if (showType == SHOW_KEY) {
            keyBoardLinear.setVisibility(VISIBLE);
            keyBoardLinear.startAnimation(mShowAction);
            ll_input_number.setVisibility(VISIBLE);
            ll_input_tips.setVisibility(VISIBLE);
            ll_join_group_chat.setVisibility(GONE);
            ll_success_input.setVisibility(GONE);
            gridView.setVisibility(GONE);
        } else {
            ll_input_number.setVisibility(GONE);
            ll_input_tips.setVisibility(GONE);
            ll_join_group_chat.setVisibility(VISIBLE);
            ll_success_input.setVisibility(VISIBLE);
            gridView.setVisibility(VISIBLE);
            keyBoardLinear.startAnimation(mHiddenAction);
            keyBoardLinear.setVisibility(GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (keyBoardLinear.getVisibility() == VISIBLE) {
                keyBoardLinear.setVisibility(GONE);
                return true;
            } else {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
