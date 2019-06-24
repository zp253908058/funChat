package com.swpu.funchat.vm;

import androidx.annotation.StringDef;
import androidx.lifecycle.MutableLiveData;

import com.swpu.funchat.datasource.net.api.UserApiService;
import com.swpu.funchat.datasource.net.support.Network;
import com.swpu.funchat.datasource.net.support.ResponseSubscriber;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.model.repponse.ResponseMessageEntity;
import com.swpu.funchat.util.MD5;
import com.swpu.funchat.util.Toaster;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SignViewModel
 * @since 2019-06-16
 */
public class SignViewModel extends ResponseViewModel {

    public final static String KEY_LOGIN_RESPONSE_LISTENER = "login_response_listener";
    public final static String KEY_REGISTER_RESPONSE_LISTENER = "register_response_listener";

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @StringDef({KEY_LOGIN_RESPONSE_LISTENER, KEY_REGISTER_RESPONSE_LISTENER})
    @interface SignResponseListener {
    }

    private final MutableLiveData<Integer> mLoginSuccessObservable;
    private final MutableLiveData<Integer> mRegisterSuccessObservable;
    private final MutableLiveData<UserEntity> mUserObservable;

    private UserApiService mUserApiService;

    public SignViewModel() {
        mLoginSuccessObservable = new MutableLiveData<>();
        mLoginSuccessObservable.setValue(null);
        mRegisterSuccessObservable = new MutableLiveData<>();
        mRegisterSuccessObservable.setValue(null);

        mUserObservable = new MutableLiveData<>();
        mUserObservable.setValue(null);

        mUserApiService = Network.getInstance().getGeneralService(UserApiService.class);
    }

    public MutableLiveData<Integer> getRegisterSuccessObservable() {
        return mRegisterSuccessObservable;
    }

    public MutableLiveData<Integer> getLoginSuccessObservable() {
        return mLoginSuccessObservable;
    }

    public MutableLiveData<UserEntity> getUserObservable() {
        return mUserObservable;
    }

    @Override
    public void addOnResponseListener(@SignResponseListener String key, OnResponseListener onResponseListener) {
        super.addOnResponseListener(key, onResponseListener);
    }

    public void login(String username, String password) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.baidu.com")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UserApiService service = retrofit.create(UserApiService.class);
//        service.login(username, password);

        String md5Password = MD5.md5Decode(password);
        Flowable<UserEntity> login = mUserApiService.login(username, md5Password);
        login.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<UserEntity>() {
            @Override
            public void onNext(UserEntity userEntity) {
                mLoginSuccessObservable.postValue(200);
                mUserObservable.postValue(userEntity);
            }

            @Override
            public void onComplete() {
                OnResponseListener listener = getOnResponseListener(KEY_LOGIN_RESPONSE_LISTENER);
                if (listener != null) {
                    listener.onResponse();
                }
            }
        });
    }

    public void register(String username, String password) {
        String md5Password = MD5.md5Decode(password);
        Flowable<ResponseMessageEntity> register = mUserApiService.register(username, md5Password);
        register.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponseMessageEntity>() {
                    @Override
                    public void onNext(ResponseMessageEntity responseMessageEntity) {
                        Toaster.showToast(responseMessageEntity.getMsg());
                        mRegisterSuccessObservable.postValue(200);
                    }

                    @Override
                    public void onComplete() {
                        OnResponseListener listener = getOnResponseListener(KEY_REGISTER_RESPONSE_LISTENER);
                        if (listener != null) {
                            listener.onResponse();
                        }
                    }
                });
    }
}
