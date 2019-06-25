package com.swpu.funchat.ui.image;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.ui.image.adapter.FolderAdapter;
import com.swpu.funchat.ui.image.adapter.ImageAdapter;
import com.swpu.funchat.vm.ImageViewModel;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Class description:
 * 图片界面，用于选择头像界面
 *
 * @author zp
 * @version 1.0
 * @see ImageChooseFragment
 * @since 2019-05-10
 */
public class ImageChooseFragment extends NavigationFragment implements EasyPermissions.PermissionCallbacks {

    private static String[] mPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private String mRationale;

    private ImageViewModel mImageViewModel;
    private ImageAdapter mImageAdapter;
    private FolderAdapter mFolderAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mRationale = getString(R.string.rationale_storage);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_image_choose;
    }

    @Override
    public String[] getPermissions() {
        return mPermissions;
    }

    @Override
    protected String getRationale() {
        return mRationale;
    }

    @Override
    protected void permissionGranted() {
        startLoadImage();
    }

    /*----------------------------------------------------check permission-------------------------------------------------------------------*/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_more_vertical, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_more_vertical) {
            showFolder();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mImageAdapter = new ImageAdapter();
        mFolderAdapter = new FolderAdapter();
        RecyclerView recyclerView = findViewById(R.id.image_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        recyclerView.setAdapter(mImageAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageViewModel.getImageObservable().observe(this, folderEntities -> mFolderAdapter.setItems(folderEntities));
    }

    private void showFolder() {

    }

    private void startLoadImage() {
        mImageViewModel.loadImage();
    }


}
