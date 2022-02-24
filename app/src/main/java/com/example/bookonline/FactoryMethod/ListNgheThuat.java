package com.example.bookonline.FactoryMethod;

class ListNgheThuat extends ListSach {

    @Override
    public void initList() {
        sanPhamArrayList = fireBaseObject.getSanPhamTheoDanhMuc(4);
    }
}
