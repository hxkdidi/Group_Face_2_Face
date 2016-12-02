package com.kenos.group_face_2_face.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kenos.group_face_2_face.R;
import com.kenos.group_face_2_face.group.GroupFace2faceActivity;
import com.kenos.group_face_2_face.model.FriendInfo;
import com.kenos.group_face_2_face.model.UserProfileInfo;
import com.kenos.group_face_2_face.widget.SimpleImageView;

import java.util.List;

public class GroupMemberAvatarAdapter extends BaseAdapter {
    private GroupFace2faceActivity context;
    private List<FriendInfo> list;

    public GroupMemberAvatarAdapter(List<FriendInfo> list, GroupFace2faceActivity context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_member_avatar, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_group_nick_name = (TextView) convertView.findViewById(R.id.tv_group_nick_name);
            viewHolder.iv_group_member_avatar = (SimpleImageView) convertView.findViewById(R.id.iv_group_member_avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FriendInfo friendInfo = list.get(position);
        UserProfileInfo info = friendInfo.getFriend();
        //昵称
        if (info != null && !TextUtils.isEmpty(info.getNick())) {
            viewHolder.tv_group_nick_name.setText(info.getNick());
        }
        //头像
        if (info != null && !TextUtils.isEmpty(info.getPicture())) {
            viewHolder.iv_group_member_avatar.setImageURI(info.getPicture());
        }
        return convertView;
    }


    class ViewHolder {
        TextView tv_group_nick_name;
        SimpleImageView iv_group_member_avatar;
    }
}
