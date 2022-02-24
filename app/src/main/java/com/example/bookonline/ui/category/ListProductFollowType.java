package com.example.bookonline.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookonline.R;
import com.example.bookonline.ui.home.hot_product_item;

import java.util.List;

public class ListProductFollowType extends BaseAdapter {

    private Context context;
    private int layout;
    private List<hot_product_item> hotProductItemList;

    public ListProductFollowType(Context context, int layout, List<hot_product_item> hotProductItemList) {
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

        //Gán giá trị
        hot_product_item hot_product_item = hotProductItemList.get(position);

        tvName.setText(hot_product_item.getName());
        tvPrice.setText(hot_product_item.getPrice());
        return convertView;
    }
}
