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
 * @see GroupMessageEntity
 * @since 2019-06-18
 */
@Entity(tableName = "group_message_info")
public class GroupMessageEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id; // 聊天室id

    @ColumnInfo(name = "content")
    private String content; // 内容

    @ColumnInfo(name = "type")
    private int type; // 消息类型

    @ColumnInfo(name = "from_user_id")
    private long fromUserId; // 指向来源用户

    @ColumnInfo(name = "create_time")
    private long createTime; // 发送时间

    public GroupMessageEntity() {
    }

    @Ignore
    public GroupMessageEntity(long id, String content, int type, long fromUserId, long createTime) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.fromUserId = fromUserId;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
