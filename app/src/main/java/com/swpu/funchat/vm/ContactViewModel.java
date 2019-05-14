package com.swpu.funchat.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.model.ContactEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactViewModel
 * @since 2019-05-14
 */
public class ContactViewModel extends ViewModel {

    private MutableLiveData<List<ContactEntity>> mContacts;

    public MutableLiveData<List<ContactEntity>> getContacts() {
        if (mContacts == null) {
            mContacts = new MutableLiveData<>();
        }
        return mContacts;
    }
}
