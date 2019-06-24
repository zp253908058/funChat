package com.swpu.funchat.vm;

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

    @SuppressWarnings("unused")
    private static final String TAG = ContactViewModel.class.getSimpleName();

    private MutableLiveData<List<ContactEntity>> mContactsLiveData;
    private MediatorLiveData<List<ContactEntity>> mContactsObservable;
    private ContactRepository mContactRepository;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public ContactViewModel() {
        mContactsLiveData = new MediatorLiveData<>();
        mContactsLiveData.setValue(null);
        mContactRepository = new ContactRepository();
        mContactsObservable = new MediatorLiveData<>();
        mContactsObservable.addSource(mContactsLiveData, mContactsObservable::setValue);
    }

    public void startContactsLoading(){
        Disposable disposable = mContactRepository.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> mContactsLiveData.setValue(contacts), Throwable::printStackTrace);
        mDisposable.add(disposable);
    }

    public LiveData<List<ContactEntity>> getContactsObservable() {
        return mContactsObservable;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }
}
