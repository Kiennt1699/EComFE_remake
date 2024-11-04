package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.UserPageAdapter;
import Domain.GroupObject;
import Domain.ItemObject;

public class UserSettingsActivity {

    ExpandableListView expandableListView;
    UserPageAdapter adapter;
    List<GroupObject> listGroup;
    HashMap<GroupObject, List<ItemObject>> listItem;
    AppCompatActivity context;

    public UserSettingsActivity(AppCompatActivity context) {
        this.context = context;
        expandableListView = context.findViewById(R.id.expandableListView);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

        // Tạo adapter
        adapter = new UserPageAdapter(context, listGroup, listItem);
        expandableListView.setAdapter(adapter);

        // Khởi tạo dữ liệu
        initData();
    }

    // Hàm initData()
    private void initData() {
        // Tạo các đối tượng GroupObject
        GroupObject homeGroup = new GroupObject(1, "Home");
        GroupObject wishGroup = new GroupObject(2, "WishList");
        GroupObject cartGroup = new GroupObject(3, "Your Cart");
        GroupObject userGroup = new GroupObject(4, "User Profile");
        GroupObject historyGroup = new GroupObject(5, "History");
        GroupObject passwordGroup = new GroupObject(6, "Change Password");



        listGroup.add(homeGroup);
        listGroup.add(wishGroup);
        listGroup.add(cartGroup);
        listGroup.add(userGroup);
        listGroup.add(historyGroup);
        listGroup.add(passwordGroup);





        listItem.put(homeGroup, new ArrayList<>());
        listItem.put(wishGroup, new ArrayList<>());
        listItem.put(cartGroup, new ArrayList<>());
        listItem.put(userGroup, new ArrayList<>());
        listItem.put(passwordGroup, new ArrayList<>());

        // Khởi tạo mục cho "History Cart"
        List<ItemObject> historyItems = new ArrayList<>();
        historyItems.add(new ItemObject(1, "History Cart"));
        listItem.put(historyGroup, historyItems);

        // Thông báo cho adapter về thay đổi dữ liệu
        adapter.notifyDataSetChanged();
    }
}
