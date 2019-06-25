package com.swpu.funchat.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.swpu.funchat.model.FolderEntity;
import com.swpu.funchat.model.ImageEntity;
import com.swpu.funchat.util.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageRepository
 * @since 2019/6/24
 */
public class ImageRepository {
    /**
     * 从SDCard加载图片
     *
     * @param context
     * @param callback
     */
    public static void loadImageForSDCard(final Context context, final DataCallback callback) {
        //由于扫描图片是耗时的操作，所以要在子线程处理。
        new Thread(new Runnable() {
            @Override
            public void run() {
                //扫描图片
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = context.getContentResolver();

                Cursor mCursor = mContentResolver.query(mImageUri, new String[]{
                                MediaStore.Images.Media.DATA,
                                MediaStore.Images.Media.DISPLAY_NAME,
                                MediaStore.Images.Media.DATE_ADDED,
                                MediaStore.Images.Media._ID,
                                MediaStore.Images.Media.MIME_TYPE},
                        null,
                        null,
                        MediaStore.Images.Media.DATE_ADDED);

                ArrayList<ImageEntity> images = new ArrayList<>();

                //读取扫描到的图片
                if (mCursor != null) {
                    while (mCursor.moveToNext()) {
                        // 获取图片的路径
                        String path = mCursor.getString(
                                mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        //获取图片名称
                        String name = mCursor.getString(
                                mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        //获取图片时间
                        long time = mCursor.getLong(
                                mCursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));

                        //获取图片类型
                        String mimeType = mCursor.getString(
                                mCursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE));

                        //过滤未下载完成或者不存在的文件
                        if (!"downloading".equals(getExtensionName(path)) && checkImgExists(path)) {
                            images.add(new ImageEntity(path, time, name, mimeType));
                        }
                    }
                    mCursor.close();
                }
                Collections.reverse(images);
                callback.onSuccess(splitFolder(images));
            }
        }).start();
    }

    /**
     * 检查图片是否存在。ContentResolver查询处理的数据有可能文件路径并不存在。
     *
     * @param filePath
     * @return
     */
    private static boolean checkImgExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 把图片按文件夹拆分，第一个文件夹保存所有的图片
     *
     * @param images
     * @return
     */
    private static ArrayList<FolderEntity> splitFolder(ArrayList<ImageEntity> images) {
        ArrayList<FolderEntity> folders = new ArrayList<>();
        folders.add(new FolderEntity("全部图片", images));

        if (images != null && !images.isEmpty()) {
            int size = images.size();
            for (int i = 0; i < size; i++) {
                String path = images.get(i).getPath();
                String name = getFolderName(path);
                if (Validator.isNotEmpty(name)) {
                    FolderEntity folder = getFolder(name, folders);
                    folder.addImage(images.get(i));
                }
            }
        }
        return folders;
    }

    /**
     * Java文件操作 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }

    /**
     * 根据图片路径，获取图片文件夹名称
     *
     * @param path
     * @return
     */
    private static String getFolderName(String path) {
        if (Validator.isNotEmpty(path)) {
            String[] strings = path.split(File.separator);
            if (strings.length >= 2) {
                return strings[strings.length - 2];
            }
        }
        return "";
    }

    private static FolderEntity getFolder(String name, List<FolderEntity> folders) {
        if (!folders.isEmpty()) {
            int size = folders.size();
            for (int i = 0; i < size; i++) {
                FolderEntity folder = folders.get(i);
                if (name.equals(folder.getName())) {
                    return folder;
                }
            }
        }
        FolderEntity newFolder = new FolderEntity(name);
        folders.add(newFolder);
        return newFolder;
    }

    public interface DataCallback {
        void onSuccess(ArrayList<FolderEntity> folders);
    }
}
