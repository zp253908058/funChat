package com.swpu.funchat.model;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageEntity
 * @since 2019/6/24
 */
public class ImageEntity {
    private String path;
    private long time;
    private String name;
    private String mimeType;

    public ImageEntity(String path, long time, String name, String mimeType) {
        this.path = path;
        this.time = time;
        this.name = name;
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isGif() {
        return "image/gif".equals(mimeType);
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "path='" + path + '\'' +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }
}
