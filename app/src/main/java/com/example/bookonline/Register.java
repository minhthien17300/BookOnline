package com.example.bookonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookonline.SingletonPattern.MessageObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText edtHoTen,edtEmail,edtSdt,edtMatKhau,edtXacNhan,edtDiaChi, edtNamSinh;
    private CheckBox cbNam, cbNu;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private String gioitinh;

    private MessageObject messageObject = MessageObject.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AnhXa();
        mAuth = FirebaseAuth.getInstance();

        gioitinh ="";
        cbNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbNu.setChecked(false);
                gioitinh = "Nam";
            }
        });
        cbNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbNam.setChecked(false);
                gioitinh = "Nữ";
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String sdt = edtSdt.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                String matkhau = edtMatKhau.getText().toString().trim();
                String xacnhanmatkhau = edtXacNhan.getText().toString().trim();

                if(hoten.equals("")||email.equals("")||sdt.equals("")||matkhau.equals("")||diachi.equals("")||xacnhanmatkhau.equals(""))
                {
                    messageObject.ShowDialogMessage(Gravity.CENTER,
                            Register.this,
                            "Vui lòng nhập đầy đủ thông tin!",
                            0);
                }
                else{
                    if(matkhau.equals(xacnhanmatkhau)){

                        mAuth
                                .createUserWithEmailAndPassword(email, matkhau)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        if (task.isSuccessful()) {

                                            messageObject.ShowDialogMessage(Gravity.CENTER,
                                                    Register.this,
                                                    "Đăng ký thành công!",
                                                    1);
                                            //Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            // if the user created intent to login activity
                                            rootNode= FirebaseDatabase.getInstance();
                                            reference= rootNode.getReference("USER");
                                            User newuser = new User(FirebaseAuth.getInstance().getCurrentUser().getUid(),hoten,email,matkhau,gioitinh,"nothing",namsinh,sdt,diachi);
                                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newuser);

                                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(intent);
                                        }
                                        else {

                                            messageObject.ShowDialogMessage(Gravity.CENTER,
                                                    Register.this,
                                                    "Đăng ký thất bại!",
                                                    0);
                                            //Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else{
                        messageObject.ShowDialogMessage(Gravity.CENTER,
                                Register.this,
                                "Mật khẩu không khớp!",
                                0);
                        //Toast.makeText(Register.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                        edtMatKhau.setText("");
                        edtXacNhan.setText("");
                    }
                }
            }
        });

    }

//    private void DialogError(int gravity){
//        final Dialog dialog = new Dialog(Register.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialogerror);
//
//        Window window = dialog.getWindow();
//        if(window == null){
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//
//        if(Gravity.BOTTOM == gravity)
//        {
//            dialog.setCancelable(true);
//        }
//        else {
//            dialog.setCancelable(false);
//        }
//        Button btn_oke = (Button) dialog.findViewById(R.id.btn_dialogError_Oke1);
//        btn_oke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

    private void AnhXa()
    {
        edtHoTen = (EditText) findViewById(R.id.register_edit_name);
        edtEmail = (EditText) findViewById(R.id.register_edit_email);
        edtSdt = (EditText) findViewById(R.id.register_edit_phone);
        edtNamSinh =(EditText) findViewById(R.id.register_edit_yearofBirth);
        edtDiaChi =(EditText) findViewById(R.id.register_edit_address);
        edtMatKhau= (EditText) findViewById(R.id.register_edit_pass);
        edtXacNhan = (EditText) findViewById(R.id.register_edit_confirmpass);
        cbNam = (CheckBox) findViewById(R.id.checkBox_man);
        cbNu = (CheckBox) findViewById(R.id.checkBox_woman);
        btnSignUp = (Button) findViewById(R.id.btn_register);
    }
}