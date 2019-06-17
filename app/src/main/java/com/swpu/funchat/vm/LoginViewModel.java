package com.swpu.funchat.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see LoginViewModel
 * @since 2019-06-16
 */
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> mLoginObservable;
    private final MutableLiveData<String> mLoginSuccessObservable;

    public LoginViewModel() {
        mLoginObservable = new MutableLiveData<>();
        mLoginObservable.setValue(null);

        mLoginSuccessObservable = new MutableLiveData<>();
        mLoginSuccessObservable.setValue(null);
    }

    public MutableLiveData<String> getLoginObservable() {
        return mLoginObservable;
    }

    public MutableLiveData<String> getLoginSuccessObservable() {
        return mLoginSuccessObservable;
    }

    public void onLoginSuccess() {

    }

    public void login(String username, String password) {

    }
}
