package com.example.hid.created.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hid.R;

import java.util.ArrayList;

public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder> {

    Context context;
    ShapeAdapterListener listener;
    ArrayList<Integer> shapeColorsList;
    int row_seleted = -1;

    public ShapeAdapter(Context context, ShapeAdapterListener listener, ArrayList<Integer> shapeColorsList) {
        this.context = context;
        this.listener = listener;
        this.shapeColorsList = shapeColorsList;
    }

    @NonNull
    @Override
    public ShapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_shape_adapter, parent,false);

        return new ShapeAdapter.ShapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeViewHolder holder, int position) {
        if(row_seleted == position){
            holder.shape_check.setVisibility(View.VISIBLE);
        }else {
            holder.shape_check.setVisibility(View.INVISIBLE);
        }

        holder.img_shape.setImageResource(shapeColorsList.get(position));
    }

    @Override
    public int getItemCount() {
        return shapeColorsList.size();
    }

    public class ShapeViewHolder extends RecyclerView.ViewHolder{

        ImageView img_shape, shape_check;

        public ShapeViewHolder(@NonNull View itemView) {
            super(itemView);

            img_shape = itemView.findViewById(R.id.img_shape);
            shape_check = itemView.findViewById(R.id.shape_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onShapeSelected(shapeColorsList.get(getAdapterPosition()));
                    row_seleted = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface ShapeAdapterListener{
        void onShapeSelected(int shape);
    }
}