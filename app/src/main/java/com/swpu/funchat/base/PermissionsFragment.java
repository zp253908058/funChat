package com.swpu.funchat.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swpu.funchat.R;
import com.swpu.funchat.util.Validator;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see PermissionsFragment
 * @since 2019/6/24
 */
abstract class PermissionsFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {

    private static final int PERMISSION_REQUEST_CODE = 0x001;

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Validator.isNotEmpty(getPermissions())) {
            checkPermission();
        }
    }

    protected String[] getPermissions() {
        return null;
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    private void checkPermission() {
        if (hasPermission()) {
            permissionGranted();
            return;
        }
        PermissionRequest.Builder builder = new PermissionRequest.Builder(this, PERMISSION_REQUEST_CODE, getPermissions());
        if (Validator.isNotEmpty(getRationale())) {
            builder.setRationale(getRationale());
        }
        builder.setPositiveButtonText(R.string.rationale_ask_ok);
        builder.setNegativeButtonText(R.string.rationale_ask_cancel);
        EasyPermissions.requestPermissions(builder.build());
    }

    private boolean hasPermission() {
        return EasyPermissions.hasPermissions(requireContext(), getPermissions());
    }

    protected void permissionGranted() {

    }

    protected String getRationale() {
        return null;
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        onBackPressed();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        permissionGranted();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    protected void onBackPressed() {

    }
}
