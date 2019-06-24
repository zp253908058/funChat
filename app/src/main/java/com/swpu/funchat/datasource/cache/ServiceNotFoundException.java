package com.swpu.funchat.datasource.cache;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ServiceNotFoundException
 * @since 2019/6/23
 */
public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceNotFoundException(Throwable cause) {
        super(cause);
    }
}
