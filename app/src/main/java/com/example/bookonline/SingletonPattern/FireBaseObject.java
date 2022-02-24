package com.example.bookonline.SingletonPattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookonline.R;
import com.example.bookonline.SanPham;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import com.example.bookonline.ui.category.item_type_book;

public class FireBaseObject {

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private SanPham sanphamMau = new SanPham();
    private SanPham sanphamMauTheoTen = new SanPham();

    private ArrayList<SanPham> sanPhams = new ArrayList<>();
    private ArrayList<SanPham> giamGia = new ArrayList<>();
    private ArrayList<SanPham> theoDanhMuc = new ArrayList<>();
    private ArrayList<item_type_book> danhMucs = new ArrayList<>();

    private ArrayList<Integer> listHinh = new ArrayList<>();
    private ArrayList<Integer> listHinhDanhMuc = new ArrayList<>();

    private static FireBaseObject instance = new FireBaseObject();
    //make the constructor private so that this class cannot be
    //instantiated
    private FireBaseObject(){
        InitlistHinh();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        reference.child("SANPHAM").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                SanPham sanPham = new SanPham();
                sanPham.setIDSanPham(snapshot.getValue(SanPham.class).getIDSanPham());
                sanPham.setIDKhuyenMai(snapshot.getValue(SanPham.class).getIDKhuyenMai());
                sanPham.setIDDanhMuc(snapshot.getValue(SanPham.class).getIDDanhMuc());
                sanPham.setTenSanPham(snapshot.getValue(SanPham.class).getTenSanPham());
                sanPham.setTenTacGia(snapshot.getValue(SanPham.class).getTenTacGia());
                sanPham.setGiaSanPham(snapshot.getValue(SanPham.class).getGiaSanPham());
                sanPham.setSoLuongDaBan(snapshot.getValue(SanPham.class).getSoLuongDaBan());
                sanPham.setSoTrang(snapshot.getValue(SanPham.class).getSoTrang());
                sanPham.setNhaXuatBan(snapshot.getValue(SanPham.class).getNhaXuatBan());
                sanPham.setDichGia(snapshot.getValue(SanPham.class).getDichGia());
                sanPham.setLoaiBia(snapshot.getValue(SanPham.class).getLoaiBia());
                sanPham.setTomTat(snapshot.getValue(SanPham.class).getTomTat());
                sanPham.setKichThuoc(snapshot.getValue(SanPham.class).getKichThuoc());
                sanPham.setHinhAnhSanPham(listHinh.get(sanPham.getIDSanPham()).intValue());

                sanPhams.add(sanPham);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        InitlistHinhDanhMuc();
        reference.child("DANHMUC").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                item_type_book danhMuc = new item_type_book();
                danhMuc.setIDDanhMuc(snapshot.getValue(item_type_book.class).getIDDanhMuc());
                danhMuc.setTenDanhMuc(snapshot.getValue(item_type_book.class).getTenDanhMuc());
                danhMuc.setHinhAnhDanhMuc(listHinhDanhMuc.get(danhMuc.getIDDanhMuc()).intValue());

                danhMucs.add(danhMuc);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //Get the only object available
    public static FireBaseObject getInstance(){
        return instance;
    }

    public ArrayList<SanPham> getListSanPham(){
        return sanPhams;
    }

    public ArrayList<SanPham> getGiamGia(){
        for(SanPham sanPham: sanPhams){
            if(sanPham.getIDKhuyenMai() == 1) giamGia.add(sanPham);
        }
        return giamGia;
    }

    public ArrayList<SanPham> getSanPhamTheoDanhMuc(int IDDanhMuc){
        for(SanPham sanPham: sanPhams){
            if(sanPham.getIDDanhMuc() == IDDanhMuc) theoDanhMuc.add(sanPham);
        }
        return theoDanhMuc;
    }

    public SanPham getSanPham(int IDSanPham){
        for(SanPham sanPham: sanPhams){
            if(sanPham.getIDSanPham() == IDSanPham) sanphamMau = sanPham;
        }
        return sanphamMau;
    }

    public SanPham getSanPhamTheoTen(String TenSanPham){
        for(SanPham sanPham: sanPhams){
            if(sanPham.getTenSanPham().equals(TenSanPham)) sanphamMauTheoTen = sanPham;
        }
        return sanphamMauTheoTen;
    }

    public ArrayList<item_type_book> getListDanhMuc(){
        return danhMucs;
    }

    public ArrayList<Integer> getListHinh(){
        return  listHinh;
    }

    private void InitlistHinh(){
        listHinh.add(R.drawable.ben_khong_chong);
        listHinh.add(R.drawable.chua_dan);
        listHinh.add(R.drawable.vo_nhat);
        listHinh.add(R.drawable.tat_den);
        listHinh.add(R.drawable.so_do);
        listHinh.add(R.drawable.bi_vo);
        listHinh.add(R.drawable.buoc_duong_cung);
        listHinh.add(R.drawable.ha_noi_36_pho_phuong);
        listHinh.add(R.drawable.chi_pheo);
        listHinh.add(R.drawable.tho_au);
        listHinh.add(R.drawable.tu_vung_5000);
        listHinh.add(R.drawable.luyen_nghe_5_bi_kip);
        listHinh.add(R.drawable.danh_van);
        listHinh.add(R.drawable.sieu_tri_nho_thpt);
        listHinh.add(R.drawable.vocabulary_in_use);
        listHinh.add(R.drawable.tactics);
        listHinh.add(R.drawable.conversation);
        listHinh.add(R.drawable.hack);
        listHinh.add(R.drawable.sieu_tri_nho);
        listHinh.add(R.drawable.luoi);
        listHinh.add(R.drawable.kheo_an_noi);
        listHinh.add(R.drawable.hai_huoc);
        listHinh.add(R.drawable.dac_nhan_tam);
        listHinh.add(R.drawable.ghet);
        listHinh.add(R.drawable.thuyet_phuc);
        listHinh.add(R.drawable.ghi_nhan);
        listHinh.add(R.drawable.nghe_thuat_giao_tiep);
        listHinh.add(R.drawable.giao_tiep_noi_cong_so);
        listHinh.add(R.drawable.ngon_ngu_co_the);
        listHinh.add(R.drawable.noi_nhieu_khong_bang_noi_dung);
        listHinh.add(R.drawable.attack_on_titan);
        listHinh.add(R.drawable.kimetsu);
        listHinh.add(R.drawable.death_note);
        listHinh.add(R.drawable.tsubasa);
        listHinh.add(R.drawable.bleach);
        listHinh.add(R.drawable.doraemon);
        listHinh.add(R.drawable.conan);
        listHinh.add(R.drawable.naruto);
        listHinh.add(R.drawable.bay_vien_ngoc_rong);
        listHinh.add(R.drawable.onepiece);
        listHinh.add(R.drawable.chuong_nguyen_hon_ai);
        listHinh.add(R.drawable.tra_hoa_nu);
        listHinh.add(R.drawable.doi_gio_hu);
        listHinh.add(R.drawable.thep_da_toi_the_day);
        listHinh.add(R.drawable.ong_gia_bien_ca);
        listHinh.add(R.drawable.tam_long_cao_ca);
        listHinh.add(R.drawable.nha_tho_duc_ba);
        listHinh.add(R.drawable.tup_leu_bac_tom);
        listHinh.add(R.drawable.bo_gia);
        listHinh.add(R.drawable.harry_potter);
    }

    private void InitlistHinhDanhMuc(){
        listHinhDanhMuc.add(R.drawable.truyen_tranh);
        listHinhDanhMuc.add(R.drawable.tieu_thuyet);
        listHinhDanhMuc.add(R.drawable.tieng_anh);
        listHinhDanhMuc.add(R.drawable.van_hoc);
        listHinhDanhMuc.add(R.drawable.nghe_thuat);
    }
}
