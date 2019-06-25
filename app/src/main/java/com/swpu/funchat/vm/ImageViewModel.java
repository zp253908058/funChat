package com.swpu.funchat.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.datasource.net.support.ResponseSubscriber;
import com.swpu.funchat.model.FolderEntity;
import com.swpu.funchat.model.ImageEntity;
import com.swpu.funchat.repository.ImageRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ImageViewModel
 * @since 2019/6/24
 */
public class ImageViewModel extends AndroidViewModel {

    private MutableLiveData<List<ImageEntity>> mImageLiveData;
    private MediatorLiveData<List<ImageEntity>> mImageObservable;
    private ImageRepository mImageRepository;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        mImageLiveData = new MediatorLiveData<>();
        mImageLiveData.setValue(null);
        mImageObservable = new MediatorLiveData<>();
        mImageObservable.addSource(mImageLiveData, mImageObservable::setValue);
        mImageRepository = new ImageRepository();
    }

    public MediatorLiveData<List<ImageEntity>> getImageObservable() {
        return mImageObservable;
    }

    public void loadImage() {
        mImageRepository.loadImage(getApplication())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<List<ImageEntity>>() {
                    @Override
                    public void onNext(List<ImageEntity> imageEntities) {
                        setupImages(imageEntities);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setupImages(List<ImageEntity> images) {
        mImageLiveData.postValue(images);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
