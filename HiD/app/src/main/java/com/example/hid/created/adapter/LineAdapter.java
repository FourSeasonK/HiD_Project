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

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineViewHolder> {

    Context context;
    LineAdapterListener listener;
    ArrayList<Integer> lineColorsList;
    int row_seleted = -1;

    public LineAdapter(Context context, LineAdapterListener listener, ArrayList<Integer> lineColorsList) {
        this.context = context;
        this.listener = listener;
        this.lineColorsList = lineColorsList;
    }

    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_line_adapter, parent,false);

        return new LineAdapter.LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder holder, int position) {
        if(row_seleted == position){
            holder.line_check.setVisibility(View.VISIBLE);
        }else {
            holder.line_check.setVisibility(View.INVISIBLE);
        }

        holder.img_line.setImageResource(lineColorsList.get(position));
    }

    @Override
    public int getItemCount() {
        return lineColorsList.size();
    }

    public class LineViewHolder extends RecyclerView.ViewHolder{

        ImageView img_line, line_check;

       public LineViewHolder(@NonNull View itemView) {
           super(itemView);

           img_line = itemView.findViewById(R.id.img_line);
           line_check = itemView.findViewById(R.id.line_check);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   listener.onLineSelected(lineColorsList.get(getAdapterPosition()));
                   row_seleted = getAdapterPosition();
                   notifyDataSetChanged();
               }
           });
       }
   }

   public interface LineAdapterListener{
        void onLineSelected(int line);
   }
}