package com.swpu.funchat.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactEntity
 * @since 2019-05-14
 */
public class ContactEntity {

    private String name;      //名字
    private String avatar;    //头像路径

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
