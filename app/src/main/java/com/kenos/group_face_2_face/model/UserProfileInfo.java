package com.kenos.group_face_2_face.model;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.Date;

public class UserProfileInfo  implements Parcelable {

    // 不可修改
    public final static String UN_CHANGEABLE = "unchangeable";

    // 可以修改
    public final static String CHANGEABLE = "changeable";

    private long cid;  //用户ID，服务器生成
    private String telNo; //电话号码
    private String subscribeUID; //UID
    private String nick; //昵称
    private String sex; //性别  0——男的  1——女的
    private String cootalkName; //CooTalk
    private String signature; //签名
    private String area; //地区
    private String address; // 地区
    private String picture; //头像
    private String subTelNos; //子号码列表
    private String remarkNick;  //备注昵称
    private String remarkTelno;  //备注号码
    private String remarkTab;  //备注标签
    private String status;    //200正常    201已拉黑

    private String password; //密码
    private String netId; //网络号
    private String countryCode; //国家码
    private String userName; //用户名
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private int topFlag;//聊天置顶
    private int lockFlag;//消息免打扰
    private String backGround = ""; //聊天背景


    // 是否修改CooTalk
    private String ext = UN_CHANGEABLE;

    public UserProfileInfo() {
    }

    protected UserProfileInfo(Parcel in) {
        cid = in.readLong();
        telNo = in.readString();
        subscribeUID = in.readString();
        nick = in.readString();
        sex = in.readString();
        cootalkName = in.readString();
        signature = in.readString();
        area = in.readString();
        address = in.readString();
        picture = in.readString();
        subTelNos = in.readString();
        remarkNick = in.readString();
        remarkTelno = in.readString();
        remarkTab = in.readString();
        status = in.readString();
        password = in.readString();
        netId = in.readString();
        countryCode = in.readString();
        userName = in.readString();
        ext = in.readString();
        topFlag = in.readInt();
        lockFlag = in.readInt();
        backGround = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(cid);
        dest.writeString(telNo);
        dest.writeString(subscribeUID);
        dest.writeString(nick);
        dest.writeString(sex);
        dest.writeString(cootalkName);
        dest.writeString(signature);
        dest.writeString(area);
        dest.writeString(address);
        dest.writeString(picture);
        dest.writeString(subTelNos);
        dest.writeString(remarkNick);
        dest.writeString(remarkTelno);
        dest.writeString(remarkTab);
        dest.writeString(status);
        dest.writeString(password);
        dest.writeString(netId);
        dest.writeString(countryCode);
        dest.writeString(userName);
        dest.writeString(ext);
        dest.writeInt(topFlag);
        dest.writeInt(lockFlag);
        dest.writeString(backGround);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserProfileInfo> CREATOR = new Creator<UserProfileInfo>() {
        @Override
        public UserProfileInfo createFromParcel(Parcel in) {
            return new UserProfileInfo(in);
        }

        @Override
        public UserProfileInfo[] newArray(int size) {
            return new UserProfileInfo[size];
        }
    };

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getSubscribeUID() {
        return subscribeUID;
    }

    public void setSubscribeUID(String subscribeUID) {
        this.subscribeUID = subscribeUID;
    }

    public String getCootalkName() {
        return cootalkName;
    }

    public void setCootalkName(String cootalkName) {
        this.cootalkName = cootalkName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSubTelNos() {
        return subTelNos;
    }

    public void setSubTelNos(String subTelNos) {
        this.subTelNos = subTelNos;
    }

    public String getRemarkNick() {
        return remarkNick;
    }

    public void setRemarkNick(String remarkNick) {
        this.remarkNick = remarkNick;
    }

    public String getRemarkTelno() {
        return remarkTelno;
    }

    public void setRemarkTelno(String remarkTelno) {
        this.remarkTelno = remarkTelno;
    }

    public String getRemarkTab() {
        return remarkTab;
    }

    public void setRemarkTab(String remarkTab) {
        this.remarkTab = remarkTab;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(int topFlag) {
        this.topFlag = topFlag;
    }

    public int getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(int lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getBackGround() {
        return backGround;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

    @Override
    public String toString() {
        return "UserProfileInfo{" +
                "cid=" + cid +
                ", telNo='" + telNo + '\'' +
                ", subscribeUID='" + subscribeUID + '\'' +
                ", nick='" + nick + '\'' +
                ", sex='" + sex + '\'' +
                ", cootalkName='" + cootalkName + '\'' +
                ", signature='" + signature + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", subTelNos='" + subTelNos + '\'' +
                ", remarkNick='" + remarkNick + '\'' +
                ", remarkTelno='" + remarkTelno + '\'' +
                ", remarkTab='" + remarkTab + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", netId='" + netId + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", topFlag=" + topFlag +
                ", lockFlag=" + lockFlag +
                ", backGround='" + backGround + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
//    @Override
//    public boolean equals(UserProfileInfo userProfileInfo) {
//        return userProfileInfo.getTelNo().equals(telNo);
//    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
