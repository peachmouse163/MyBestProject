package com.example.mybestproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mybestproject.R;
import com.example.mybestproject.databinding.FragmentNotificationsBinding;
import com.example.mybestproject.datas.PersonMessage;
import com.example.mybestproject.login.Login;

public class NotificationsFragment extends Fragment {

    //基本组件类型
    private TextView tvName,tvLevel,tvScore,tvCoupon,tvMoney,tvGift;
    //特殊组件类型
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //初始化
        InitView();
    }

    private void InitView() {
        tvName = getActivity().findViewById(R.id.ps_tv_name);
        tvLevel = getActivity().findViewById(R.id.ps_tv_level);
        tvScore = getActivity().findViewById(R.id.ps_tv_gn1);
        tvCoupon = getActivity().findViewById(R.id.ps_tv_gn2);
        tvMoney = getActivity().findViewById(R.id.ps_tv_gn3);
        tvGift = getActivity().findViewById(R.id.ps_tv_gn4); 
        
        //通过方法，获得该用户的一系列详细信息
        PersonMessage pm = getUserPersonMessage(Login.user.getUserid());

        tvName.setText(pm.getName()+"顾客");
        tvLevel.setText("lv:"+pm.getLevel());
        tvScore.setText(""+pm.getScore());
        tvCoupon.setText(""+pm.getCoupon());
        tvMoney.setText(""+pm.getMoney());
        tvGift.setText(""+pm.getGift());

    }

    private PersonMessage getUserPersonMessage(String userid) {
        //根据传入的userid，获取该用户的详细信息。并返回personmessage对象
        //★假数据，数据写死
        return new PersonMessage("汝豪","99",5,3,1,10);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}