package com.example.bookonline.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookonline.R;
import com.example.bookonline.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private EditText editAddressProfile,editEmailProfile,editNumberPhoneProfile,
            editBirthdayProfile,editUserNameProfile;
    private Button btnSaveProfile,btnExitProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button btnCancel = (Button) findViewById(R.id.btnExitProfile);
        Button btnSaveProfile = (Button) findViewById(R.id.btnSaveProfile);
        EditText editAddressProfile = (EditText) findViewById(R.id.edtAddressProfile);
        EditText editEmailProfile = (EditText) findViewById(R.id.edtEmailProfile);
        EditText editNumberPhoneProfile = (EditText) findViewById(R.id.edtNumberPhoneProfile);
        EditText editBirthdayProfile = (EditText) findViewById(R.id.edtBirthdayProfile);
        EditText editUserNameProfile = (EditText) findViewById(R.id.edtUserNameProfile);

        reference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        String iduser = mAuth.getCurrentUser().getUid();
        //rootNode = FirebaseDatabase.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("USER").child(iduser);
        //reference = rootNode.getReference("USER").child(iduser);
        //reference.child("USER").push().setValue(USER);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                String sdt=user.getSDT();
                editNumberPhoneProfile.setText(sdt);
                String email=user.getEmail();
                editEmailProfile.setText(email);
                String name=user.getName();
                editUserNameProfile.setText(name);
                String DC=user.getDiaChi();
                editAddressProfile.setText(DC);
                String NS=user.getNgaySinh();
                editBirthdayProfile.setText(NS);
                //email fetched from database
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        reference.addValueEventListener(listener);
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//                String sdt = String.valueOf(snapshot.getValue());
//                editNumberPhoneProfile.setText(sdt);
//                String email = String.valueOf(snapshot.getValue());
//                editEmailProfile.setText(email);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, ProfileFragment.class));
            }
        });

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (editUserNameProfile.getText().toString().isEmpty()
                        || editNumberPhoneProfile.getText().toString().isEmpty()
                        || editEmailProfile.getText().toString().isEmpty()
                        || editAddressProfile.getText().toString().isEmpty()
                        || editBirthdayProfile.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfileActivity.this,"Lưu thất bại ! Có dữ liệu trống !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    String name = editUserNameProfile.getText().toString();
                    reference.child("name").setValue(name);
                    editUserNameProfile.setText(name);

                    String sdt = editNumberPhoneProfile.getText().toString();
                    reference.child("sdt").setValue(sdt);
                    editNumberPhoneProfile.setText(sdt);

                    String email = editEmailProfile.getText().toString();
                    reference.child("email").setValue(email);
                    editEmailProfile.setText(email);

                    String diaChi = editAddressProfile.getText().toString();
                    reference.child("diaChi").setValue(diaChi);
                    editAddressProfile.setText(diaChi);

                    String ngaySinh = editBirthdayProfile.getText().toString();
                    reference.child("ngaySinh").setValue(ngaySinh);
                    editBirthdayProfile.setText(ngaySinh);

                    Toast.makeText(EditProfileActivity.this,"Lưu thành công !", Toast.LENGTH_SHORT).show();

                    intent = new Intent(EditProfileActivity.this,
                            ProfileFragment.class);
                }
                startActivity(intent);
            }
        });
    }
}