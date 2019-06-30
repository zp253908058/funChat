package com.swpu.funchat.vm;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.model.MessageEntity;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see MessageViewModel
 * @since 2019-06-06
 */
public class MessageViewModel extends ViewModel {

    private MutableLiveData<MessageEntity> mLiveData;
    private MediatorLiveData<MessageEntity> mMessageLiveData;

    public MessageViewModel() {
        mMessageLiveData = new MediatorLiveData<>();
        mLiveData = new MutableLiveData<>();
        mMessageLiveData.setValue(null);

        mMessageLiveData.addSource(mLiveData, mLiveData::setValue);
    }
}
