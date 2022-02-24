package com.example.bookonline.Builder;

import com.example.bookonline.User;
import com.example.bookonline.ui.history.ChiTietDonHang;

import java.util.ArrayList;

public interface HoaDonBuilder {
    public void buildIDDonHang(int id);
    public void buildIDUser();
    public void buildNgayMua();
    public void buildSDT(String sdt);
    public void buildTen(String ten);
    public void buildDiaChi(String diaChi);
    public void buildListChiTiet(ArrayList<ChiTietDonHang> chiTietDonHangs);

    public HoaDon getHoaDon();
}
