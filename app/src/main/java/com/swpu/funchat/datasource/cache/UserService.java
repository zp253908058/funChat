package com.swpu.funchat.datasource.cache;

import com.swpu.funchat.model.UserEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserService
 * @since 2019/6/23
 */
public class UserService {

    private UserEntity mUserEntity;

    public UserEntity getUserEntity() {
        return mUserEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        mUserEntity = userEntity;
    }
}
