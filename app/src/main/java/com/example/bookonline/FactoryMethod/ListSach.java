package com.example.bookonline.FactoryMethod;

import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;

import java.util.ArrayList;

public abstract class ListSach {
    protected ArrayList<SanPham> sanPhamArrayList;
    protected FireBaseObject fireBaseObject = FireBaseObject.getInstance();
    public abstract void initList();

    public ArrayList<SanPham> getList(){
        return sanPhamArrayList;
    }
}
