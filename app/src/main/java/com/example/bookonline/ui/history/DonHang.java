package com.example.bookonline.ui.history;

public class DonHang {
    private String DiaChiUser;
    private int IDDonHang;
    private String IDUser;
    private String NgayMua;
    private String SDTUser;
    private String TenUser;

    public DonHang() {
    }

    public DonHang(String diaChiUser, int IDDonHang, String IDUser, String ngayMua, String SDTUser, String tenUser) {
        DiaChiUser = diaChiUser;
        this.IDDonHang = IDDonHang;
        this.IDUser = IDUser;
        NgayMua = ngayMua;
        this.SDTUser = SDTUser;
        TenUser = tenUser;
    }

    public String getDiaChiUser() {
        return DiaChiUser;
    }

    public void setDiaChiUser(String diaChiUser) {
        DiaChiUser = diaChiUser;
    }

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

    public String getSDTUser() {
        return SDTUser;
    }

    public void setSDTUser(String SDTUser) {
        this.SDTUser = SDTUser;
    }

    public String getTenUser() {
        return TenUser;
    }

    public void setTenUser(String tenUser) {
        TenUser = tenUser;
    }
}
