package com.example.bookonline.FactoryMethod;

class ListTruyenTranh extends ListSach {

    @Override
    public void initList() {
        sanPhamArrayList = fireBaseObject.getSanPhamTheoDanhMuc(0);
    }
}

