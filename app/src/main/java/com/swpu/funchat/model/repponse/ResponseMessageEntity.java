package com.swpu.funchat.model.repponse;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ResponseMessageEntity
 * @since 2019-06-21
 */
public class ResponseMessageEntity {
    private String msg;

    public ResponseMessageEntity() {
    }

    public ResponseMessageEntity(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
