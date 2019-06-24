package com.swpu.funchat.datasource.net.support;

import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.Toaster;

import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.FlowableSubscriber;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ResponseSubscriber
 * @since 2019/6/22
 */
public abstract class ResponseSubscriber<T> implements FlowableSubscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable t) {
        String error = t.getLocalizedMessage();
        if (!(t instanceof HttpException)) {
            Logger.e(error);
            Toaster.showToast(error);
            return;
        }
        HttpException e = (HttpException) t;
        Response response = e.response();
        if (response == null) {
            Logger.e(error);
            Toaster.showToast(error);
            return;
        }
        ResponseBody errorBody = response.errorBody();
        if (errorBody == null) {
            Logger.e(error);
            Toaster.showToast(error);
            return;
        }
        try {
            Logger.e(errorBody.string());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
