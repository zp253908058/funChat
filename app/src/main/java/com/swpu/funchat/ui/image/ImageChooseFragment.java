package com.swpu.funchat.ui.image;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.swpu.funchat.R;
import com.swpu.funchat.base.GridItemDecoration;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.ui.image.adapter.FolderAdapter;
import com.swpu.funchat.ui.image.adapter.ImageAdapter;
import com.swpu.funchat.vm.ImageViewModel;

/**
 * Class description:
 * 图片界面，用于选择头像界面
 *
 * @author zp
 * @version 1.0
 * @see ImageChooseFragment
 * @since 2019-05-10
 */
public class ImageChooseFragment extends NavigationFragment {

    private static String[] mPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private String mRationale;

    private ImageViewModel mImageViewModel;
    private ImageAdapter mImageAdapter;
    private FolderAdapter mFolderAdapter = new FolderAdapter();

    private BottomSheetDialog mBottomSheetDialog;

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
        RecyclerView recyclerView = findViewById(R.id.image_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        recyclerView.addItemDecoration(new GridItemDecoration(requireContext()));
        recyclerView.setAdapter(mImageAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageViewModel.getImageObservable().observe(this, images -> mImageAdapter.setItems(images));
    }

    private void showFolder() {
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(requireContext());
            mBottomSheetDialog.setContentView(R.layout.part_image_folder);
            ListView listView = mBottomSheetDialog.findViewById(R.id.image_folder_list);
            if (listView != null) {
                listView.setAdapter(mFolderAdapter);
            }

        }
        mBottomSheetDialog.show();
    }

    private void onCancel(DialogInterface dialog) {

    }

    private void startLoadImage() {
        mImageViewModel.loadImage();
    }


}
