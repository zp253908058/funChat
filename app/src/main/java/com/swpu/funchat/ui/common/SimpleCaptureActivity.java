package com.swpu.funchat.ui.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.swpu.funchat.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see SimpleCaptureActivity
 * @since 2019-06-18
 */
public class SimpleCaptureActivity extends AppCompatActivity {
    private static final String TAG = SimpleCaptureActivity.class.getSimpleName();

    /** 选取相册图片 **/
    private final int CHOOSE_PICTURE = 1;
    /** 截取1:1头像图片 **/
    private final int CROP_PICTURE = 2;
    /** 拍照获取图片 **/
    private final int CAPTURE_PICTURE = 3;
    /** 文件夹的名字 **/
    private String folderName = "MineCapture";
    /** 生成的头像图片文件名字  PS：这里使用了当前时间作为文件名字 **/
    private long fileName = 0;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private Uri pictureUri;
    private Bitmap bitmap;
    /** 开始更换头像 **/
    private TextView start;
    /** 更换头像显示的View **/
    private ImageView mPicture;
    /** 提示的BottomSheetDialog **/
    private BottomSheetDialog bottomSheetDialog;

    public static void go(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SimpleCaptureActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_capture);
//        start = findViewById(R.id.simple_capture_start);
//        mPicture = findViewById(R.id.simple_capture_img);
        start.setOnClickListener(v -> showChooseImg());
    }

    /**
     * 打开BottomSheetDialog
     */
    private void showChooseImg() {
        Log.d(TAG, "showChooseImg");
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(this);
        }
        View view = getLayoutInflater().inflate(R.layout.dialog_simple_capture, null);
        TextView byPhoto = view.findViewById(R.id.dialog_button_by_photo);
        TextView byCamera = view.findViewById(R.id.dialog_button_by_camera);
        TextView cancel = view.findViewById(R.id.dialog_button_cancel);
        cancel.setOnClickListener(v -> bottomSheetDialog.cancel());
        byPhoto.setOnClickListener(v -> selectFromLocal());
        byCamera.setOnClickListener(v -> getPhotoByCamera());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
    /**
     * 关闭BottomSheetDialog
     */
    private void closeChooseImg(){
        if (bottomSheetDialog!=null&&bottomSheetDialog.isShowing()){
            bottomSheetDialog.cancel();
        }
    }

    /**
     * 创建保存图片的地址
     */
    private void createFilePath() {
        File file = new File(Environment.getExternalStorageDirectory(), folderName);
        if (!file.exists()) {
            file.mkdir();
        }
        fileName = System.currentTimeMillis();
        File fileImage = new File(file, fileName + ".jpg");
        try {
            if (fileImage.exists()) {
                fileImage.delete();
            }
            fileImage.createNewFile();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        pictureUri = Uri.fromFile(fileImage);
    }

    /**
     * 拍照选取图片
     */
    private void getPhotoByCamera() {
        if (!checkPermission(0)) {
            return;
        }
        if (!checkPermission(1)) {
            return;
        }
        createFilePath();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, CAPTURE_PICTURE);
    }

    /**
     * 从相册中选取图片
     */
    private void selectFromLocal() {
        Log.d(TAG, "showChooseImg");
        if (!checkPermission(0)) {
            return;
        }
        createFilePath();
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    /**
     * 判断权限是否给予
     */
    private boolean checkPermission(int num) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[num]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                //没有授权，暂时关闭Dialog
                closeChooseImg();
                if (num == 0) {
                    showDialogNeedStoragePermission();
                } else if (num == 1) {
                    showDialogNeedCapturePermission();
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 提示需要相机权限
     */
    private void showDialogNeedCapturePermission() {
        new AlertDialog.Builder(this)
                .setTitle("相机权限不可用")
                .setMessage("需要获取相机权限才可以拍照\n否则，您将无法使用相机拍照")
                .setPositiveButton("立即开启", (dialog, which) -> startRequestPermission(1))
                .setNegativeButton("取消", (dialog, which) -> finish()).setCancelable(false).show();
    }

    /**
     * 提示需要存储权限
     */
    private void showDialogNeedStoragePermission() {
        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("从本地获取图片需要获取存取权限\n否则，您将无法正常使用个人头像更换")
                .setPositiveButton("立即开启", (dialog, which) -> startRequestPermission(0))
                .setNegativeButton("取消", (dialog, which) -> finish()).setCancelable(false).show();
    }

    /**
     * 获取权限
     */
    private void startRequestPermission(int num) {
        ActivityCompat.requestPermissions(this, new String[]{permissions[num]}, 1024);
    }

    /**
     * 在Fragment中使用需要在Activity中重写onActivityResult,将事件分发给Fragment
     * 我在Kotlin中使用这个来进行强制分发，祝老师您的代码我这边没编译成功，所以没有测试过
     * mFragmentHolder?.get(R.id.navigation_user)?.onActivityResult(requestCode,resultCode,data)
     * ，并且在Fragment中调用super.onActivityResult(requestCode, resultCode, data)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, String.valueOf(requestCode));
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData());
                    break;
                case CAPTURE_PICTURE:
                    startPhotoZoom(pictureUri);
                    break;
                case CROP_PICTURE:
                    // 取得裁剪后的图片
                    try {
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/" + folderName + "/" + fileName + ".jpg"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    mPicture.setImageBitmap(bitmap);
                    // 使用后关闭BottomSheetDialog
                    closeChooseImg();
                    break;
                default:
            }
        }
    }

    /**
     * 截取1:1的头像图片
     * @param uri
     */
    void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        //关闭位图返回，防止内存使用过大
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //关闭人脸识别
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CROP_PICTURE);
    }
}

