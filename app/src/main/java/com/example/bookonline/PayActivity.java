package com.example.bookonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookonline.Builder.HoaDon;
import com.example.bookonline.Builder.HoaDonBuilder;
import com.example.bookonline.Builder.HoaDonConstructor;
import com.example.bookonline.Builder.HoaDonThuongBuilder;
import com.example.bookonline.SQLite.Database;
import com.example.bookonline.SQLite.DatabaseAccess;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.SingletonPattern.MessageObject;
import com.example.bookonline.ui.cart.CartAdapter;
import com.example.bookonline.ui.cart.CartFragment;
import com.example.bookonline.ui.cart.item_cart;
import com.example.bookonline.ui.category.CategoryActivity;
import com.example.bookonline.ui.category.CategoryFragment;
import com.example.bookonline.ui.history.ChiTietDonHang;
import com.example.bookonline.ui.history.DonHang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    private ListView listViewCart;
    private ArrayList<item_cart> itemCartArrayList;
    private ArrayList<ChiTietDonHang> chiTietDonHangs = new ArrayList<>();
    private ArrayList<Integer> listIDDonHang = new ArrayList<>();
    private ArrayList<Integer> listIDChiTiet = new ArrayList<>();

    private CartAdapter cartAdapter;
    private TextView tvTotalBill;
    private EditText edtTen, edtDiaChi, edtSDT;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private DatabaseAccess DB;

    private HoaDonBuilder hoaDonBuilder;

    private FireBaseObject fireBaseObject = FireBaseObject.getInstance();

    private MessageObject messageObject = MessageObject.getInstance();

    private int totalCart = 0;
    private int total;

    private SQLiteDatabase database;
    final  String DATABASE_NAME = "Cartogary.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        listViewCart = (ListView) findViewById(R.id.listCart_Pay);
        itemCartArrayList = new ArrayList<>();

        AddarraySP();
        cartAdapter = new CartAdapter(PayActivity.this,R.layout.list_cart_item, itemCartArrayList);
        listViewCart.setAdapter(cartAdapter);
        tvTotalBill = (TextView) findViewById(R.id.totalBill_Pay);
        tvTotalBill.setText(String.valueOf(totalCart));

        //Ánh xạ editText
        edtTen = (EditText) findViewById(R.id.ed_NN);
        edtDiaChi = (EditText) findViewById(R.id.ed_DC);
        edtSDT = (EditText) findViewById(R.id.ed_SDT);

        //đổ dữ liệu
        Intent intent = getIntent();
        String tenUser = intent.getStringExtra("tenUser");
        String sdtUser = intent.getStringExtra("sdtUser");
        String diaChiUser = intent.getStringExtra("diaChiUser");

        edtTen.setText(tenUser);
        edtDiaChi.setText(diaChiUser);
        edtSDT.setText(sdtUser);

        //lấy dữ liệu
        String tenNguoiNhan = edtTen.getText().toString();
        String diaChi = edtDiaChi.getText().toString();
        String SDT = edtSDT.getText().toString();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();

        reference.child("DONHANG").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                DonHang donHang = snapshot.getValue(DonHang.class);
                listIDDonHang.add(donHang.getIDDonHang());
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

        reference.child("CHITIETDONHANG").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                ChiTietDonHang chiTietDonHang = snapshot.getValue(ChiTietDonHang.class);
                listIDChiTiet.add(chiTietDonHang.getIDChiTietDonHang());
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

        DB = DatabaseAccess.getInstance(this);

        Button btn_cancel_pay = (Button) findViewById(R.id.button_cancel_Pay);
        btn_cancel_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageObject.ShowDialogMessage(Gravity.CENTER,
                        PayActivity.this,
                        "Hủy thanh toán!",
                        0);
                //oast.makeText(PayActivity.this, "Hủy Thanh Toán", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PayActivity.this, MainActivity.class));
            }
        });
        Button btn_pay = (Button) findViewById(R.id.button_Pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddarrayCT(listIDChiTiet.size(), listIDDonHang.size());

                hoaDonBuilder = new HoaDonThuongBuilder();
                HoaDonConstructor constructor = new HoaDonConstructor(hoaDonBuilder);
                constructor.constructHoaDon(listIDDonHang.size(), tenNguoiNhan, SDT, diaChi,chiTietDonHangs);
                constructor.pushFireBase();

                DB.ClearData();

                messageObject.ShowDialogMessage(Gravity.CENTER,
                        PayActivity.this,
                        "Thanh toán thành công!",
                        1);
                //Toast.makeText(PayActivity.this, "Thanh Toán Thành Công", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PayActivity.this, MainActivity.class));
            }
        });
    }

    private void AddarraySP(){
        database= Database.initDatabase(this ,DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM GioHang",null);
        itemCartArrayList.clear();
        total = 0;

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String id_sp = cursor.getString(0);
            int  sl = cursor.getInt(1);

            SanPham sanPham = fireBaseObject.getSanPham(Integer.parseInt(id_sp));
            String ten_sp = sanPham.getTenSanPham();
            int price_sp = sanPham.getGiaSanPham();
            int imga = sanPham.getHinhAnhSanPham();

//            int gia = price_sp;
//            int soluong = sl;
            total = price_sp*sl;
            totalCart = totalCart + total;

            itemCartArrayList.add(new item_cart(imga,ten_sp,price_sp,sl,total));
        }
    }

    private void AddarrayCT(int beginID, int IDDonHang){
        database= Database.initDatabase(this ,DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM GioHang",null);
        chiTietDonHangs.clear();

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String id_sp = cursor.getString(0);
            int  sl = cursor.getInt(1);

            SanPham sanPham = fireBaseObject.getSanPham(Integer.parseInt(id_sp));
            String ten_sp = sanPham.getTenSanPham();
            int price_sp = sanPham.getGiaSanPham();
            int imga = sanPham.getHinhAnhSanPham();
            int mucGiamGia = sanPham.getIDKhuyenMai()*10;

            int tongGia = (price_sp*(100-mucGiamGia)/100)*sl;
            int IDChiTiet = beginID + i;

            chiTietDonHangs.add(new ChiTietDonHang(price_sp,
                                                    String.valueOf(imga),
                                                    IDChiTiet,
                                                    IDDonHang,
                                                    mucGiamGia,
                                                    sl,
                                                    ten_sp,
                                                    tongGia));
        }
    }
}