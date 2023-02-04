package com.example.mybestproject.ui.other;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.mybestproject.R;

public class Other extends Fragment {

    //基本数据类型
    private String webFile;
    //基本组件
    private WebView webView;
    //特殊组件
    private OtherViewModel mViewModel;

    public static Other newInstance() {
        return new Other();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        webView = getActivity().findViewById(R.id.other_web);
        webFile = "http://www.baidu.com/";

        WebSettings webSettings =webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置能够解析Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置适应Html5 重点是这个设置
        webSettings.setDomStorageEnabled(true);
        webView.loadUrl(webFile);

    }
}