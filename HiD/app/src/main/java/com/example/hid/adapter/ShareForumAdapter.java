package com.example.hid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.model.Shareforum;

import java.util.ArrayList;

public class ShareForumAdapter extends RecyclerView.Adapter<ShareForumAdapter.shareForumViewHolder> {

    private static final String TAG = ShareForumAdapter.class.getSimpleName();
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_List = 1;

    int index;

    private Context context;
    ArrayList<Shareforum> sfArrayList;

    public ShareForumAdapter(Context context, ArrayList<Shareforum> sfArrayList) {
        this.context = context;
        this.sfArrayList = sfArrayList;
    }

    @NonNull
    @Override
    public shareForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TYPE_List){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item,parent,false);
            return new ShareForumAdapter.shareForumViewHolder(view, viewType);

        } else if(viewType == TYPE_HEAD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item_header,parent,false);
            return new ShareForumAdapter.shareForumViewHolder(view, viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull shareForumViewHolder holder, int position) {

        if(holder.view_type == TYPE_List){

            index = position-1;

            holder.txtSFNum.setText(sfArrayList.get(index).getNumber());
            holder.txtSFTitle.setText(sfArrayList.get(index).getTitle());
            holder.txtSFName.setText(sfArrayList.get(index).getName());
            holder.txtSFDate.setText(sfArrayList.get(index).getDateToday());
            holder.imgSFHeart.setImageResource(sfArrayList.get(index).getSfHeart());
            holder.imgSFStar.setImageResource(sfArrayList.get(index).getSfStar());

        }
//        else if(holder.view_type == TYPE_HEAD){
//
//            holder.txtSFNumH.setText("No");
//            holder.txtSFTitleH.setText("Title");
//            holder.txtSFNumH.setText("Name");
//            holder.txtSFDateH.setText("Date");
//            holder.txtSFReaction.setText("Reaction");
//        }


    }

    @Override
    public int getItemCount() {
        return sfArrayList.size() + 1;
    }


    class shareForumViewHolder extends RecyclerView.ViewHolder{

        int view_type;

        TextView txtSFNum, txtSFTitle, txtSFName, txtSFDate;
        ImageView imgSFHeart, imgSFStar;
        TextView txtSFNumH, txtSFTitleH, txtSFNameH, txtSFDateH, txtSFReaction;

        public shareForumViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            if(viewType == TYPE_List){
                txtSFNum = itemView.findViewById(R.id.txtSFNumH);
                txtSFTitle = itemView.findViewById(R.id.txtSFTitleH);
                txtSFName = itemView.findViewById(R.id.txtSFNameH);
                txtSFDate = itemView.findViewById(R.id.txtSFDateH);
                imgSFHeart = itemView.findViewById(R.id.imgSFHeart);
                imgSFStar = itemView.findViewById(R.id.imgSFStar);

                viewType = 1;

            }
            else if(viewType == TYPE_HEAD){
                txtSFNumH = itemView.findViewById(R.id.txtSFNumH);
                txtSFTitleH = itemView.findViewById(R.id.txtSFTitleH);
                txtSFNameH = itemView.findViewById(R.id.txtSFNameH);
                txtSFDateH = itemView.findViewById(R.id.txtSFDateH);
                txtSFReaction = itemView.findViewById(R.id.txtSFReaction);

                viewType = 0;
            }

        }
    }

    //Display Header
    @Override
    public int getItemViewType(int position) {

        if(position == 0)
            return TYPE_HEAD;
            return TYPE_List;

    }
}


