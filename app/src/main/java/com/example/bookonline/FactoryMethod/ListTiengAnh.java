package com.example.bookonline.FactoryMethod;

class ListTiengAnh extends ListSach {

    @Override
    public void initList() {
        sanPhamArrayList = fireBaseObject.getSanPhamTheoDanhMuc(2);
    }
}
