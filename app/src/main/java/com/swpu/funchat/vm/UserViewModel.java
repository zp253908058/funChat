package com.swpu.funchat.vm;

import androidx.annotation.StringDef;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.swpu.funchat.datasource.net.api.UserApiService;
import com.swpu.funchat.datasource.net.support.Network;
import com.swpu.funchat.datasource.net.support.ResponseSubscriber;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.model.repponse.ResponseMessageEntity;

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
 * @see UserViewModel
 * @since 2019-05-17
 */
public class UserViewModel extends ResponseViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    public final static String KEY_UPDATE_NICKNAME_RESPONSE_LISTENER = "update_nickname_response_listener";

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @StringDef({KEY_UPDATE_NICKNAME_RESPONSE_LISTENER})
    @interface UserResponseListener {
    }

    private MutableLiveData<UserEntity> mUserLiveData;
    private MediatorLiveData<UserEntity> mUserObservable;

    private UserApiService mUserApiService;

    public UserViewModel() {
        mUserLiveData = new MutableLiveData<>();
        mUserLiveData.setValue(null);
        mUserObservable = new MediatorLiveData<>();
        mUserObservable.addSource(mUserLiveData, mUserObservable::postValue);

        mUserApiService = Network.getInstance().getGeneralService(UserApiService.class);
    }

    @Override
    public void addOnResponseListener(@UserResponseListener String key, OnResponseListener onResponseListener) {
        super.addOnResponseListener(key, onResponseListener);
    }

    public MediatorLiveData<UserEntity> getUserObservable() {
        return mUserObservable;
    }

    public void setUser(UserEntity entity) {
        mUserLiveData.postValue(entity);
    }

    public void setUserNickname(String nickname) {
        UserEntity entity = mUserLiveData.getValue();
        if (entity == null) {
            return;
        }
        Flowable<ResponseMessageEntity> update = mUserApiService.updateNickname(entity.getUserId(), nickname);
        update.observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseSubscriber<ResponseMessageEntity>() {
            @Override
            public void onNext(ResponseMessageEntity responseMessageEntity) {
                entity.setNickname(nickname);
                mUserLiveData.postValue(entity);
            }

            @Override
            public void onComplete() {
                OnResponseListener listener = getOnResponseListener(KEY_UPDATE_NICKNAME_RESPONSE_LISTENER);
                if (listener != null) {
                    listener.onResponse();
                }
            }
        });
    }
}
