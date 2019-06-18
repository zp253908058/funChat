package com.swpu.funchat.ui.chat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.swpu.funchat.R;
import com.swpu.funchat.base.NavigationFragment;
import com.swpu.funchat.base.RecyclerViewTouchListener;
import com.swpu.funchat.model.GroupMessageEntity;
import com.swpu.funchat.model.MessageEntity;
import com.swpu.funchat.model.UserMessageEntity;
import com.swpu.funchat.ui.chat.adapter.MessageAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see MessageSearchFragment
 * @since 2019-06-18
 */
public class MessageSearchFragment extends NavigationFragment implements View.OnClickListener, RecyclerViewTouchListener.OnItemClickListener{

    private SearchView mSearchView;
    private LinearLayoutManager mLayoutManager;
    private MessageAdapter mAdapter;
    private long id;
    private int isPrivate;


    private List<UserMessageEntity> mUserMessageList;
    private List<GroupMessageEntity> mGroupMessageList;
    private List<MessageEntity> mList;
    public void initTestDate() {
        UserMessageEntity userEntity;
        GroupMessageEntity groupEntity;
        for(int i=0;i<100;i++) {
            userEntity = new UserMessageEntity("私人消息"+i,0,1000,2000,1,System.currentTimeMillis());
            groupEntity = new GroupMessageEntity(10000,"群组消息"+i,0,1000,System.currentTimeMillis());
            mUserMessageList.add(userEntity);
            mGroupMessageList.add(groupEntity);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("");
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        isPrivate = bundle.getInt("isPrivate");
        id = bundle.getLong("id");

        mUserMessageList = new ArrayList<>();
        mGroupMessageList = new ArrayList<>();
        mList = new ArrayList<>();

        mAdapter = new MessageAdapter(mList);
        initTestDate();
        //Log.d("搜索",id+" "+isPrivate);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message_search;
    }

    @Override
    public void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(requireContext(), this));

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            default:
                break;
        }
    }
    public void search(String keyword) {
        mList.clear();
        if(isPrivate == 0) {
            groupSearch(keyword);
        } else {
            userSearch(keyword);
        }
    }
    public void groupSearch(String keyword) {
        //群组聊天处理逻辑
        for(int i=0;i<mGroupMessageList.size();i++) {
            GroupMessageEntity entity = mGroupMessageList.get(i);
            if(entity.getContent().contains(keyword)) {
                MessageEntity temp = new MessageEntity();
                temp.setContent(entity.getContent());
                temp.setCreateTime(entity.getCreateTime());
                temp.setId(entity.getFromUserId());
                temp.setUsername("群XX");
                mList.add(temp);
            }
        }
    }
    public void userSearch(String keyword) {
        //私人聊天处理逻辑
        for(int i=0;i<mUserMessageList.size();i++) {
            UserMessageEntity entity = mUserMessageList.get(i);
            if(entity.getContent().contains(keyword)) {
                MessageEntity temp = new MessageEntity();
                temp.setContent(entity.getContent());
                temp.setCreateTime(entity.getCreateTime());
                temp.setId(entity.getFromUserId());
                temp.setUsername("李XX");
                mList.add(temp);
            }
        }
    }
    @Override
    public void onItemClick(View view, int position) {

    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        mSearchView.setIconifiedByDefault(true);
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchView.setIconified(false);

        mSearchView.setFocusable(true);
        mSearchView.requestFocusFromTouch();
        mSearchView.setQueryHint("搜索");

        mSearchView.onActionViewExpanded();

        View underline =mSearchView.findViewById(R.id.search_plate);
        underline.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));

        EditText editText = mSearchView.findViewById(R.id.search_src_text);
        editText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.tab_text_color));
        editText.setTextColor(ContextCompat.getColor(getContext(), R.color.window_background_color));
        editText.setTextSize(14);

        ImageView searchViewIcon = mSearchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchViewIcon.setColorFilter(Color.WHITE);

        ImageView closeViewIcon = mSearchView.findViewById(R.id.search_close_btn);
        closeViewIcon.setColorFilter(Color.WHITE);

        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            if (mCursorDrawableRes <= 0) {
                return;
            }

            Drawable cursorDrawable = ContextCompat.getDrawable(mSearchView.getContext(), mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }
            Drawable tintDrawable = DrawableCompat.wrap(cursorDrawable);
            DrawableCompat.setTintList(tintDrawable,
                    //此处的R.color.window_background_color 为自定义光标颜色
                    ColorStateList.valueOf(
                            ContextCompat.getColor(getContext(), R.color.window_background_color)));
            Drawable[] drawables
                    = new Drawable[] {tintDrawable, tintDrawable};
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {

        }


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                mSearchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

}

