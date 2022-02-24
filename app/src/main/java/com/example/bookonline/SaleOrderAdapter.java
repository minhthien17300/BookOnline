package com.example.bookonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SaleOrderAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<SaleOrder> saleOrderList;

    public SaleOrderAdapter(Context context, int layout, List<SaleOrder> saleOrderList) {
        this.context = context;
        this.layout = layout;
        this.saleOrderList = saleOrderList;
    }

    @Override
    public int getCount() {
        return saleOrderList.size();
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
        TextView tvCode = (TextView) convertView.findViewById(R.id.saleOrder_code);
        TextView tvName = (TextView) convertView.findViewById(R.id.saleOrder_nameUser);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.saleOrder_addressUser);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.saleOrder_phoneUser);
        TextView tvDayBuy = (TextView) convertView.findViewById(R.id.saleOrder_dayBuy);

        //Gán giá trị
        SaleOrder saleOrder = saleOrderList.get(position);

        tvCode.setText("Mã đơn hàng: " + saleOrder.getIDDonHang());
        tvName.setText("Tên khách hàng: " + saleOrder.getTenUser());
        tvAddress.setText("Địa chỉ: " + saleOrder.getDiaChiUser());
        tvPhone.setText("Số điện thoại: " + saleOrder.getSDTUser());
        tvDayBuy.setText("Ngày mua: " + saleOrder.getNgayMua());
        return convertView;
    }
}
