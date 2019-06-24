package com.swpu.funchat.datasource.net.api;

import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.model.repponse.ResponseMessageEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserApiService
 * @since 2019-06-20
 */
public interface UserApiService {

    @POST("sign/phone")
    @FormUrlEncoded
    Flowable<UserEntity> login(@Field("phone") String phone, @Field("password") String password);

    @POST("sign/up")
    @FormUrlEncoded
    Flowable<ResponseMessageEntity> register(@Field("phone") String phone, @Field("password") String password);

    @POST("user/update/nickname")
    @FormUrlEncoded
    Flowable<ResponseMessageEntity> updateNickname(@Field("id") long id, @Field("nickname") String nickname);
}
