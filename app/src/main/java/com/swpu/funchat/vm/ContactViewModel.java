package com.swpu.funchat.vm;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swpu.funchat.model.ContactEntity;
import com.swpu.funchat.repository.ContactRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see ContactViewModel
 * @since 2019-05-14
 */
public class ContactViewModel extends ViewModel {

    private static final String TAG = ContactViewModel.class.getSimpleName();

    private MutableLiveData<List<ContactEntity>> mContactsLiveData;
    private ContactRepository mContactRepository;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public ContactViewModel() {
        mContactsLiveData = new MediatorLiveData<>();
        mContactsLiveData.setValue(null);
        mContactRepository = new ContactRepository();
        Disposable disposable = mContactRepository.getContacts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(contacts -> mContactsLiveData.setValue(contacts), throwable -> Log.e(TAG, "获取联系人失败！"));
        mDisposable.add(disposable);
        Log.e(TAG, "创建了实例");
    }

    public MutableLiveData<List<ContactEntity>> getContactsLiveData() {
        return mContactsLiveData;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }
}
