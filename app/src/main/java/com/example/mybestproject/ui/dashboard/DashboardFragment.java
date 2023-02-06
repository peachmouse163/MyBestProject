package com.example.mybestproject.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestproject.ComplaintActivity;
import com.example.mybestproject.R;
import com.example.mybestproject.adapters.AdapterSQL;
import com.example.mybestproject.databinding.FragmentDashboardBinding;
import com.example.mybestproject.datas.PayHistory;
import com.example.mybestproject.datas.User;
import com.example.mybestproject.login.Login;
import com.example.mybestproject.threads.GetPayHistoryThread;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    //基本数据类型
    //特殊数据类型
    private Context mcontext;
    public static ArrayList<PayHistory> payHistories = new ArrayList<>();
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 10){
                payHistories = (ArrayList<PayHistory>) message.obj;
            }
            //得到数据重新装载
            AdapterSQL adapterSQL = new AdapterSQL(mcontext,payHistories);
            listView.setAdapter(adapterSQL);
            return true;
        }
    });
    //基本控件对象
    private ListView listView;
    private Button btnComplaint;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Android studio自带模板——所默认控件显示
        /*
        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*
        重写此方法的解释说明：
        版本 1.3.0-alpha02
        2020 年 3 月 18 日
            onActivityCreated() 方法现已弃用。
            与 Fragment 视图有关的代码应在 onViewCreated()（在 onActivityCreated() 之前调用）中执行，
            而其他初始化代码应在 onCreate() 中执行。如需专门在 Activity 的 onCreate() 完成时接收回调，
            应在 onAttach() 中的 Activity 的 Lifecycle 上注册 LifeCycleObserver，
            并在收到 onCreate() 回调后将其移除。
         */
        //初始化控件
        InitView();
        //按钮监听
        buttonLisener();
    }

    private void buttonLisener() {
        btnComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, ComplaintActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitView() {
        mcontext = this.getActivity();
        //根据用户名，获取该用户相关账单信息。
        GetPayHistory(Login.user.getUserid());

        btnComplaint = requireActivity().findViewById(R.id.btn_payhistory_complaint);

        listView = requireActivity().findViewById(R.id.dash_listview);
        AdapterSQL adapterSQL = new AdapterSQL(mcontext,payHistories);
        listView.setAdapter(adapterSQL);
    }

    private void GetPayHistory(String userId) {
        //访问服务器，json数据。
        GetPayHistoryThread getPayHistoryThread = new GetPayHistoryThread(handler,userId);
        getPayHistoryThread.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}