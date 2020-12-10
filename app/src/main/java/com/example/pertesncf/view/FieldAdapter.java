package com.example.pertesncf.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertesncf.R;
import com.example.pertesncf.model.beans.Fields;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private ArrayList<Fields> fields;

    public FieldAdapter(ArrayList<Fields> fields) {
        this.fields = fields;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_item,parent,false);
        return new FieldAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Fields field = fields.get(position);
        holder.tv_date.setText(field.getDate());
        holder.tv_station.setText(field.getGc_obo_gare_origine_r_name());
        holder.tv_type.setText(field.getGc_obo_nature_c());

    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_date, tv_type, tv_station;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_date = itemView.findViewById(R.id.tv_date);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_station = itemView.findViewById(R.id.tv_station);
        }
    }
}
