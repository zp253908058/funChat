package com.swpu.funchat.repository;

import com.swpu.funchat.datasource.net.Network;
import com.swpu.funchat.datasource.net.api.ContactService;
import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.util.ChineseNameGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

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
        return Flowable.create(new FlowableOnSubscribe<List<ContactEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<ContactEntity>> emitter) throws Exception {
                List<ContactEntity> entities = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    ContactEntity entity = new ContactEntity();
                    entity.setName(ChineseNameGenerator.generateName());
                    entity.setAvatar("http://s2.sinaimg.cn/mw690/006tMk7wzy74ZYrLFux01&690");
                    entities.add(entity);
                }
                Collections.sort(entities);
                emitter.onNext(entities);
            }
        }, BackpressureStrategy.BUFFER);
    }
}
