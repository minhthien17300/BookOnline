package com.example.bookonline.Builder;

import com.example.bookonline.ui.history.ChiTietDonHang;

import java.util.ArrayList;

public class HoaDon {
    private int IDDonHang;
    private String IDUser;
    private String NgayMua;
    private String SDT;
    private String Ten;
    private String DiaChi;
    private ArrayList<ChiTietDonHang> chiTietDonHangs = new ArrayList<>();

    public int getIDDonHang() {
        return IDDonHang;
    }

    public void setIDDonHang(int IDDonHang) {
        this.IDDonHang = IDDonHang;
    }

    public String getIDUser() {
        return IDUser;
    }

    public void setIDUser(String IDUser) {
        this.IDUser = IDUser;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public ArrayList<ChiTietDonHang> getChiTietDonHangs() {
        return chiTietDonHangs;
    }

    public void setChiTietDonHangs(ArrayList<ChiTietDonHang> chiTietDonHangs) {
        this.chiTietDonHangs = chiTietDonHangs;
    }
}
