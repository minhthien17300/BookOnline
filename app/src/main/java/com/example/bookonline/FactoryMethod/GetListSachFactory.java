package com.example.bookonline.FactoryMethod;

public class GetListSachFactory {
    public ListSach getListSach(int IDDanhMuc){
        if(IDDanhMuc == 0) {
            return new ListTruyenTranh();
        }
        else if(IDDanhMuc == 1){
            return new ListTieuThuyet();
        }
        else if(IDDanhMuc == 2) {
            return new ListTiengAnh();
        }
        else if(IDDanhMuc == 3) {
            return new ListVanHoc();
        }
        else if(IDDanhMuc == 4) {
            return new ListNgheThuat();
        }
        return null;
    }
}
