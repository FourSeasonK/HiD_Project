package com.example.hid.tools;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditingToolsAdapter extends RecyclerView.Adapter<EditingToolsAdapter.toolsViewHolder> {


    @NonNull
    @Override
    public toolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull toolsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class toolsViewHolder extends  RecyclerView.ViewHolder{

        public toolsViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

        }

    }

}
