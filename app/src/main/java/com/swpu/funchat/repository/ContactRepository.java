package com.swpu.funchat.repository;

import com.swpu.funchat.datasource.net.support.Network;
import com.swpu.funchat.datasource.net.api.ContactApiService;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.util.ChineseNameGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.BackpressureStrategy;
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

    private ContactApiService mContactApiService;

    public ContactRepository() {
        mContactApiService = Network.getInstance().getAuthorizationService(ContactApiService.class);
    }

    public Flowable<List<ContactEntity>> getContacts() {
        return Flowable.create(emitter -> {
            List<ContactEntity> entities = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                ContactEntity entity = new ContactEntity();
                entity.setName(ChineseNameGenerator.generateName());
                entity.setAvatar("http://s2.sinaimg.cn/mw690/006tMk7wzy74ZYrLFux01&690");
                entities.add(entity);
            }
            Collections.sort(entities);
            emitter.onNext(entities);
        }, BackpressureStrategy.BUFFER);
    }
}
