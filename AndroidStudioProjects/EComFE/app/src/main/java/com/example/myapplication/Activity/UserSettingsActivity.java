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

        // In ra log để kiểm tra
        Log.d("UserSettingsActivity", "Adding group: " + homeGroup.getName());
        listGroup.add(homeGroup);
        Log.d("UserSettingsActivity", "Group added: " + homeGroup.getName());

        listGroup.add(wishGroup);
        Log.d("UserSettingsActivity", "Group added: " + wishGroup.getName());

        listGroup.add(cartGroup);
        listGroup.add(userGroup);
        listGroup.add(historyGroup);





        // Các mục con cho các nhóm khác
        listItem.put(homeGroup, new ArrayList<>()); // Không có mục cho wishlist
        listItem.put(wishGroup, new ArrayList<>()); // Không có mục cho wishlist
        listItem.put(cartGroup, new ArrayList<>()); // Không có mục cho cart
        listItem.put(userGroup, new ArrayList<>()); // Không có mục cho user profile
        listItem.put(historyGroup, new ArrayList<>()); // Không có mục cho history cart

        for (GroupObject group : listGroup) {
            List<ItemObject> items = listItem.get(group);
        }

        // Thông báo cho adapter về thay đổi dữ liệu
        adapter.notifyDataSetChanged();
    }
}
