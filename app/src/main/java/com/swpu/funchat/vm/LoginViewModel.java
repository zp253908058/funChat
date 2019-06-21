package com.swpu.funchat.vm;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.datasource.net.api.UserService;
import com.swpu.funchat.datasource.net.support.Network;
import com.swpu.funchat.model.UserEntity;
import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.Toaster;

import org.reactivestreams.Subscription;

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

        Flowable<UserEntity> login = mUserService.login(username, password);
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
        Flowable<String> login = mUserService.register(username, password);
        login.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String msg) {
                        Logger.d(msg);
                        Toaster.showToast(msg);
                        mRegisterSuccessObservable.postValue(200);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        Toaster.showToast(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
