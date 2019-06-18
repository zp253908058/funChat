package com.swpu.funchat.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see UserMessageEntity
 * @since 2019-06-18
 */
@Entity(tableName = "user_message_info")
public class UserMessageEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id; // 自增id

    @ColumnInfo(name = "content")
    private String content; // 内容

    @ColumnInfo(name = "type")
    private int type; // 消息类型 0 普通消息 1图片 2表情

    @ColumnInfo(name = "from_user_id")
    private long fromUserId; // 指向用户表

    @ColumnInfo(name = "to_user_id")
    private long toUserId; // 指向用户表

    @ColumnInfo(name = "is_read")
    private int isRead; // 消息接受状态

    @ColumnInfo(name = "create_time")
    private long createTime; // 接受时间

    public UserMessageEntity() {
    }

    @Ignore
    public UserMessageEntity(String content, int type, long fromUserId, long toUserId, int isRead, long createTime) {
        this.content = content;
        this.type = type;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.isRead = isRead;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
