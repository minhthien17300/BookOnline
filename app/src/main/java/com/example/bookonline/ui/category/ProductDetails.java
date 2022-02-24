package com.example.bookonline.ui.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookonline.R;
import com.example.bookonline.SQLite.DatabaseAccess;
import com.example.bookonline.SanPham;
import com.example.bookonline.SingletonPattern.FireBaseObject;
import com.example.bookonline.SingletonPattern.MessageObject;

public class ProductDetails extends AppCompatActivity {

    public int IDSanPham;
    private DatabaseAccess DB;
    private MessageObject messageObject = MessageObject.getInstance();

    private ImageView imgHinhAnhSanPham;
    private TextView tvTenSanPham, tvTenTacGia, tvGiaSanPham, tvSoLuongDaBan, tvNhaXuatBan, tvKichThuoc, tvDichGia, tvLoaiBia, tvSoTrang, tvTomtat;
    private Button btn_ThemVaoGio;

    private FireBaseObject fireBaseObject = FireBaseObject.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        DB = DatabaseAccess.getInstance(getApplicationContext());
        AnhXa();
        Intent intent = getIntent();
        IDSanPham = intent.getIntExtra("IDSanPham",0);

        SanPham sanPham = fireBaseObject.getSanPham(IDSanPham);

        imgHinhAnhSanPham.setImageResource(sanPham.getHinhAnhSanPham());
        tvTenSanPham.setText("Tên: " + sanPham.getTenSanPham());
        tvGiaSanPham.setText("Giá: " + String.valueOf(sanPham.getGiaSanPham()));
        tvSoLuongDaBan.setText("Số lương đã bán: " + String.valueOf(sanPham.getSoLuongDaBan()));
        tvTenTacGia.setText("Tác giả: " + sanPham.getTenTacGia());
        tvNhaXuatBan.setText("Nhà xuất bản: " + sanPham.getNhaXuatBan());
        tvKichThuoc.setText("Kích thước: " + sanPham.getKichThuoc());
        tvDichGia.setText("Dịch giả: " + sanPham.getDichGia());
        tvLoaiBia.setText("Loại bìa: " + sanPham.getLoaiBia());
        tvSoTrang.setText("Số trang: " + sanPham.getSoTrang());
        tvTomtat.setText("Tóm tắt: " + sanPham.getTomTat());

        btn_ThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ID = sanPham.getIDSanPham();//id đây nha
                String ids = String.valueOf(ID);
                //DB.open();
                DB.insertData(ids,1);
                DB.close();
                messageObject.ShowDialogMessage(Gravity.CENTER,
                                                ProductDetails.this,
                                                "Thêm thành công!",
                                                1);
                //Toast.makeText(ProductDetails.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        imgHinhAnhSanPham = (ImageView) findViewById(R.id.imageView3);
        tvTenSanPham = (TextView) findViewById(R.id.detail_product_name);
        tvGiaSanPham = (TextView) findViewById(R.id.detail_product_price);
        tvSoLuongDaBan = (TextView) findViewById(R.id.detail_product_quantity);
        tvTenTacGia = (TextView) findViewById(R.id.detail_product_TG);
        tvNhaXuatBan = (TextView) findViewById(R.id.detail_product_NXB);
        tvKichThuoc = (TextView) findViewById(R.id.detail_product_KT);
        tvDichGia = (TextView) findViewById(R.id.detail_product_DG);
        tvLoaiBia = (TextView) findViewById(R.id.detail_product_LB);
        tvSoTrang = (TextView) findViewById(R.id.detail_product_ST);
        tvTomtat = (TextView) findViewById(R.id.detail_product_TT);
        btn_ThemVaoGio = (Button) findViewById(R.id.btn_add_to_cart);
    }
}