package com.example.bookonline.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookonline.R;
import com.example.bookonline.ui.history.History;

import java.util.List;

public class ListTypeBookAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<item_type_book> itemTypeBookList;

    public ListTypeBookAdapter(Context context, int layout, List<item_type_book> itemTypeBookList) {
        this.context = context;
        this.layout = layout;
        this.itemTypeBookList = itemTypeBookList;
    }

    @Override
    public int getCount() {
        return itemTypeBookList.size();
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
        TextView tvName = (TextView) convertView.findViewById(R.id.type_book_item_name_);
        ImageView imgHinhDanhMuc = (ImageView) convertView.findViewById(R.id.type_book_item_img);

        //Gán giá trị
        item_type_book danhMuc = itemTypeBookList.get(position);

        tvName.setText(danhMuc.getTenDanhMuc());
        imgHinhDanhMuc.setImageResource(danhMuc.getHinhAnhDanhMuc());
        return convertView;
    }
}
