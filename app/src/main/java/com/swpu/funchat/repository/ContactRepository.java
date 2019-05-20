package com.swpu.funchat.repository;

import android.util.Log;

import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.util.ChineseNameGenerator;

import java.util.ArrayList;
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

    public Flowable<List<ContactEntity>> getContacts() {
        return Flowable.just(1).map(integer -> {
            List<ContactEntity> result = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                ContactEntity entity = new ContactEntity();
                entity.setName(ChineseNameGenerator.generateName());
                result.add(entity);
                Log.e(TAG, entity.getName());
            }
            return result;
        });
    }
}
