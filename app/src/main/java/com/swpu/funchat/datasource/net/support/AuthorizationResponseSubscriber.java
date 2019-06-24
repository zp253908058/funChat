package com.swpu.funchat.datasource.net.support;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AuthorizationResponseSubscriber
 * @since 2019/6/22
 */
public abstract class AuthorizationResponseSubscriber<T> extends ResponseSubscriber<T> {
    @Override
    public void onError(Throwable t) {
        super.onError(t);
        //TODO 登录失效的处理
    }
}
