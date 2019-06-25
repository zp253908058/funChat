package com.swpu.funchat.model;

import com.swpu.funchat.util.Validator;

import java.util.ArrayList;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see FolderEntity
 * @since 2019/6/24
 */
public class FolderEntity {
    private String name;
    private ArrayList<ImageEntity> images;

    public FolderEntity(String name) {
        this.name = name;
    }

    public FolderEntity(String name, ArrayList<ImageEntity> images) {
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ImageEntity> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageEntity> images) {
        this.images = images;
    }

    public void addImage(ImageEntity image) {
        if (image != null && Validator.isNotEmpty(image.getPath())) {
            if (images == null) {
                images = new ArrayList<>();
            }
            images.add(image);
        }
    }
}
