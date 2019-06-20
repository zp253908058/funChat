package com.swpu.funchat.datasource.net.api;

import com.swpu.funchat.model.ContactEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactService
 * @since 2019-05-30
 */
public interface ContactService {

    @GET("contact")
    Flowable<List<ContactEntity>> getList();
}
