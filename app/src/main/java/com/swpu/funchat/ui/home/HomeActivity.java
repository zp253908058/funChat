package com.swpu.funchat.ui.home;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseActivity;
import com.swpu.funchat.base.ToolbarActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.Map;

/**
 * Class description:
 * 首页
 *
 * @author zp
 * @version 1.0
 * @see HomeActivity
 * @since 2019-05-09
 */
public class HomeActivity extends ToolbarActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private SparseArrayCompat<Fragment> mFragmentHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        showNavigationIcon(false);
        mFragmentHolder = new SparseArrayCompat<>();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        selectFragment(navView.getSelectedItemId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectFragment(item.getItemId());
        return true;
    }

    /**
     * 显示对应的Fragment
     *
     * @param id 点击BottomNavigationView对应Item的id
     */
    private void selectFragment(int id) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = getFragment(id);
        transaction.replace(R.id.home_container, fragment, String.valueOf(id));
        transaction.commitAllowingStateLoss();
    }

    /**
     * 获取Fragment
     *
     * @param id 点击BottomNavigationView对应Item的id
     * @return 返回与id对应的Fragment
     */
    private Fragment getFragment(int id) {
        Fragment fragment = mFragmentHolder.get(id);
        if (fragment == null) {
            fragment = createFragment(id);
            mFragmentHolder.put(id, fragment);
        }
        return fragment;

    }

    /**
     * 创建Fragment
     *
     * @param id 点击BottomNavigationView对应Item的id
     * @return 返回与id对应的Fragment
     */
    private Fragment createFragment(int id) {
        switch (id) {
            case R.id.navigation_message:
                return MessageFragment.newInstance();
            case R.id.navigation_contact:
                return ContactFragment.newInstance();
            case R.id.navigation_user:
            default:
                return UserFragment.newInstance();
        }
    }
}
