package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.PurchasedActivity;
import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

import Domain.GroupObject;
import Domain.ItemObject;

public class UserPageAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<GroupObject> listGroup;
    private HashMap<GroupObject, List<ItemObject>> listItem;
    private int[] groupIcons = {R.drawable.menu_nav, R.drawable.love_nav, R.drawable.shopping_cart2, R.drawable.account_nav, R.drawable.history, R.drawable.key};

    public UserPageAdapter(Context context, List<GroupObject> listGroup, HashMap<GroupObject, List<ItemObject>> listItem) {
        this.context = context;
        this.listGroup = listGroup;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return (listGroup != null) ? listGroup.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<ItemObject> childList = listItem.get(listGroup.get(groupPosition));
        return (childList != null) ? childList.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listGroup.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return listItem.get(listGroup.get(groupPosition)).get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_user_page, parent, false);
            holder = new GroupViewHolder();
            holder.groupTitleTextView = convertView.findViewById(R.id.groupTitle);
            holder.groupIcon = convertView.findViewById(R.id.groupIcon);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        GroupObject group = (GroupObject) getGroup(groupPosition);
        holder.groupTitleTextView.setText(group.getName());
        if (groupPosition < groupIcons.length) {
            holder.groupIcon.setImageResource(groupIcons[groupPosition]);
        } else {
            holder.groupIcon.setImageResource(R.drawable.user); // Fallback icon
        }
        if (group.getOnclickListener() != null)
            convertView.setOnClickListener(v -> group.getOnclickListener().run());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_user_page, parent, false);
            holder = new ChildViewHolder();
            holder.childTitleTextView = convertView.findViewById(R.id.child_title);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        ItemObject child = (ItemObject) getChild(groupPosition, childPosition);
        holder.childTitleTextView.setText(child.getName());

        convertView.setOnClickListener(v -> {
            Log.d("UserPageAdapter", "Clicked on: " + child.getName());
            switch (child.getName()) {
                case "History Cart":
                    Intent intent = new Intent(context, PurchasedActivity.class);
                    context.startActivity(intent);
                    break;



                default:
                    Log.d("UserPageAdapter", "Other item clicked");
                    break;
            }
        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // ViewHolder classes
    static class GroupViewHolder {
        TextView groupTitleTextView;
        ImageView groupIcon;
    }

    static class ChildViewHolder {
        TextView childTitleTextView;
    }
}
