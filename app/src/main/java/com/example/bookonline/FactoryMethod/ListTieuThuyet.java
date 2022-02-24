package com.example.bookonline.FactoryMethod;

class ListTieuThuyet extends ListSach {

    @Override
    public void initList() {
        sanPhamArrayList = fireBaseObject.getSanPhamTheoDanhMuc(1);
    }
}
