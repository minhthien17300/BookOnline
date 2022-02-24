package com.example.bookonline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.bookonline.ui.category.ListTypeBookAdapter;
import com.example.bookonline.ui.category.item_type_book;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserofAdmin extends AppCompatActivity {
    private ListView listViewUserList;
    private ArrayList<User> userArrayList;
    private UserAdapter userAdapter;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userof_admin);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        listViewUserList = (ListView) findViewById(R.id.List_User);

        userArrayList = new ArrayList<>();
        userAdapter = new UserAdapter(UserofAdmin.this,R.layout.list_user_item, userArrayList);
        listViewUserList.setAdapter(userAdapter);

        reference.child("USER").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                userArrayList.add(user);
                userAdapter.notifyDataSetChanged();
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