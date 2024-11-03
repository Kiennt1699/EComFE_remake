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

import Adapter.QuestionAdapter;

import Domain.GroupObject;
import Domain.ItemObject;

public class QuestionActivity  {

    ExpandableListView expandableListView;
    QuestionAdapter adapter;
    List<GroupObject> listGroup;
    HashMap<GroupObject, List<ItemObject>> listItem;
    AppCompatActivity context;

    public QuestionActivity(AppCompatActivity context) {
        this.context = context;
        expandableListView = context.findViewById(R.id.expandableQuestionListView);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();



        // Tạo adapter
        adapter = new QuestionAdapter(context, listGroup, listItem);
        expandableListView.setAdapter(adapter);
        // Khởi tạo dữ liệu
        initData();
    }

    // Hàm initData()
    private void initData() {
        // Tạo các đối tượng GroupObject
        GroupObject mapGroup = new GroupObject(1, "Where are the shop location");
        GroupObject question1Group = new GroupObject(3, "How long does delivery take?");
        GroupObject question2Group = new GroupObject(4, "Can I refund the item?");
        GroupObject question3Group = new GroupObject(5, "How can I pay?");
        GroupObject warrantyGroup = new GroupObject(6, "What is the warranty policy for your products?");
        GroupObject ChatGroup = new GroupObject(7, "Chat with staff");
        ChatGroup.setOnclickListener(() -> {
            Intent intent = new Intent(context, ChatActivity.class);
            context.startActivity(intent);
        });

        // Thêm các group vào danh sách
        listGroup.add(mapGroup);
        listGroup.add(question1Group);
        listGroup.add(question2Group);
        listGroup.add(question3Group);
        listGroup.add(warrantyGroup);
        listGroup.add(ChatGroup);

        // Tạo danh sách ItemObject cho từng group
        List<ItemObject> mapItems = new ArrayList<>();
        mapItems.add(new ItemObject(1, "R639+HM2, Khu đô thị mới, Quy Nhơn, Bình Định 55117"));
        listItem.put(mapGroup, mapItems);

        List<ItemObject> question1Items = new ArrayList<>();
        question1Items.add(new ItemObject(4, "Standard delivery: 3-5 business days."));
        question1Items.add(new ItemObject(5, "Express delivery: 1-2 business days."));
        listItem.put(question1Group, question1Items);

        List<ItemObject> question2Items = new ArrayList<>();
        question2Items.add(new ItemObject(6, "You can request a refund within 30 days."));
        question2Items.add(new ItemObject(7, "Refunds are processed within 7-10 business days."));
        listItem.put(question2Group, question2Items);

        List<ItemObject> question3Items = new ArrayList<>();
        question3Items.add(new ItemObject(8, "You can pay via credit card, PayPal, or bank transfer."));
        question3Items.add(new ItemObject(9, "Cash on delivery is also available in some areas."));
        listItem.put(question3Group, question3Items);

        // Tạo danh sách item cho nhóm warranty
        List<ItemObject> warrantyItems = new ArrayList<>();
        warrantyItems.add(new ItemObject(10, "Our products come with a one-year warranty against manufacturing defects."));
        warrantyItems.add(new ItemObject(11, "The warranty does not cover damages due to misuse or accidents."));
        listItem.put(warrantyGroup, warrantyItems);

        // Thông báo cho adapter về thay đổi dữ liệu
        adapter.notifyDataSetChanged();
    }


}
