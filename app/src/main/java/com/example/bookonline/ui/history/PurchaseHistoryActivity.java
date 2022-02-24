package com.example.bookonline.ui.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookonline.R;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryActivity extends AppCompatActivity {
    private ListView lvHistory;
    private ArrayList<History> historyArrayList;
    private ArrayList<Integer> listHinh;
    private HistoryAdapter historyAdapter;
    private TextView Name;
    private TextView DiaChi;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private String iduser;
    private User user;

    private FireBaseObject fireBaseObject = FireBaseObject.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        Anhxa();

        listHinh = fireBaseObject.getListHinh();
        historyArrayList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(this,R.layout.adapter_purchase_history,historyArrayList);
        lvHistory.setAdapter(historyAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        iduser = mAuth.getCurrentUser().getUid();
        mDatabase.child("USER").child(iduser).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                user = task.getResult().getValue(User.class);
                Name.setText("Tên khách hàng: " + user.getName());
                DiaChi.setText("Địa chỉ: " + user.getDiaChi());
            }
        });

        mDatabase.child("DONHANG").orderByChild("iduser").equalTo(iduser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                DonHang donhang = snapshot.getValue(DonHang.class);

                mDatabase.child("CHITIETDONHANG").orderByChild("iddonHang").equalTo(donhang.getIDDonHang()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                        ChiTietDonHang chitiet = snapshot.getValue(ChiTietDonHang.class);
                        SanPham sanPham = fireBaseObject.getSanPhamTheoTen(chitiet.getTenSanPham());
                        historyArrayList.add(new History(listHinh.get(sanPham.getIDSanPham()).intValue(), chitiet.getTenSanPham(), chitiet.getGiaSanPham(), chitiet.getSoLuongSanPham(), "Sách", donhang.getNgayMua(), chitiet.getTongTienSanPham()));
                        lvHistory.setAdapter(historyAdapter);
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

        historyAdapter = new HistoryAdapter(this,R.layout.adapter_purchase_history,historyArrayList);
        lvHistory.setAdapter(historyAdapter);
    }

    private void Anhxa() {
        lvHistory = (ListView) findViewById(R.id.lvPurchaseHistory);
        Name =(TextView) findViewById(R.id.txtUserNamePurchaseHistory);
        DiaChi =(TextView) findViewById(R.id.txtAddressPurchaseHistory);
    }


}