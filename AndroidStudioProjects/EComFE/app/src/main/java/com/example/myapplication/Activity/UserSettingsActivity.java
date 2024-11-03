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

public class UserSettingsActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    UserPageAdapter adapter;
    List<GroupObject> listGroup;
    HashMap<GroupObject, List<ItemObject>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        expandableListView = findViewById(R.id.expandableListView);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

        // Khởi tạo dữ liệu
        initData(); // Đảm bảo bạn đã gọi initData trước khi tạo adapter

        // Tạo adapter
        adapter = new UserPageAdapter(this, listGroup, listItem);
        expandableListView.setAdapter(adapter);
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
        Log.d("UserSettingsActivity", "Group added: " + cartGroup.getName());

        listGroup.add(userGroup);
        Log.d("UserSettingsActivity", "Group added: " + userGroup.getName());

        listGroup.add(historyGroup);
        Log.d("UserSettingsActivity", "Group added: " + historyGroup.getName());

        // Khởi tạo các mục con cho từng nhóm
        List<ItemObject> homeItems = new ArrayList<>();
        homeItems.add(new ItemObject(1, "Dashboard"));
        homeItems.add(new ItemObject(2, "Notifications"));

        // In ra log để kiểm tra mục con
        Log.d("UserSettingsActivity", "Adding items to group: " + homeGroup.getName());
        for (ItemObject item : homeItems) {
            Log.d("UserSettingsActivity", "Item added: " + item.getName());
        }

        listItem.put(homeGroup, homeItems);

        // Các mục con cho các nhóm khác
        listItem.put(wishGroup, new ArrayList<>()); // Không có mục cho wishlist
        listItem.put(cartGroup, new ArrayList<>()); // Không có mục cho cart
        listItem.put(userGroup, new ArrayList<>()); // Không có mục cho user profile
        listItem.put(historyGroup, new ArrayList<>()); // Không có mục cho history cart

        // In ra log để kiểm tra trạng thái của listGroup và listItem
        Log.d("UserSettingsActivity", "Group count: " + listGroup.size());
        for (GroupObject group : listGroup) {
            Log.d("UserSettingsActivity", "Group: " + group.getName());
            List<ItemObject> items = listItem.get(group);
            Log.d("UserSettingsActivity", "Items count in group " + group.getName() + ": " + (items != null ? items.size() : 0));
        }

        // Thông báo cho adapter về thay đổi dữ liệu
        adapter.notifyDataSetChanged();
    }


}
