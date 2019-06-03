package com.swpu.funchat.repository;

import com.swpu.funchat.datasource.net.Network;
import com.swpu.funchat.datasource.net.api.ContactService;
import com.swpu.funchat.model.ContactEntity;

import java.util.List;

import io.reactivex.Flowable;

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
