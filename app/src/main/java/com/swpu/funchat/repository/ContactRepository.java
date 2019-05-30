package com.swpu.funchat.repository;

import android.util.Log;

import com.swpu.funchat.datasource.net.Network;
import com.swpu.funchat.datasource.net.api.ContactService;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.util.ChineseNameGenerator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactRepository
 * @since 2019-05-14
 */
public class ContactRepository {

    private static final String TAG = ContactRepository.class.getSimpleName();

    private ContactService mContactService;

    public ContactRepository() {
        mContactService = Network.getInstance().getService(ContactService.class);
    }

    public Flowable<List<ContactEntity>> getContacts() {
        return mContactService.getList();
    }
}
