package com.example.bookonline.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookonline.LoginActivity;
import com.example.bookonline.SingletonPattern.MessageObject;
import com.example.bookonline.User;
import com.example.bookonline.ui.history.PurchaseHistoryActivity;
import com.example.bookonline.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String passCheck;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ProfileViewModel mViewModel;
    private TextView usename,sdt,adress;
    private MessageObject messageObject = MessageObject.getInstance();

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_fragment, container, false);

        usename = (TextView) root.findViewById(R.id.user_name);
        sdt = (TextView) root.findViewById(R.id.user_phone);
        adress = (TextView) root.findViewById(R.id.user_address);

        reference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        String iduser = mAuth.getCurrentUser().getUid();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("USER").child(iduser);
        //reference.child("USER").push().setValue(USER);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                String passfirebase= user.getPassword();
                passCheck = passfirebase;

                String sdtn=user.getSDT();
                sdt.setText("Số điện thoại : "+sdtn);

                String name=user.getName();
                usename.setText("Name : "+name);

                String DC=user.getDiaChi();
                adress.setText("Địa chỉ : "+DC);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        reference.addValueEventListener(listener);

        Button btn_editUser = (Button) root.findViewById(R.id.btn_edit_user);
        btn_editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        Button btn_goHistoryBuy = (Button) root.findViewById(R.id.btn_history_buy_user);
        btn_goHistoryBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PurchaseHistoryActivity.class));
            }
        });

        Button btn_changePass = (Button) root.findViewById(R.id.btn_change_pass_user);
        btn_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassDialog(Gravity.CENTER);
            }
        });

        Button btn_logout = (Button) root.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return root;
    }

    private void ChangePassDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_change_pass);

        EditText edtPassOld = dialog.findViewById(R.id.edtPassOld);
        EditText edtPassNew = dialog.findViewById(R.id.edtPassNew);
        EditText etdRePassNew = dialog.findViewById(R.id.etdRePassNew);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gravity)
        {
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        reference.addValueEventListener(listener);

        Button btn_cancel = dialog.findViewById(R.id.btnCancelChangePass);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btn_save = dialog.findViewById(R.id.btnAcceptChangePass);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matkhaucu = edtPassOld.getText().toString().trim();
                String matkhaumoi = edtPassNew.getText().toString().trim();
                String rematkhaumoi = etdRePassNew.getText().toString().trim();

                //edtPassOld.setText(passCheck);

                // validations for input email and password // check th trong
                if (TextUtils.isEmpty(matkhaucu) || TextUtils.isEmpty(matkhaumoi)|| TextUtils.isEmpty(rematkhaumoi)) {
                    Toast.makeText(getActivity(), "Thất bại ! Có dữ liệu trống !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!passCheck.equals(matkhaucu)) {
                    //edtPassOld.setText(passCheck);
                    Toast.makeText(getActivity(), "Thất bại ! mật khẩu cũ không đúng !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (matkhaumoi.toString().length() < 6){
                    Toast.makeText(getActivity(), "Thất bại ! mật khẩu chưa đủ dài !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!matkhaumoi.equals(rematkhaumoi)){

                    Toast.makeText(getActivity(), "Thất bại ! mật khẩu nhập lại không khớp !", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    user.updatePassword(matkhaumoi);
                    String p = edtPassNew.getText().toString();
                    reference.child("password").setValue(p);
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}