package com.swpu.funchat.datasource.net.api;

import com.swpu.funchat.model.UserEntity;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserService
 * @since 2019-06-20
 */
public interface UserService {

    @POST("sign/phone")
    @FormUrlEncoded
    Flowable<UserEntity> login(@Field("phone") String phone, @Field("password") String password);

    @GET("user/{username}")
    Flowable<UserEntity> getUserInfo(@Path("username") long id);
}
