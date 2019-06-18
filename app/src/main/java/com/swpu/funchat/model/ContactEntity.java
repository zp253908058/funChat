package com.swpu.funchat.model;

import androidx.annotation.NonNull;

import com.swpu.funchat.util.PinyinUtils;

import java.io.Serializable;


/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactEntity
 * @since 2019-05-14
 */
public class ContactEntity implements Comparable<ContactEntity>, Serializable {

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

    @Override
    public int compareTo(@NonNull ContactEntity o) {
        if ('@' == (name.charAt(0)) || o.name.charAt(0) == '#') {
            return -1;
        } else if ('@' == (o.name.charAt(0)) || name.charAt(0) == '#') {
            return 1;
        } else {
            return PinyinUtils.getFirstSpell(name).compareTo(PinyinUtils.getFirstSpell(o.name));
        }
    }
}
