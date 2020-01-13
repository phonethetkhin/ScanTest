package com.t3k.fams.scantest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {
    List<String> datallist;

    public MAdapter(List<String> datallist) {
        this.datallist = datallist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.resultlistitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvResult.setText(datallist.get(position));

    }

    @Override
    public int getItemCount() {
        return datallist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvResult;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvResult=v.findViewById(R.id.tvResult);
        }
    }

}
