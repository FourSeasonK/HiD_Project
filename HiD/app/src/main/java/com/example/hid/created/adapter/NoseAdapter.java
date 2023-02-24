package com.example.hid.created.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hid.R;

import java.util.ArrayList;

public class NoseAdapter extends RecyclerView.Adapter<NoseAdapter.NoseViewHolder> {

    Context context;
    NosesAdapterListener listener;
    ArrayList<Integer> noseColorsList;
    int row_seleted = -1;

    public NoseAdapter(Context context, NosesAdapterListener listener, ArrayList<Integer> noseColorsList) {
        this.context = context;
        this.listener = listener;
        this.noseColorsList = noseColorsList;
    }

    @NonNull
    @Override
    public NoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_nose_adapter, parent,false);

        return new NoseAdapter.NoseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoseViewHolder holder, int position) {

        if(row_seleted == position){
            holder.nose_check.setVisibility(View.VISIBLE);
        }else {
            holder.nose_check.setVisibility(View.INVISIBLE);
        }

        holder.img_nose.setImageResource(noseColorsList.get(position));

    }

    @Override
    public int getItemCount() {
        return noseColorsList.size();
    }

    public class NoseViewHolder extends RecyclerView.ViewHolder{

        ImageView img_nose, nose_check;

      public NoseViewHolder(@NonNull View itemView) {
          super(itemView);

          img_nose = itemView.findViewById(R.id.img_nose);
          nose_check = itemView.findViewById(R.id.nose_check);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  listener.onNoseSelected(noseColorsList.get(getAdapterPosition()));
                  row_seleted = getAdapterPosition();
                  notifyDataSetChanged();
              }
          });
      }
  }

    public interface NosesAdapterListener{
        void onNoseSelected(int nose);
    }
}