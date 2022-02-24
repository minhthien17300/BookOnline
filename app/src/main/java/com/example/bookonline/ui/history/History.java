package com.example.bookonline.ui.history;

import java.util.Date;

public class History {
    private int AnhSP;
    private String TenSP;
    private int Gia;
    private int Soluong;
    private String Loai;
    private String Ngaymua;
    private int Tongtien;

    public int getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(int anhSP) {
        AnhSP = anhSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public double getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getNgaymua() {
        return Ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        Ngaymua = ngaymua;
    }

    public double getTongtien() {
        return Tongtien;
    }

    public void setTongtien(int tongtien) {
        Tongtien = tongtien;
    }

    public History() {
    }

    public History(int anhSP, String tenSP, int gia, int soluong, String loai, String ngaymua, int tongtien) {
        AnhSP = anhSP;
        TenSP = tenSP;
        Gia = gia;
        Soluong = soluong;
        Loai = loai;
        Ngaymua = ngaymua;
        Tongtien = tongtien;
    }
}
