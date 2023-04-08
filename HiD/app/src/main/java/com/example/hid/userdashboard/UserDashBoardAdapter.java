package com.example.hid.userdashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.model.BoxBreathing;
import com.example.hid.model.ContactPeople;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.MyDDiary;
import com.example.hid.model.NotificationDBM;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDashBoardAdapter extends RecyclerView.Adapter<UserDashBoardAdapter.userDashBoardViewHolder> {

    private static final String TAG = UserDashBoardAdapter.class.getSimpleName();

    private Context context;
//    ArrayList<HiDUserInformation> hiDArrayList;
    List<BoxBreathing> boxBreathingList;
    List<ContactPeople> contactPeopleList;
    List<NotificationDBM> notificationDBMList;
    List<MyDDiary> myDDiaryList;
    List<String> dateList;
    int maxNum;

//    public UserDashBoardAdapter(Context context, ArrayList<HiDUserInformation> hiDArrayList) {
//        this.context = context;
//        this.hiDArrayList = hiDArrayList;
//    }

    public UserDashBoardAdapter(Context context, List<BoxBreathing> boxBreathingList, List<ContactPeople> contactPeopleList, List<NotificationDBM> notificationDBMList, List<MyDDiary> myDDiaryList, List<String> dateListAll, int maxNum) {
        this.context = context;
        this.boxBreathingList = boxBreathingList;
        this.contactPeopleList = contactPeopleList;
        this.notificationDBMList = notificationDBMList;
        this.myDDiaryList = myDDiaryList;
        this.dateList = dateListAll;
        this.maxNum = maxNum;
    }

    @NonNull
    @Override
    public userDashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.userdashboard_itemlayout,parent, false);
        return new UserDashBoardAdapter.userDashBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userDashBoardViewHolder holder, int position) {

        String date = dateList.get(position);
        holder.txtUserDDate.setText(date);

//        String date = boxBreathingList.get(position).getBreathDate();
//        holder.txtUserDDate.setText(date);
//        holder.txtBoxbreathRound.setText(String.valueOf(boxBreathingList.get(position).getBreathNumTimes()));

        if(boxBreathingList.size() < position + 1){
            holder.txtBoxbreathRound.setVisibility(View.GONE);

            holder.BoxbreathRoundLabel.setVisibility(View.GONE);

        }else if(boxBreathingList.size() >= position + 1){

            if(date.equals(boxBreathingList.get(position).getBreathDate())) {
                holder.txtBoxbreathRound.setText(String.valueOf(boxBreathingList.get(position).getBreathNumTimes()));
            }
        }

        if(contactPeopleList.size() < position + 1){
            holder.txtContactPMethod.setVisibility(View.GONE);

            holder.ContactPMethodLabel.setVisibility(View.GONE);

        }else if(contactPeopleList.size() >= position + 1){
            if(date.equals(contactPeopleList.get(position).getContactDate())){
                holder.txtContactPMethod.setText(contactPeopleList.get(position).getReceiverName() +",  " + contactPeopleList.get(position).getMethod());
            }
        }

        if(notificationDBMList.size() < position + 1){
            holder.txtSituation.setVisibility(View.GONE);
            holder.txtSolution.setVisibility(View.GONE);

            holder.SituationLabel.setVisibility(View.GONE);
            holder.SolutionLabel.setVisibility(View.GONE);
            holder.notificationTitle.setVisibility(View.GONE);

        } else if(notificationDBMList.size() >= position + 1){
            if(date.equals(notificationDBMList.get(position).getNotifiDate())){
                holder.txtSituation.setText(notificationDBMList.get(position).getSituation());
                holder.txtSolution.setText(notificationDBMList.get(position).getSolution());
            }
        }

        if(myDDiaryList.size() < position + 1){
            holder.txtTitle.setVisibility(View.GONE);
            holder.txtMood.setVisibility(View.GONE);
            holder.txtDName.setVisibility(View.GONE);
            holder.txtContents.setVisibility(View.GONE);
//            holder.imageD.setImageResource(0);
            holder.imageD.setVisibility(View.GONE);

            holder.TitleLabel.setVisibility(View.GONE);
            holder.MoodLabel.setVisibility(View.GONE);
            holder.DNameLabel.setVisibility(View.GONE);
            holder.ContentsLabel.setVisibility(View.GONE);
            holder.myDDiaryTitle.setVisibility(View.GONE);

        }else if(myDDiaryList.size() >= position + 1){
            if(date.equals(myDDiaryList.get(position).getMyDDate())){
                holder.txtTitle.setText(myDDiaryList.get(position).getTitle());
                holder.txtMood.setText(myDDiaryList.get(position).getMyMood());
                holder.txtDName.setText(myDDiaryList.get(position).getMyDName());
                holder.txtContents.setText(myDDiaryList.get(position).getMyDContents());
                //holder.imageD.setImageURI(myDDiaryList.get(position).getMyDImageUrl());
                Picasso.get().load(myDDiaryList.get(position).getMyDImageUrl()).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(holder.imageD);

            }
        }
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
//    public int getItemCount() {
//        return maxNum;
//    }

    class userDashBoardViewHolder extends RecyclerView.ViewHolder{

        TextView txtUserDDate, txtBoxbreathRound, txtContactPMethod, txtSituation, txtSolution, txtTitle, txtMood, txtDName, txtContents;
        ImageView imageD;
        TextView BoxbreathRoundLabel, ContactPMethodLabel, SituationLabel, SolutionLabel, TitleLabel, MoodLabel, DNameLabel, ContentsLabel;
        TextView notificationTitle, myDDiaryTitle;

        public userDashBoardViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserDDate = itemView.findViewById(R.id.txtUDDate);
            txtBoxbreathRound = itemView.findViewById(R.id.txtBoxBreathRoundNum);
            txtContactPMethod = itemView.findViewById(R.id.txtContacPeopleMethod);
            txtSituation = itemView.findViewById(R.id.txtNotiSituation);
            txtSolution = itemView.findViewById(R.id.txtNotiSolution);
            txtTitle = itemView.findViewById(R.id.txtMyDTitle);
            txtMood = itemView.findViewById(R.id.txtMyDMyMood);
            txtDName = itemView.findViewById(R.id.txtMyDName);
            txtContents = itemView.findViewById(R.id.txtMyDContents);
            imageD = itemView.findViewById(R.id.imgMyDUpload);

            BoxbreathRoundLabel = itemView.findViewById(R.id.txtBoxBreathTitle);
            ContactPMethodLabel = itemView.findViewById(R.id.txtContacPeopleTitle);
            SituationLabel = itemView.findViewById(R.id.txtNotiSituationTitle);
            SolutionLabel = itemView.findViewById(R.id.txtNotiSolutionTitle);
            TitleLabel = itemView.findViewById(R.id.txtMyDTitleLable);
            MoodLabel = itemView.findViewById(R.id.txtMyDMyMoodTitle);
            DNameLabel = itemView.findViewById(R.id.txtMyDNameTitle);
            ContentsLabel = itemView.findViewById(R.id.txtMyDContentsTitle);

            notificationTitle = itemView.findViewById(R.id.txtNotificationTitle);
            myDDiaryTitle = itemView.findViewById(R.id.txtMyDDiaryTitle);


        }
    }


}
