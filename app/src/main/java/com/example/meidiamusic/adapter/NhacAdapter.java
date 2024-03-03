package com.example.meidiamusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meidiamusic.R;
import com.example.meidiamusic.model.Nhac;

import java.util.ArrayList;

public class NhacAdapter extends RecyclerView.Adapter{
    ArrayList<Nhac> nhac;
    Context contex;

    public NhacAdapter(ArrayList<Nhac> nhac, Context contex) {
        this.nhac = nhac;
        this.contex = contex;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contex);
        View view = inflater.inflate(R.layout.item_layout_2column,parent,false);
        NhacViewHolder viewHolder = new NhacViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Nhac item = nhac.get(position);
        NhacViewHolder nhacViewHolder = (NhacViewHolder) holder;
        nhacViewHolder.txtItem2Columns.setText(item.getTitle());
        nhacViewHolder.imageViewItem2Columns.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return nhac.size();
    }

    public class NhacViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewItem2Columns;
        TextView txtItem2Columns;
        public NhacViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem2Columns = itemView.findViewById(R.id.imageViewItem2Columns);
            txtItem2Columns = itemView.findViewById(R.id.txtViewItem2Columns);
        }
    }
}
