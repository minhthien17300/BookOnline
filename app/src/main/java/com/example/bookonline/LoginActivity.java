package com.example.bookonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookonline.SingletonPattern.MessageObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    public String IDUSER;
    private Button btnDangnhap;
    private TextView tvDangky, tvforgotPassword;
    private EditText edttaikhoan, edtmatkhau;
    private CheckBox checkBoxNhoMatKhau;
    private FirebaseAuth mAuth;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private MessageObject messageObject = MessageObject.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        //DB = DatabaseAccess.getInstance(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        //Đăng nhập thành công chuyển sang MainActivity
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edttaikhoan.getText().toString().trim();
                String matkhau = edtmatkhau.getText().toString().trim();


                // validations for input email and password // check th trong
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(matkhau)) {
                    messageObject.ShowDialogMessage(Gravity.CENTER,
                                                LoginActivity.this,
                                                "Vui lòng nhập đầy đủ thông tin!",
                                                0);
                    return;
                }
                else {
                    // signin existing user
                    mAuth.signInWithEmailAndPassword(email, matkhau)
                            .addOnCompleteListener(
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(
                                                @NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                messageObject.ShowDialogMessage(Gravity.CENTER,
                                                        LoginActivity.this,
                                                        "Đăng nhập thành công!",
                                                        1);

                                                String iduser = mAuth.getCurrentUser().getUid();
                                                rootNode = FirebaseDatabase.getInstance();
                                                reference = rootNode.getReference("USER").child(iduser);

                                                reference.child("id").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                                                        String id = String.valueOf(task.getResult().getValue());
                                                        Intent intent;

                                                        if(id.equals("admin")){
                                                            IDUSER = "admin";
                                                            intent = new Intent(LoginActivity.this,
                                                                    Admin_Nav_Bottom.class);
                                                        }
                                                        else{
                                                            IDUSER = iduser;
                                                            intent = new Intent(LoginActivity.this,
                                                                    MainActivity.class);
                                                        }
                                                        startActivity(intent);
                                                    }
                                                });
                                            }
                                            else {
                                                // sign-in failed
                                                messageObject.ShowDialogMessage(Gravity.CENTER,
                                                        LoginActivity.this,
                                                        "Sai Email hoặc mật khẩu!!",
                                                        0);
                                            }
                                        }
                                    });
                }
            }
        });
        tvDangky.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

        tvforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForgotPassword();
            }
        });
    }

    private void DialogForgotPassword(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forgetpass);
        dialog.setCanceledOnTouchOutside(false);

        //ánh xạ
        EditText edtEmailForgot = (EditText) dialog.findViewById(R.id.edt_changesoluong);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.btnLuu);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnCancel);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailForgot = edtEmailForgot.getText().toString();

                if(emailForgot.isEmpty()){
                    edtEmailForgot.setError("Hãy nhập Email của bạn!");
                    edtEmailForgot.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailForgot).matches()){
                    edtEmailForgot.setError("Hãy nhập đúng Email!");
                    edtEmailForgot.requestFocus();
                }

                mAuth.sendPasswordResetEmail(emailForgot).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.cancel();
                        if(task.isSuccessful()){
                            messageObject.ShowDialogMessage(Gravity.CENTER,
                                    LoginActivity.this,
                                    "Hãy kiểm tra (Hộp thư đến) của bạn để tiến hành thiết lập lại mật khẩu!",
                                    1);
                            //Toast.makeText(LoginActivity.this,"Hãy kiểm tra (Hộp thư đến) của bạn để tiến hành thiết lập lại mật khẩu!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            messageObject.ShowDialogMessage(Gravity.CENTER,
                                    LoginActivity.this,
                                    "Hãy kiểm tra lại Email của bạn và thử lại!",
                                    0);
                            //Toast.makeText(LoginActivity.this,"Hãy kiểm tra lại Email của bạn và thử lại!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

//    private void DialogError(int gravity){
//        final Dialog dialog = new Dialog(LoginActivity.this);
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

    private void AnhXa() {
        btnDangnhap = (Button) findViewById(R.id.btn_login);
        tvDangky = (TextView) findViewById(R.id.btn_create);
        tvforgotPassword = (TextView) findViewById(R.id.login_forgetpass);
        edttaikhoan = (EditText) findViewById(R.id.login_edit_email);
        edtmatkhau = (EditText) findViewById(R.id.login_edit_pass);
        checkBoxNhoMatKhau = (CheckBox) findViewById(R.id.remember_check);
    }
}