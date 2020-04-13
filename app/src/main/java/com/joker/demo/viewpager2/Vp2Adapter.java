package com.joker.demo.viewpager2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joker.demo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Vp2Adapter extends RecyclerView.Adapter<Vp2Adapter.ViewHolder> {

    public List<String> colors;

    public Vp2Adapter(List<String> colors){
        this.colors=colors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vp2_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.layout.setBackgroundColor(Color.parseColor(colors.get(position)));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;

        public ViewHolder(View itemView){
            super(itemView);
            layout=itemView.findViewById(R.id.vp2_tiem_layout);
        }
    }
}
