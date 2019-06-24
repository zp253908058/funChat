package com.swpu.funchat.vm;

import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ResponseViewModel
 * @since 2019/6/22
 */
public class ResponseViewModel extends ViewModel {
    public interface OnResponseListener {
        void onResponse();
    }

    private Map<String, OnResponseListener> mOnResponseListeners = new HashMap<>();

    public OnResponseListener getOnResponseListener(String key) {
        return mOnResponseListeners.get(key);
    }

    public void addOnResponseListener(String key, OnResponseListener onResponseListener) {
        Preconditions.checkNotNull(onResponseListener);
        mOnResponseListeners.put(key, onResponseListener);
    }

    public OnResponseListener removeOnResponseListener(String key){
        return mOnResponseListeners.remove(key);
    }
}
