package com.example.myapplication.Activity;

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

public class UserSettingsActivity  {

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
        initData(); // Đảm bảo bạn đã gọi initData trước khi tạo adapter
    }

    // Hàm initData()
    private void initData() {
        // Tạo các đối tượng GroupObject
        GroupObject homeGroup = new GroupObject(1, "Home");
        GroupObject wishGroup = new GroupObject(2, "WishList");
        GroupObject cartGroup = new GroupObject(3, "Your Cart");
        GroupObject userGroup = new GroupObject(4, "User Profile");
        GroupObject historyGroup = new GroupObject(5, "History Cart");
        GroupObject passwordGroup = new GroupObject(6,"Change Password");


        listGroup.add(homeGroup);
        listGroup.add(wishGroup);
        listGroup.add(cartGroup);
        listGroup.add(userGroup);
        listGroup.add(historyGroup);
        listGroup.add(passwordGroup);





        listItem.put(homeGroup, new ArrayList<>());
        listItem.put(wishGroup, new ArrayList<>());
        listItem.put(cartGroup, new ArrayList<>()); // Không có
        listItem.put(userGroup, new ArrayList<>()); // Không có mục cho user profile
        listItem.put(historyGroup, new ArrayList<>()); // Không có mục cho history cart
        listItem.put(passwordGroup, new ArrayList<>());
        for (GroupObject group : listGroup) {
            List<ItemObject> items = listItem.get(group);
        }

        // Thông báo cho adapter về thay đổi dữ liệu
        adapter.notifyDataSetChanged();
    }
}
