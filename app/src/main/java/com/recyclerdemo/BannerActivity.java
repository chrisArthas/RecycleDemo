package com.recyclerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.recyclerdemo.view.MyBanner;
import com.recyclerdemo.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chris
 */
public class BannerActivity extends AppCompatActivity implements OnItemClickListener{


    private MyBanner myBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        myBanner = (MyBanner)findViewById(R.id.mybanner);

        List<String> list = new ArrayList<>();
        list.add("http://img.jandan.net/news/2018/01/4c8dd177d3db19fb85a995c4dfff2fbf.jpg");
        list.add("http://img.jandan.net/news/2018/01/7843254260c49a426140ffa60a7169e5.jpg");
        list.add("http://img.jandan.net/news/2018/01/4594a564fc05e7f276958b4d19e9eab1.jpg");
        list.add("http://img.jandan.net/news/2018/01/1f446e78a7220597f2e3045f06638c42.jpg");

        myBanner.setItemList(list);
        myBanner.setAutoPlay(true);
        myBanner.setOnItemClickListener(this);


    }


    @Override
    public void onClick(int position) {
        Toast.makeText(BannerActivity.this,"click: "+position,Toast.LENGTH_SHORT).show();
    }
}
