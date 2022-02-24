package com.example.bookonline.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookonline.R;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<History> historyList;

    public HistoryAdapter(Context context, int layout, List<History> historyList) {
        this.context = context;
        this.layout = layout;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
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
        TextView txtTen = (TextView) convertView.findViewById(R.id.txtProductNameHistory);
        TextView txtLoai = (TextView) convertView.findViewById(R.id.txtCategoryHistory);
        TextView txtGia = (TextView) convertView.findViewById(R.id.txtPriceHistory);
        TextView txtSoluong = (TextView) convertView.findViewById(R.id.txtAmountHistory);
        TextView txtNgaymua = (TextView) convertView.findViewById(R.id.txtDateHistory);
        TextView txtTongtien = (TextView) convertView.findViewById(R.id.txtTotalMoneyHistory);
        ImageView imgSanPham = (ImageView)  convertView.findViewById(R.id.txtImageProduct);

        //Gán giá trị
        History history = historyList.get(position);

        txtTen.setText("Tên sản phẩm: " + history.getTenSP());
        txtLoai.setText("Loại: " +history.getLoai());
        txtNgaymua.setText("Ngày mua: " + history.getNgaymua());
        txtGia.setText("Giá :" + history.getGia());
        txtSoluong.setText("Số lượng: " + history.getSoluong());
        txtTongtien.setText("Tổng tiền: " + history.getTongtien());
        imgSanPham.setImageResource(history.getAnhSP());



        return convertView;
    }
}
