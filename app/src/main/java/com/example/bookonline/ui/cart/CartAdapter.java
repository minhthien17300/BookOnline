package com.example.bookonline.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookonline.R;
import com.example.bookonline.SQLite.DatabaseAccess;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private FireBaseObject fireBaseObject= FireBaseObject.getInstance();
    private int layout;
    DatabaseAccess DB;
    List<item_cart> itemCartList;

    public CartAdapter(Context context, int layout, List<item_cart> itemCartList) {
        this.context = context;
        this.layout = layout;
        this.itemCartList = itemCartList;
    }

    @Override
    public int getCount() {
        return itemCartList.size();
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

        DB = DatabaseAccess.getInstance(this.context);
        convertView = inflater.inflate(layout,null);


        //Ánh xạ view
        ImageView imgHinhAnh = (ImageView) convertView.findViewById(R.id.imgHA);
        TextView tvName = (TextView) convertView.findViewById(R.id.item_cart_name);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.item_cart_price);
        TextView edQuantity = (TextView) convertView.findViewById(R.id.tv_item_cart_quantity);
        TextView tvTotal = (TextView) convertView.findViewById(R.id.item_cart_total);


        //Gán giá trị
        item_cart item_cart = itemCartList.get(position);

        imgHinhAnh.setImageResource(item_cart.getImg());
        tvName.setText(item_cart.getName());
        tvPrice.setText(String.valueOf(item_cart.getPrice()));
        edQuantity.setText(String.valueOf(item_cart.getQuantity()));
        tvTotal.setText(String.valueOf(item_cart.getTotal()));

        return convertView;
    }
}
