package com.swpu.funchat.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.datasource.net.api.UserService;
import com.swpu.funchat.datasource.net.support.Network;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.model.repponse.ResponseMessageEntity;
import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.MD5;
import com.swpu.funchat.util.Toaster;

import org.reactivestreams.Subscription;

import java.security.MessageDigest;
import java.security.MessageDigestSpi;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginViewModel
 * @since 2019-06-16
 */
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Integer> mLoginSuccessObservable;
    private final MutableLiveData<Integer> mRegisterSuccessObservable;

    private UserService mUserService;

    public LoginViewModel() {
        mLoginSuccessObservable = new MutableLiveData<>();
        mLoginSuccessObservable.setValue(null);
        mRegisterSuccessObservable = new MutableLiveData<>();
        mRegisterSuccessObservable.setValue(null);

        mUserService = Network.getInstance().getGeneralService(UserService.class);
    }

    public MutableLiveData<Integer> getRegisterSuccessObservable() {
        return mRegisterSuccessObservable;
    }

    public MutableLiveData<Integer> getLoginSuccessObservable() {
        return mLoginSuccessObservable;
    }

    public void login(String username, String password) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.baidu.com")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UserService service = retrofit.create(UserService.class);
//        service.login(username, password);

        String md5Password = MD5.md5Decode(password);
        Flowable<UserEntity> login = mUserService.login(username, md5Password);
        login.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<UserEntity>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        Logger.d(userEntity);
                        mLoginSuccessObservable.postValue(200);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void register(String username, String password) {
        String md5Password = MD5.md5Decode(password);
        Flowable<ResponseMessageEntity> login = mUserService.register(username, md5Password);
        login.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<ResponseMessageEntity>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ResponseMessageEntity msg) {
                        Logger.d(msg);
                        Toaster.showToast(msg.toString());
                        mRegisterSuccessObservable.postValue(200);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        Toaster.showToast(t.getMessage());
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
