package com.joker.demo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.demo.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mDatas;

    public RecyclerViewAdapter(Context context, ArrayList<String> datas){
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_view,parent,false);
        return new RvViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            RvViewHolder rvViewHolder = (RvViewHolder) holder;
            rvViewHolder.mTv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class RvViewHolder extends RecyclerView.ViewHolder{
        TextView mTv;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.item_tv);
            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mTv.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
