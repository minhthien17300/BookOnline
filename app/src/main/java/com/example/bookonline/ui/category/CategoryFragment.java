package com.example.bookonline.ui.category;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.bookonline.FactoryMethod.GetListSachFactory;
import com.example.bookonline.FactoryMethod.ListSach;
import com.example.bookonline.R;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.ui.home.VipProductAdapter;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private CategoryViewModel mViewModel;
    private ListView listViewTypeBook;
    private ArrayList<item_type_book> item_type_books = new ArrayList<>();
    private ListTypeBookAdapter listTypeBookAdapter;
    private GridView gridViewProductFollowType;
    private ArrayList<SanPham> listProductFollowType = new ArrayList<>();
    private VipProductAdapter listProductFollowTypeAdapter;

    private FireBaseObject fireBaseObject = FireBaseObject.getInstance();
    private GetListSachFactory factory;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category_fragment, container, false);

        factory = new GetListSachFactory();

        item_type_books = fireBaseObject.getListDanhMuc();
        listViewTypeBook = (ListView) root.findViewById(R.id.list_type_book);
        listTypeBookAdapter = new ListTypeBookAdapter(getActivity(),R.layout.item_type_book, item_type_books);
        listViewTypeBook.setAdapter(listTypeBookAdapter);
        listViewTypeBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listProductFollowType.clear();

                int IDDanhMuc = item_type_books.get(position).getIDDanhMuc();
                ListSach listSach = factory.getListSach(IDDanhMuc);
                listSach.initList();
                listProductFollowType = listSach.getList();
                listProductFollowTypeAdapter.notifyDataSetChanged();
            }
        });

        gridViewProductFollowType = root.findViewById(R.id.grid_product_follow_type);
        ListSach listSach = factory.getListSach(4);
        listSach.initList();
        listProductFollowType = listSach.getList();
        listProductFollowTypeAdapter = new VipProductAdapter(getActivity(), R.layout.item_product_follow_type, listProductFollowType);
        gridViewProductFollowType.setAdapter(listProductFollowTypeAdapter);

        gridViewProductFollowType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ID = listProductFollowType.get(position).getIDSanPham();

                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("IDSanPham", ID);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel
    }
}