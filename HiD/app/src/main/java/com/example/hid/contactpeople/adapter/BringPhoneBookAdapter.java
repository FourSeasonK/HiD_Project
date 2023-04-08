package com.example.hid.contactpeople.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.model.UserPhoneBook;

import java.util.ArrayList;

public class BringPhoneBookAdapter extends RecyclerView.Adapter<BringPhoneBookAdapter.bringPhoneBookViewHolder> {

    private static final String TAG = BringPhoneBookAdapter.class.getSimpleName();
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_List = 1;

    int index;
    private Context context;
    ArrayList<UserPhoneBook> phoneBookArrayList;
    public ListItemListener mListener;

    public BringPhoneBookAdapter(Context context, ArrayList<UserPhoneBook> phoneBookArrayList) {
        this.context = context;
        this.phoneBookArrayList = phoneBookArrayList;
    }

    public void setListener(ListItemListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public bringPhoneBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    public bringPhoneBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_phonebook_item,parent, false);
        return new BringPhoneBookAdapter.bringPhoneBookViewHolder(view);

//        if(viewType == TYPE_List){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_phonebook_item,parent, false);
//            return new BringPhoneBookAdapter.bringPhoneBookViewHolder(view, viewType);
//
//        } else if(viewType == TYPE_HEAD){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_phonebook_header, parent, false);
//            return new BringPhoneBookAdapter.bringPhoneBookViewHolder(view, viewType);
//        }
//
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull bringPhoneBookViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        if(holder.view_type == TYPE_List){
//
//            index = position - 1;
//
//            Log.d(TAG, "InADAPTER USERNAME: " + phoneBookArrayList.get(index).getUserName());
//            Log.d(TAG, "InADAPTER USEPHONENUMBER: " + phoneBookArrayList.get(index).getUserPhoneNumber());
//            holder.userName.setText(phoneBookArrayList.get(index).getUserName());
//            holder.userPhoneNumber.setText(phoneBookArrayList.get(index).getUserPhoneNumber());
//
//        }
//        else if(holder.view_type == TYPE_HEAD){
//            holder.userNameHeader.setText("Name");
//            holder.userPhoneNumberHeader.setText("Phone Number");
//        }

        Log.d(TAG, "InADAPTER USERNAME: " + phoneBookArrayList.get(position).getUserName());
        Log.d(TAG, "InADAPTER USEPHONENUMBER: " + phoneBookArrayList.get(position).getUserPhoneNumber());
        holder.userName.setText(phoneBookArrayList.get(position).getUserName());
        holder.userPhoneNumber.setText(phoneBookArrayList.get(position).getUserPhoneNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneBookArrayList.size();
//        return phoneBookArrayList.size() + 1;
    }

    class bringPhoneBookViewHolder extends RecyclerView.ViewHolder{

        int view_type;
        TextView userName, userPhoneNumber, userNameHeader, userPhoneNumberHeader;

        public bringPhoneBookViewHolder(@NonNull View itemView) {
//        public bringPhoneBookViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            userName = itemView.findViewById(R.id.txtNamePhone);
            userPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);

//            if(viewType == TYPE_List){
//                userName = itemView.findViewById(R.id.txtNamePhone);
//                userPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
//
//                viewType = 1;
//
//            } else if(viewType == TYPE_HEAD){
//                userNameHeader  = itemView.findViewById(R.id.txtNamePhoneHeader);
//                userPhoneNumberHeader = itemView.findViewById(R.id.txtPhoneNumberHeader);
//
//                viewType = 0;
//            }
        }
    }

    public interface ListItemListener {
        void onListItemClick(int position);
    }

//    @Override
//    public int getItemViewType(int position) {
//
//        if(position == 0)
//            return TYPE_HEAD;
//            return TYPE_List;
//
//    }
}
