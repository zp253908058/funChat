package com.swpu.funchat.ui.contact;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.BaseFragment;
import com.swpu.funchat.model.PhoneContactEntity;
import com.swpu.funchat.ui.contact.adapter.PhoneContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description:
 * 添加联系人界面
 *
 * @author zp
 * @version 1.0
 * @see ContactAddFragment
 * @since 2019-06-06
 */
public class ContactAddFragment extends BaseFragment {

    private LinearLayoutManager mLayoutManager;
    private PhoneContactAdapter phoneContactAdapter;
    private RecyclerView recyclerView;
    private Context context;
    private List<PhoneContactEntity> phoneContactEntityList = new ArrayList<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_contact_add;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
        } else {
            phoneContactAdapter=new PhoneContactAdapter(queryContactPhoneNumber());
        }
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(phoneContactAdapter);
    }

    public  List<PhoneContactEntity> queryContactPhoneNumber() {
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex);
            String number = cursor.getString(numberFieldColumnIndex);
            PhoneContactEntity phoneContactEntity=new PhoneContactEntity();
            phoneContactEntity.setPhoneContactName(name);
            phoneContactEntity.setPhoneContactNumber(number);
            phoneContactEntityList.add(phoneContactEntity);
        }
        return phoneContactEntityList;
    }//获得联系人

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    phoneContactAdapter=new PhoneContactAdapter(queryContactPhoneNumber());
                    recyclerView.setAdapter(phoneContactAdapter);
                }
                else
                {
                    Toast.makeText(context, "你没有打开相应权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }//寻求权限
}

