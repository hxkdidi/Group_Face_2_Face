package com.kenos.group_face_2_face.model;


public class FriendInfo {
    private UserProfileInfo friend;

    public UserProfileInfo getFriend() {
        return friend;
    }

    public void setFriend(UserProfileInfo friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "SearchFriendInfo{" +
                "friend=" + friend +
                '}';
    }
}
