package com.swpu.funchat.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see MessageEntity
 * @since 2019-05-10
 */
public class MessageEntity {
    private long id;
    private String username;
    private String content;
    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
