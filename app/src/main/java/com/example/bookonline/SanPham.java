package com.example.bookonline;

public class SanPham {
    private int IDSanPham;
    private int IDDanhMuc;
    private int IDKhuyenMai;
    private String TenSanPham;
    private String TenTacGia;
    private int GiaSanPham;
    private String DichGia;
    private int HinhAnhSanPham;
    private String KichThuoc;
    private String LoaiBia;
    private String NhaXuatBan;
    private int SoLuongDaBan;
    private int SoTrang;
    private String TomTat;

    public SanPham() {
    }

    public SanPham(int IDSanPham, int IDDanhMuc, int IDKhuyenMai, String tenSanPham, String tenTacGia, int giaSanPham, String dichGia, int hinhAnhSanPham, String kichThuoc, String loaiBia, String nhaXuatBan, int soLuongDaBan, int soTrang, String tomTat) {
        this.IDSanPham = IDSanPham;
        this.IDDanhMuc = IDDanhMuc;
        this.IDKhuyenMai = IDKhuyenMai;
        TenSanPham = tenSanPham;
        TenTacGia = tenTacGia;
        GiaSanPham = giaSanPham;
        DichGia = dichGia;
        HinhAnhSanPham = hinhAnhSanPham;
        KichThuoc = kichThuoc;
        LoaiBia = loaiBia;
        NhaXuatBan = nhaXuatBan;
        SoLuongDaBan = soLuongDaBan;
        SoTrang = soTrang;
        TomTat = tomTat;
    }

    public int getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(int IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getIDDanhMuc() {
        return IDDanhMuc;
    }

    public void setIDDanhMuc(int IDDanhMuc) {
        this.IDDanhMuc = IDDanhMuc;
    }

    public int getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(int IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public int getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getDichGia() {
        return DichGia;
    }

    public void setDichGia(String dichGia) {
        DichGia = dichGia;
    }

    public int getHinhAnhSanPham() {
        return HinhAnhSanPham;
    }

    public void setHinhAnhSanPham(int hinhAnhSanPham) {
        HinhAnhSanPham = hinhAnhSanPham;
    }

    public String getKichThuoc() {
        return KichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        KichThuoc = kichThuoc;
    }

    public String getLoaiBia() {
        return LoaiBia;
    }

    public void setLoaiBia(String loaiBia) {
        LoaiBia = loaiBia;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        NhaXuatBan = nhaXuatBan;
    }

    public int getSoLuongDaBan() {
        return SoLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        SoLuongDaBan = soLuongDaBan;
    }

    public int getSoTrang() {
        return SoTrang;
    }

    public void setSoTrang(int soTrang) {
        SoTrang = soTrang;
    }

    public String getTomTat() {
        return TomTat;
    }

    public void setTomTat(String tomTat) {
        TomTat = tomTat;
    }
}
