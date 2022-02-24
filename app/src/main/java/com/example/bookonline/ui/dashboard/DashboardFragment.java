package com.example.bookonline.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookonline.R;
import com.example.bookonline.SaleOrderActivity;
import com.example.bookonline.UserofAdmin;
import com.example.bookonline.ui.category.CategoryActivity;
import com.example.bookonline.ui.category.CategoryFragment;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button btn_cate_admin = (Button) root.findViewById(R.id.btn_cate_admin);
        btn_cate_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Danh mục sản phẩm", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });

        Button btn_user_admin = (Button) root.findViewById(R.id.btn_user_admin);
        btn_user_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserofAdmin.class));
            }
        });

        Button btn_sale_order_admin = (Button) root.findViewById(R.id.btn_bill_admin);
        btn_sale_order_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SaleOrderActivity.class));
            }
        });

        return root;
    }
}