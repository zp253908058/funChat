package com.swpu.funchat.vm;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.model.FolderEntity;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageViewModel
 * @since 2019/6/24
 */
public class ImageViewModel extends ViewModel {

    private MutableLiveData<List<FolderEntity>> mImageLiveData;
    private MediatorLiveData<List<FolderEntity>> mImageObservable;

    public ImageViewModel() {
        mImageLiveData = new MediatorLiveData<>();
        mImageLiveData.setValue(null);
        mImageObservable = new MediatorLiveData<>();
        mImageObservable.addSource(mImageLiveData, mImageObservable::setValue);
    }

    public MediatorLiveData<List<FolderEntity>> getImageObservable() {
        return mImageObservable;
    }

    public void loadImage(){

    }
}
