package com.example.hid.created.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hid.R;

import java.util.ArrayList;
import java.util.List;

public class IconColorAcdapter extends RecyclerView.Adapter<IconColorAcdapter.EyeColorViewHolder> {

    Context context;
    List<Integer> colorEList;
    IconColorAcdapterListener listener;

    int row_seleted = -1;

    public IconColorAcdapter(Context context, IconColorAcdapterListener listener) {
        this.context = context;
        this.colorEList = getEColor();
        this.listener = listener;
    }

    private List<Integer> getEColor() {
        List<Integer> result = new ArrayList<>();
//        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.colorcircle);
//        DrawableCompat.setTint(drawable, Color.RED);
        result.add(R.drawable.circlered);
        result.add(R.drawable.circleorange);
        result.add(R.drawable.circleyellow);
        result.add(R.drawable.circlegreen);
        result.add(R.drawable.circleblue);
        result.add(R.drawable.circledarkblue);
        result.add(R.drawable.circlepurple);

        return result;
    }

    @NonNull
    @Override
    public EyeColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_eye_color_acdapter, parent,false);

        return new IconColorAcdapter.EyeColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EyeColorViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.colorEyes.setImageResource(colorEList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onColorSeledted(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  colorEList.size();
    }

    public class EyeColorViewHolder extends RecyclerView.ViewHolder{

        ImageView colorEyes;

        public EyeColorViewHolder(@NonNull View itemView) {
            super(itemView);

            colorEyes = itemView.findViewById(R.id.circleColorE);

        }
    }

    public interface IconColorAcdapterListener{
        void onColorSeledted(int index);
    }

}