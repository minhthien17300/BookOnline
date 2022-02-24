package com.example.bookonline.ui.category;

public class item_type_book {
    private int IDDanhMuc;
    private String TenDanhMuc;
    private int HinhAnhDanhMuc;

    public item_type_book() {
    }

    public item_type_book(int IDDanhMuc, String tenDanhMuc, int hinhAnhDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
        TenDanhMuc = tenDanhMuc;
        HinhAnhDanhMuc = hinhAnhDanhMuc;
    }

    public int getIDDanhMuc() {
        return IDDanhMuc;
    }

    public void setIDDanhMuc(int IDDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public int getHinhAnhDanhMuc() {
        return HinhAnhDanhMuc;
    }

    public void setHinhAnhDanhMuc(int hinhAnhDanhMuc) {
        HinhAnhDanhMuc = hinhAnhDanhMuc;
    }
}
