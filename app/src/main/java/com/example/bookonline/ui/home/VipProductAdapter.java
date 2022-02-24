package com.example.bookonline.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookonline.R;
import com.example.bookonline.SanPham;

import java.util.ArrayList;
import java.util.List;

public class VipProductAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<SanPham> hotProductItemList;

    public VipProductAdapter(Context context, int layout, ArrayList<SanPham> hotProductItemList) {
        this.context = context;
        this.layout = layout;
        this.hotProductItemList = hotProductItemList;
    }

    @Override
    public int getCount() {
        return hotProductItemList.size();
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
        TextView tvName = (TextView) convertView.findViewById(R.id.hot_item_name_product);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.hot_item_price_product);
        ImageView imgImage = (ImageView) convertView.findViewById(R.id.hot_item_img_product);

        //Gán giá trị
        SanPham sanPham = hotProductItemList.get(position);

        tvName.setText(sanPham.getTenSanPham());
        tvPrice.setText(String.valueOf(sanPham.getGiaSanPham()));
        imgImage.setImageResource(sanPham.getHinhAnhSanPham());
        return convertView;
    }
}
