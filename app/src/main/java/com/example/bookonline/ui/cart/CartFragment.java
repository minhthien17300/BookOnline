package com.example.bookonline.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.versionedparcelable.VersionedParcelize;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookonline.PayActivity;
import com.example.bookonline.R;
import com.example.bookonline.SQLite.Database;
import com.example.bookonline.SQLite.DatabaseAccess;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.User;
import com.example.bookonline.ui.category.ProductDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.security.Key;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    private FireBaseObject fireBaseObject= FireBaseObject.getInstance();
    private CartViewModel mViewModel;
    private DatabaseAccess DB;
    private ListView listViewCart;
    private ArrayList<item_cart> itemCartArrayList;
    private CartAdapter cartAdapter;

    private int imga;
    private int price_sp;
    private String ten_sp;
    private Button btnXoaGioHang;
    private TextView totalBill;
    private int TongCart;
    private User user;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    private TextView tv_item_cart_quantity;
    final  String DATABASE_NAME = "Cartogary.db";
    private static final int DATABASE_VERSION = 1;
    //private DatabaseHelper db;
    private SQLiteDatabase database;

    public CartFragment() {
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cart_fragment, container, false);
        listViewCart = (ListView) root.findViewById(R.id.listCart);
        tv_item_cart_quantity = (TextView) root.findViewById(R.id.tv_item_cart_quantity) ;
        totalBill = (TextView) root.findViewById(R.id.totalBill) ;

        //Ánh xạ
        TextView tvName = (TextView) root.findViewById(R.id.txtUserNameCart);
        TextView tvDiaChi = (TextView) root.findViewById(R.id.txtAddressCart);

        mAuth = FirebaseAuth.getInstance();
        String iduser = mAuth.getCurrentUser().getUid();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("USER").child(iduser);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                user = task.getResult().getValue(User.class);
                tvName.setText("Tên khách hàng: " + user.getName());
                tvDiaChi.setText("Địa chỉ: " + user.getDiaChi());
            }
        });


        btnXoaGioHang = (Button) root.findViewById(R.id.btnXoaGioHang);
        btnXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.ClearData();
                itemCartArrayList.clear();
                cartAdapter.notifyDataSetChanged();
                totalBill.setText("Tổng: "+0+" VNĐ");
            }
        });

        DB = DatabaseAccess.getInstance(getContext());

        itemCartArrayList = new ArrayList<>();
        AddarraySP();
        totalBill.setText("Tổng: "+TongCart+" VNĐ");
        cartAdapter = new CartAdapter(getActivity(),R.layout.list_cart_item, itemCartArrayList);
        listViewCart.setAdapter(cartAdapter);

        listViewCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String Ten = itemCartArrayList.get(position).getName();
                ChangeSLItemonCart(Gravity.CENTER,Ten,position);
                return false;
            }
        });


        Button btn_buy = (Button) root.findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PayActivity.class);
                intent.putExtra("tenUser", user.getName());
                intent.putExtra("sdtUser", user.getSDT());
                intent.putExtra("diaChiUser", user.getDiaChi());
                startActivity(intent);
            }
        });
        return root;
    }


    private void AddarraySP(){
        database= Database.initDatabase(getActivity(),DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM GioHang",null);
        itemCartArrayList.clear();
        TongCart = 0;

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String id_sp = cursor.getString(0);
            int  sl = cursor.getInt(1);

            SanPham sanPham = fireBaseObject.getSanPham(Integer.parseInt(id_sp));
            ten_sp = sanPham.getTenSanPham();
            price_sp = sanPham.getGiaSanPham();
            imga = sanPham.getHinhAnhSanPham();

//            int gia = price_sp;
//            int soluong = sl;
            int total = price_sp*sl;
            TongCart = TongCart + total;

            itemCartArrayList.add(new item_cart(imga,ten_sp,price_sp,sl,total));
        }
    }
    private void ChangeSLItemonCart(int gravity, String ten,int vitri){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changecart);

        EditText edt_changesoluong = dialog.findViewById(R.id.edt_changesoluong);
        Button btn_XoaItem = dialog.findViewById(R.id.btn_XoaItem);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnLuu = dialog.findViewById(R.id.btnLuu);

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

        btn_XoaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = fireBaseObject.getSanPhamTheoTen(ten);
                DB.deleteData(String.valueOf(sanPham.getIDSanPham()));
                itemCartArrayList.remove(vitri);
                cartAdapter.notifyDataSetChanged();
                AddarraySP();
                totalBill.setText("Tổng: "+TongCart+" VNĐ");
                dialog.cancel();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sl = edt_changesoluong.getText().toString().trim();
                SanPham sanPham = fireBaseObject.getSanPhamTheoTen(ten);
                DB.changeData(String.valueOf(sanPham.getIDSanPham()),Integer.parseInt(sl));
                itemCartArrayList.clear();
                AddarraySP();
                cartAdapter.notifyDataSetChanged();
                //itemCartArrayList.notifyAll();
                totalBill.setText("Tổng: "+TongCart+" VNĐ");
                Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        dialog.show();
    }
}