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

public class LipAdapter extends RecyclerView.Adapter<LipAdapter.LipViewHolder> {

    Context context;
    LipAdapterListener listener;
    ArrayList<Integer> lipColorsList;
    int row_seleted = -1;

    public LipAdapter(Context context, LipAdapterListener listener, ArrayList<Integer> lipColorsList) {
        this.context = context;
        this.listener = listener;
        this.lipColorsList = lipColorsList;
    }

    @NonNull
    @Override
    public LipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_lip_adapter, parent,false);

        return new LipAdapter.LipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LipViewHolder holder, int position) {
        if(row_seleted == position){
            holder.lip_check.setVisibility(View.VISIBLE);
        }else {
            holder.lip_check.setVisibility(View.INVISIBLE);
        }

        holder.img_lip.setImageResource(lipColorsList.get(position));
    }

    @Override
    public int getItemCount() {
        return lipColorsList.size();
    }

    public class LipViewHolder extends RecyclerView.ViewHolder{

        ImageView img_lip, lip_check;

        public LipViewHolder(@NonNull View itemView) {
            super(itemView);

            img_lip = itemView.findViewById(R.id.img_lip);
            lip_check = itemView.findViewById(R.id.lip_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onLipSelected(lipColorsList.get(getAdapterPosition()));
                    row_seleted = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface LipAdapterListener{
        void onLipSelected(int lip);
    }

}