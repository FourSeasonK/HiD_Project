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

public class EyesAdapter extends RecyclerView.Adapter<EyesAdapter.EyesViewHolder> {

    Context context;
    List<Integer> eyesList;
    EyesAdapterListener listener;
    int colornum = -1;
    ArrayList<Integer> eyeColorsList;

    int row_seleted = -1;

//    public EyesAdapter(Context context, EyesAdapterListener listener) {
//        this.context = context;
////        this.eyesList = getEyeList();
//        this.listener = listener;
//    }

    public EyesAdapter(Context context, EyesAdapterListener listener, ArrayList<Integer> eyeColorsList) {
        this.context = context;
//        this.eyesList = getEyeList();
        this.listener = listener;
//        this.colornum = colornum;
        this.eyeColorsList = eyeColorsList;
    }

//    private List<Integer> getEyeList() {
//        List<Integer> result = new ArrayList<>();
//
////        if(colornum == 1){
////            result.add(R.drawable.eyered1);
////            result.add(R.drawable.eyered2);
////            result.add(R.drawable.eyered3);
////            result.add(R.drawable.eyered4);
////            result.add(R.drawable.eyered5);
////        }
//
//        result.add(R.drawable.eye1);
//        result.add(R.drawable.eye2);
//        result.add(R.drawable.eye3);
//        result.add(R.drawable.eye4);
//        result.add(R.drawable.eye5);
//
//        return result;
//    }

    @NonNull
    @Override
    public EyesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_eyes_adapter, parent,false);

        return new EyesAdapter.EyesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EyesViewHolder holder, int position) {

        if(row_seleted == position){
            holder.eye_check.setVisibility(View.VISIBLE);
        }else {
            holder.eye_check.setVisibility(View.INVISIBLE);
        }

        holder.img_eyes.setImageResource(eyeColorsList.get(position));

    }

    @Override
    public int getItemCount() {
        return eyeColorsList.size();
    }

    public class EyesViewHolder extends RecyclerView.ViewHolder{

        ImageView eye_check, img_eyes;

        public EyesViewHolder(@NonNull View itemView) {
            super(itemView);

            eye_check = itemView.findViewById(R.id.eye_check);
            img_eyes = itemView.findViewById(R.id.img_eye);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEyeSelected(eyeColorsList.get(getAdapterPosition()));
                    row_seleted = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface EyesAdapterListener{
        void onEyeSelected(int eye);
    }

}