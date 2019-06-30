package com.swpu.funchat.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.swpu.funchat.model.FolderEntity;
import com.swpu.funchat.model.ImageEntity;
import com.swpu.funchat.util.Logger;
import com.swpu.funchat.util.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageRepository
 * @since 2019/6/24
 */
public class ImageRepository {

    private static final String[] PROJECTION = {MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE};

    private static final Uri URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    /**
     * 从SDCard加载图片
     *
     * @param context context
     * @return Flowable
     */
    public Flowable<List<ImageEntity>> loadImage(final Context context) {
        Flowable<List<ImageEntity>> result = Flowable.create(emitter -> {
            Logger.d("start load images ...");
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(URI, PROJECTION, null, null, PROJECTION[2]);
            if (cursor == null) {
                Logger.d("cursor == null");
                emitter.onError(new RuntimeException("cursor == null, check the uri please."));
                return;
            }
            List<ImageEntity> images = new ArrayList<>();
            while (cursor.moveToNext()) {
                // 获取图片的路径
                String path = cursor.getString(cursor.getColumnIndex(PROJECTION[0]));
                //获取图片名称
                String name = cursor.getString(cursor.getColumnIndex(PROJECTION[1]));
                //获取图片时间
                long time = cursor.getLong(cursor.getColumnIndex(PROJECTION[2]));
                //获取图片类型
                String mimeType = cursor.getString(cursor.getColumnIndex(PROJECTION[3]));
                Logger.d(name);
                images.add(new ImageEntity(path, time, name, mimeType));
            }
            cursor.close();
            Collections.reverse(images);
            emitter.onNext(images);
            Logger.d(images.toString());
        }, BackpressureStrategy.BUFFER);

        return result;
    }

    /**
     * 检查图片是否存在。ContentResolver查询处理的数据有可能文件路径并不存在。
     *
     * @param filePath filePath
     * @return if true exists
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
