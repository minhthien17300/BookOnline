package com.example.bookonline.Builder;

import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.ui.history.ChiTietDonHang;
import com.example.bookonline.ui.history.DonHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HoaDonConstructor {
    private HoaDonBuilder hoaDonBuilder;
    private HoaDon hoaDon;
    private ArrayList<ChiTietDonHang> chiTiets;
    private DatabaseReference donHangRef;
    private DatabaseReference chiTietRef;

    public HoaDonConstructor(HoaDonBuilder hoaDonBuilder) {
        this.hoaDonBuilder = hoaDonBuilder;
    }

    public HoaDon getHoaDon(){
        return this.hoaDonBuilder.getHoaDon();
    }

    public void constructHoaDon(int idDonHang, String ten, String sdt, String diaChi, ArrayList<ChiTietDonHang> chiTietDonHangs){
        this.hoaDonBuilder.buildIDDonHang(idDonHang);
        this.hoaDonBuilder.buildIDUser();
        this.hoaDonBuilder.buildNgayMua();
        this.hoaDonBuilder.buildTen(ten);
        this.hoaDonBuilder.buildSDT(sdt);
        this.hoaDonBuilder.buildDiaChi(diaChi);
        this.hoaDonBuilder.buildListChiTiet(chiTietDonHangs);
    }

    public void pushFireBase(){
        this.hoaDon = getHoaDon();
        this.chiTiets = hoaDon.getChiTietDonHangs();

        DonHang donHang = new DonHang();
        donHang.setIDDonHang(hoaDon.getIDDonHang());
        donHang.setIDUser(hoaDon.getIDUser());
        donHang.setTenUser(hoaDon.getTen());
        donHang.setDiaChiUser(hoaDon.getDiaChi());
        donHang.setSDTUser(hoaDon.getSDT());
        donHang.setNgayMua(hoaDon.getNgayMua());

        donHangRef = FirebaseDatabase.getInstance().getReference("DONHANG");
        donHangRef.child(String.valueOf(donHang.getIDDonHang())).setValue(donHang);

        chiTietRef = FirebaseDatabase.getInstance().getReference("CHITIETDONHANG");
        for(ChiTietDonHang chiTietDonHang : chiTiets){
            chiTietRef.child(String.valueOf(chiTietDonHang.getIDChiTietDonHang())).setValue(chiTietDonHang);
        }
    }
}
