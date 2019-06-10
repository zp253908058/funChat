package com.swpu.funchat.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.base.ToolbarActivity;
import com.swpu.funchat.ui.chat.MessageFragment;
import com.swpu.funchat.ui.contact.ContactListFragment;
import com.swpu.funchat.ui.user.HomeUserFragment;

/**
 * Class description:
 * 首页
 *
 * @author zp
 * @version 1.0
 * @see HomeFragment
 * @since 2019-05-09
 */
public class HomeFragment extends NavigationFragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private SparseArrayCompat<Fragment> mFragmentHolder;
    private Fragment mCurrentFragment;
    private CharSequence mTitle;

    public HomeFragment() {
        Log.e(TAG, "HomeFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentHolder = new SparseArrayCompat<>();
        Log.e(TAG, "onCreate");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mCurrentFragment == null) {
            setupBottomNavigationBar();
        } else {
            FragmentManager manager = getFragmentManager();
            if (manager == null) {
                return;
            }
            manager.beginTransaction().show(mCurrentFragment).commit();
            setTitle(mTitle);
        }
    }

    private void setupBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.message_fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switchFragment(item.getItemId());
        setTitle(item.getTitle());
        return true;
    }

    private void setTitle(CharSequence title) {
        mTitle = title;
        ToolbarActivity activity = (ToolbarActivity) requireActivity();
        activity.setTitle(title);
    }

    private void switchFragment(int id) {
        FragmentManager manager = getFragmentManager();
        if (manager == null) {
            return;
        }
        Fragment target = getFragment(id);
        if (target == mCurrentFragment) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (target.isAdded()) {
            transaction.show(target);
        } else {
            transaction.add(R.id.home_content_layout, target);
        }
        mCurrentFragment = target;
        transaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(int id) {
        Fragment fragment = mFragmentHolder.get(id);
        if (fragment != null) {
            return fragment;
        }
        switch (id) {
            case R.id.contact_fragment:
                fragment = ContactListFragment.newInstance();
                break;
            case R.id.user_fragment:
                fragment = HomeUserFragment.newInstance();
                break;
            case R.id.message_fragment:
            default:
                fragment = MessageFragment.newInstance();
                break;
        }
        mFragmentHolder.put(id, fragment);
        return fragment;
    }
}
