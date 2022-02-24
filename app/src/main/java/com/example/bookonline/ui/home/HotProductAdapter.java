package com.example.bookonline.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookonline.R;
import com.example.bookonline.SanPham;

import java.util.ArrayList;

public class HotProductAdapter extends  RecyclerView.Adapter<HotProductAdapter.ViewHolder>{
    ArrayList<SanPham> listHotProduct;
    Context context;

    public HotProductAdapter(ArrayList<SanPham> listHotProduct, Context context) {
        this.listHotProduct = listHotProduct;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.hot_product_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listHotProduct.get(position).getTenSanPham());
        holder.price.setText(String.valueOf(listHotProduct.get(position).getGiaSanPham()));
//        switch(listHotProduct.get(position).getIDSanPham()){
//            case 0:
//                holder.image.setImageResource(R.drawable.nghe_thuat);
//                break;
//            default:
//                holder.image.setImageResource(R.drawable.tieng_anh);
//        }
        holder.image.setImageResource(listHotProduct.get(position).getHinhAnhSanPham());
    }

    @Override
    public int getItemCount() {
        return listHotProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.hot_item_name_product);
            price = (TextView)itemView.findViewById(R.id.hot_item_price_product);
            image = (ImageView)itemView.findViewById(R.id.hot_item_img_product);
        }
    }
}
