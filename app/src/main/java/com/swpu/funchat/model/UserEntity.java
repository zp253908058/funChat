package com.swpu.funchat.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserEntity
 * @since 2019-05-30
 */
public class UserEntity {
    private long userId;
    private String nickname;
    private String avatar;
    private int sex;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                '}';
    }
}