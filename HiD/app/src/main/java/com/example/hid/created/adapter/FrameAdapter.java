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
import java.util.List;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.FrameViewHolder> {

    Context context;
    List<Integer> frameList;
    FrameAdapterListener listener;

    int row_seleted = -1;

    public FrameAdapter(Context context, FrameAdapterListener listener) {
        this.context = context;
        this.frameList = getFrameList();
        this.listener = listener;
    }

    public List<Integer> getFrameList(){
        List<Integer> result = new ArrayList<>();
        result.add(R.drawable.frame01);
        result.add(R.drawable.frame02);
        result.add(R.drawable.frame03);
        result.add(R.drawable.frame04);
        result.add(R.drawable.frame05);
        result.add(R.drawable.frame06);
        result.add(R.drawable.frame07);
        result.add(R.drawable.frame08);
        result.add(R.drawable.frame09);
        result.add(R.drawable.frame10);
        result.add(R.drawable.frame11);
        result.add(R.drawable.frame12);
        result.add(R.drawable.frame13);
        result.add(R.drawable.frame14);
        result.add(R.drawable.frame15);
        result.add(R.drawable.frame16);
        result.add(R.drawable.frame17);
        result.add(R.drawable.frame18);

        return result;
    }

    @NonNull
    @Override
    public FrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_frame_adapter, parent,false);

        return new FrameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameViewHolder holder, int position) {

        if(row_seleted == position){
            holder.img_check.setVisibility(View.VISIBLE);
        }else {
            holder.img_check.setVisibility(View.INVISIBLE);
        }

        holder.img_frame.setImageResource(frameList.get(position));

    }

    @Override
    public int getItemCount() {
        return frameList.size();
    }

    public class FrameViewHolder extends RecyclerView.ViewHolder{

//        FrameLayout frameLayout;
        ImageView img_check, img_frame;


        public FrameViewHolder(@NonNull View itemView) {
            super(itemView);

            img_check = itemView.findViewById(R.id.icon_check);
            img_frame = itemView.findViewById(R.id.img_frame);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFrameSelected(frameList.get(getAdapterPosition()));
                    row_seleted = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface FrameAdapterListener{
        void onFrameSelected(int frame);
    }
}