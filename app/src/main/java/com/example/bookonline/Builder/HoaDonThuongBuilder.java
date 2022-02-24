package com.example.bookonline.Builder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.bookonline.PayActivity;
import com.example.bookonline.SQLite.Database;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.User;
import com.example.bookonline.ui.cart.item_cart;
import com.example.bookonline.ui.history.ChiTietDonHang;
import com.example.bookonline.ui.history.History;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HoaDonThuongBuilder implements HoaDonBuilder {

    private HoaDon hoaDon;
    private User user;
    private FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    private DatabaseReference reference = rootNode.getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String iduser;
    private String tenUser;
    private String sdtUser;
    private String diaChiUser;

    public HoaDonThuongBuilder() {
        this.hoaDon = new HoaDon();
        iduser = mAuth.getCurrentUser().getUid();
        //getUserInfo();
    }

    public void getUserInfo(){
        user = new User();

        reference.child("USER").child(iduser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                user = snapshot.getValue(User.class);
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


    @Override
    public void buildIDDonHang(int id) {
        this.hoaDon.setIDDonHang(id);
    }

    @Override
    public void buildIDUser() {
        this.hoaDon.setIDUser(iduser);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void buildNgayMua() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayMua = date.format(formatter);

        this.hoaDon.setNgayMua(ngayMua);
    }

    @Override
    public void buildSDT(String sdt) {
//        sdtUser = user.getSDT().toString();
        this.hoaDon.setSDT(sdt);
    }

    @Override
    public void buildTen(String ten) {
//        tenUser = user.getName().toString();
        this.hoaDon.setTen(ten);
    }

    @Override
    public void buildDiaChi(String diaChi) {
//        diaChiUser = user.getDiaChi().toString();
        this.hoaDon.setDiaChi(diaChi);
    }

    @Override
    public void buildListChiTiet(ArrayList<ChiTietDonHang> chiTietDonHangs) {
        this.hoaDon.setChiTietDonHangs(chiTietDonHangs);
    }

    @Override
    public HoaDon getHoaDon() {
        return this.hoaDon;
    }
}
