package com.example.bookonline.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookonline.R;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.ui.category.ProductDetails;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ArrayList<SanPham> arrayList2;
    HotProductAdapter hotProductAdapter;
    GridView gridViewVipProduct;
    ArrayList<SanPham> arrayList1;
    VipProductAdapter vipProductAdapter;

    private FireBaseObject fireBaseObject = FireBaseObject.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerViewHotProduct =(RecyclerView) root.findViewById(R.id.recyler_hot_product);
        recyclerViewHotProduct.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHotProduct.setLayoutManager(layoutManager2);

        arrayList2 = new ArrayList<>();
        arrayList2 = fireBaseObject.getGiamGia();
        hotProductAdapter = new HotProductAdapter(arrayList2, getActivity());
        recyclerViewHotProduct.setAdapter(hotProductAdapter);

        gridViewVipProduct = root.findViewById(R.id.grid_vip_product);
        arrayList1 = new ArrayList<>();
        arrayList1 = fireBaseObject.getListSanPham();
        vipProductAdapter = new VipProductAdapter(getActivity(), R.layout.hot_product_item, arrayList1);
        gridViewVipProduct.setAdapter(vipProductAdapter);

        gridViewVipProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ID = arrayList1.get(position).getIDSanPham();

                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("IDSanPham", ID);
                startActivity(intent);
            }
        });
        return root;
    }
}