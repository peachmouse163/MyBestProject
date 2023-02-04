package com.example.mybestproject.ui.pay;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybestproject.Bill;
import com.example.mybestproject.R;
import com.example.mybestproject.login.Login;

public class PayFragment extends Fragment {

    //基本数据
    private String name;
    //基本组件
    private TextView tvName;
    private ImageButton ibtnNew1,ibtnNew2,ibtnNew3;
    private LinearLayout layoutPay,layoutCollect;
    //特殊组件
    private PayViewModel mViewModel;

    public static PayFragment newInstance() {
        return new PayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化组件，绑定xml文件
        initView();
        //为组件，绑定点击响应监听
        OnClickListenerView();

    }

    private void OnClickListenerView() {
        layoutPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Bill.class);
                startActivity(intent);
            }
        });
        layoutCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂未",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        tvName = getActivity().findViewById(R.id.tv_pay_name);

        ibtnNew1 = getActivity().findViewById(R.id.ibtn_pay_new1);
        ibtnNew2 = getActivity().findViewById(R.id.ibtn_pay_new2);
        ibtnNew3 = getActivity().findViewById(R.id.ibtn_pay_new3);

        layoutPay = getActivity().findViewById(R.id.ll_pay_bill);
        layoutCollect = getActivity().findViewById(R.id.ll_pay_collect);

        name = Login.user.getUsername()+"先生，您好！";
        tvName.setText(name);
    }
}