package com.example.bookonline.FactoryMethod;

class ListVanHoc extends ListSach {

    @Override
    public void initList() {
        sanPhamArrayList = fireBaseObject.getSanPhamTheoDanhMuc(3);
    }
}
