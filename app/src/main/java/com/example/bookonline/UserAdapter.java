package com.example.bookonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookonline.ui.category.item_type_book;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<User> userList;

    public UserAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);

        //Ánh xạ view
        TextView tvCode = (TextView) convertView.findViewById(R.id.userlist_code);
        TextView tvName = (TextView) convertView.findViewById(R.id.userlist_name);
        TextView tvBirth = (TextView) convertView.findViewById(R.id.userlist_birth);
        TextView tvMail = (TextView) convertView.findViewById(R.id.userlist_mail);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.userlist_address);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.userlist_phone);

        //Gán giá trị
        User user = userList.get(position);

        tvCode.setText("Mã khách hàng: " + user.getID());
        tvName.setText("Tên: " + user.getName());
        tvBirth.setText("Năm sinh: " + user.getNgaySinh());
        tvMail.setText("Email: " + user.getEmail());
        tvAddress.setText("Địa chỉ: " + user.getDiaChi());
        tvPhone.setText("Số điện thoại: " + user.getSDT());
        return convertView;
    }
}
