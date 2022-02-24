package com.example.bookonline.ui.history;

public class ChiTietDonHang {
    private int GiaSanPham;
    private String HinhAnhSanPham;
    private int IDChiTietDonHang;
    private int IDDonHang;
    private int MucGiamGia;
    private int SoLuongSanPham;
    private String TenSanPham;
    private int TongTienSanPham;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int giaSanPham, String hinhAnhSanPham, int IDChiTietDonHang, int IDDonHang, int mucGiamGia, int soLuongSanPham, String tenSanPham, int tongTienSanPham) {
        this.GiaSanPham = giaSanPham;
        this.HinhAnhSanPham = hinhAnhSanPham;
        this.IDChiTietDonHang = IDChiTietDonHang;
        this.IDDonHang = IDDonHang;
        this.MucGiamGia = mucGiamGia;
        this.SoLuongSanPham = soLuongSanPham;
        this.TenSanPham = tenSanPham;
        this.TongTienSanPham = tongTienSanPham;
    }

    public int getGiaSanPham() {
        return GiaSanPham;
    }

    public String getHinhAnhSanPham() {
        return HinhAnhSanPham;
    }

    public int getIDChiTietDonHang() {
        return IDChiTietDonHang;
    }

    public int getIDDonHang() {
        return IDDonHang;
    }

    public int getMucGiamGia() {
        return MucGiamGia;
    }

    public int getSoLuongSanPham() {
        return SoLuongSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public int getTongTienSanPham() {
        return TongTienSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        HinhAnhSanPham = hinhAnhSanPham;
    }

    public void setIDChiTietDonHang(int IDChiTietDonHang) {
        this.IDChiTietDonHang = IDChiTietDonHang;
    }

    public void setIDDonHang(int IDDonHang) {
        this.IDDonHang = IDDonHang;
    }

    public void setMucGiamGia(int mucGiamGia) {
        MucGiamGia = mucGiamGia;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        SoLuongSanPham = soLuongSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public void setTongTienSanPham(int tongTienSanPham) {
        TongTienSanPham = tongTienSanPham;
    }
}
