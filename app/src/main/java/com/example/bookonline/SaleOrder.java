package com.example.bookonline;

public class SaleOrder {
    private String DiaChiUser;
    private Long IDDonHang;
    private String NgayMua;
    private String SDTUser;
    private String TenUser;

    public SaleOrder() {
    }

    public SaleOrder(String DiaChiUser, Long IDDonHang, String NgayMua, String SDTUser, String TenUser) {
        this.DiaChiUser = DiaChiUser;
        this.IDDonHang = IDDonHang;
        this.NgayMua = NgayMua;
        this.SDTUser = SDTUser;
        this.TenUser = TenUser;
    }

    public String getDiaChiUser() {
        return DiaChiUser;
    }

    public void setDiaChiUser(String DiaChiUser) {
        this.DiaChiUser = DiaChiUser;
    }

    public Long getIDDonHang() {
        return IDDonHang;
    }

    public void setIDDonHang(Long IDDonHang) {
        this.IDDonHang = IDDonHang;
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
