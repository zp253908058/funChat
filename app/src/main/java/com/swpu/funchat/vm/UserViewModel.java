package com.swpu.funchat.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.model.UserEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserViewModel
 * @since 2019-05-17
 */
public class UserViewModel extends ViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    private MutableLiveData<UserEntity> mUsernameLiveData;

    public UserViewModel() {
        mUsernameLiveData = new MutableLiveData<>();
        mUsernameLiveData.setValue(null);
        Log.e(TAG, "new");
    }

    public MutableLiveData<UserEntity> getUsernameLiveData() {
        return mUsernameLiveData;
    }
}
