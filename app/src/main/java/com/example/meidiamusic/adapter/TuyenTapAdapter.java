package com.example.meidiamusic.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meidiamusic.R;
import com.example.meidiamusic.model.Nhac;
import com.example.meidiamusic.model.TuyenTap;

import java.util.ArrayList;
import java.util.Collection;

public class TuyenTapAdapter extends RecyclerView.Adapter<TuyenTapAdapter.TuyenTapViewHolder>{
    ArrayList<TuyenTap> tuyentap;
    Context context;

    public TuyenTapAdapter(ArrayList<TuyenTap> tuyentap, Context context) {
        this.tuyentap = tuyentap;
        this.context = context;
    }

    @NonNull
    @Override
    public TuyenTapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout_horizontal1,parent,false);
        TuyenTapViewHolder tuyenTapViewHolder = new TuyenTapViewHolder(view);
        return tuyenTapViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuyenTapViewHolder holder, int position) {
        /*Nhac item = nhac.get(position);
        NhacAdapter.NhacViewHolder nhacViewHolder = (NhacAdapter.NhacViewHolder) holder;
        nhacViewHolder.txtItem2Columns.setText(item.getTitle());
        nhacViewHolder.imageViewItem2Columns.setImageResource(item.getImage());*/
        TuyenTap tt = tuyentap.get(position);
        holder.imageViewItemHorizontal.setImageResource(tt.getImageTT());
        holder.txtItemHorizontal.setText(tt.getNameTT());
    }

    @Override
    public int getItemCount() {
        return tuyentap.size();
    }

    public class TuyenTapViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewItemHorizontal;
        TextView txtItemHorizontal;
        public TuyenTapViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItemHorizontal = itemView.findViewById(R.id.imageViewItemHorizontal);
            txtItemHorizontal = itemView.findViewById(R.id.txtItemHorizontal);


        }
    }
}
