package com.recyclerdemo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.recyclerdemo.R;

import java.util.List;

/**
 * @author Chris
 */

public class MyBanner extends FrameLayout {

    private static final String TAG = "MyBanner";

    private Context mContext;

    private RecyclerView recyclerView;

    private RecycleAdapter adapter;

    private int currentNumPosition = 0;

    private Boolean isAutoPlay = false;

    private int AUTO_PLAY = 0x0;

    private long delayMill = 2000;

    private LinearLayoutManager linearLayoutManager;

    private BannerLayoutManager layoutManager;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(isAutoPlay)
            {
               if(msg.what == AUTO_PLAY)
               {
                   Log.i(TAG, "smoothScrollToPosition: "+ currentNumPosition);
                   recyclerView.smoothScrollToPosition(currentNumPosition);
                   currentNumPosition++;


                   handler.sendEmptyMessageDelayed(AUTO_PLAY,delayMill);
               }
            }

            return false;
        }
    });

    public MyBanner(Context context) {
        this(context,null);
    }

    public MyBanner(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    private void init()
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_lay,this,true);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        layoutManager = new BannerLayoutManager();

        recyclerView.setLayoutManager(layoutManager);


        adapter = new RecycleAdapter(mContext);

        recyclerView.setAdapter(adapter);

        new PagerSnapHelper().attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentNumPosition = layoutManager.getCurrentPosition()+1;
                Log.i(TAG, "onScrolled: "+currentNumPosition);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

        });


    }


    public void setItemList(List<String> imageList)
    {
        adapter.setmDataSet(imageList);
        currentNumPosition = imageList.size()*1000;
        recyclerView.scrollToPosition(currentNumPosition-1);
        Log.i(TAG, "setItemList: "+currentNumPosition);
        recyclerView.smoothScrollToPosition(currentNumPosition);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        adapter.setOnItemClickListener(onItemClickListener);
    }
    public void setAutoPlay(Boolean isAutoPlay)
    {
        this.isAutoPlay = isAutoPlay;
        handler.sendEmptyMessageDelayed(AUTO_PLAY,delayMill);
    }


}
