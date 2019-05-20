package com.swpu.funchat.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    private MutableLiveData<String> mUsernameLiveData;

    public UserViewModel() {
        mUsernameLiveData = new MutableLiveData<>();
        mUsernameLiveData.setValue(null);
        Log.e(TAG, "new");
    }

    public MutableLiveData<String> getUsernameLiveData() {
        return mUsernameLiveData;
    }
}
