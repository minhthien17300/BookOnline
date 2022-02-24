package com.example.bookonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SaleOrderActivity extends AppCompatActivity {
    private ListView listViewSaleOrder;
    private ArrayList<SaleOrder> saleOrderArrayList;
    private SaleOrderAdapter saleOrderAdapter;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_order);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        listViewSaleOrder = (ListView) findViewById(R.id.List_Sale_Order);

        saleOrderArrayList = new ArrayList<>();
        saleOrderAdapter = new SaleOrderAdapter(SaleOrderActivity.this,R.layout.sale_order_item, saleOrderArrayList);
        listViewSaleOrder.setAdapter(saleOrderAdapter);

        reference.child("DONHANG").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SaleOrder saleOrder = snapshot.getValue(SaleOrder.class);
                saleOrderArrayList.add(saleOrder);
                saleOrderAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}