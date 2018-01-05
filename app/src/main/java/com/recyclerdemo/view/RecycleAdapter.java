package com.recyclerdemo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.recyclerdemo.R;

import java.util.List;

/**
 * Created by win on 2018/1/3.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private Context mContext;

    private List<String> mDataSet;

    private OnItemClickListener onItemClickListener;

    private int imageSize = 0;

    public RecycleAdapter(Context context)
    {
        mContext = context;
    }


    public void setmDataSet(List<String> dataSet)
    {
        mDataSet = dataSet;
        imageSize  = mDataSet.size();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return imageSize<2?1: Integer.MAX_VALUE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String url = mDataSet.get(position % imageSize);
        Glide.with(mContext).load(url).into(holder.imageView);

        if(onItemClickListener != null)
        {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(position % imageSize);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageView;

        public ViewHolder(View view)
        {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.image_view);
        }
    }


}



