package com.example.hid.lovemyself;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityDoNotBlameBinding;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.NotificationDBM;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DoNotBlameActivity extends NavigationActivity {

    private static final String TAG = DoNotBlameActivity.class.getSimpleName();
    ActivityDoNotBlameBinding activityDoNotBlameBinding;

    HiDUserInformationDAO dao;
    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    ArrayList<HiDUserInformation> notificationOJList = new ArrayList<>();
    ArrayList<String> situationList = new ArrayList<>();
    ArrayList<String> solutionsList = new ArrayList<>();
    List<NotificationDBM> notificationList = new ArrayList<>();
    String situation;
    String solution;
    Date today;
    SimpleDateFormat dateFormat;
    String todayDate;
    String key1, key2;

    private NotificationManagerCompat notificationManager;
    private EditText situationDB;
    private EditText describeDB;
    private Button btnSave, btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_do_not_blame);

        notificationManager = NotificationManagerCompat.from(this);
        dao = new HiDUserInformationDAO();
        loadData();
//        int sizeOfList = solutionsList.size();
//        int randomNum = ThreadLocalRandom.current().nextInt(0,sizeOfList - 1);

        activityDoNotBlameBinding = ActivityDoNotBlameBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_do_not_blame, frameLayout);

        situationDB = rootView.findViewById(R.id.edittxtDBSituation);
        describeDB = rootView.findViewById(R.id.edittxtDBDescribe);
        btnSave = rootView.findViewById(R.id.btnDoNotBSave);
        btnNotification = rootView.findViewById(R.id.btnDoNotBNotification);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNotification();
            }
        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNotificationChannels();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,00);
                calendar.set(Calendar.MINUTE,28);
                calendar.set(Calendar.SECOND,00);

                if(Calendar.getInstance().after(calendar)){
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                Intent intent = new Intent(DoNotBlameActivity.this, NotificationReceiver.class);
                intent.putExtra("toastMessage", "Happy Day");
                intent.putExtra("SITUATION", situationList.get(0));
                intent.putExtra("SOLUTION", solutionsList.get(0));
                Log.d(TAG, "situation intent: " + situationList.get(0));
                Log.d(TAG, "solutions intent: " + solutionsList.get(0));
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }
        });
    }


    private void createNotificationChannels() {
        //check the version because the certain version above the support this
        //the channel name will be displayed to the user
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "HiD";
            String description = "HiD's CHANNEL";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("HiD_Notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void saveNotification(){
//    private void saveNotification(NotificationDBM notificationDBM){
//        removeNotification();
        situation = situationDB.getText().toString();
        solution = describeDB.getText().toString();

        today = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("MM/dd/yy");
        todayDate = dateFormat.format(today);
        NotificationDBM notificationDBM = new NotificationDBM(todayDate, situation, solution);
//        notificationList.add(notificationDBM);

        dao.createNotification(key1, "NotificationDBM", notificationDBM).addOnSuccessListener(new OnSuccessListener<Void>() {
//        dao.createNotification("0", notificationDBM).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(DoNotBlameActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DoNotBlameActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void removeNotification(){

        dao.remove(key1, "NotificationDBM").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(DoNotBlameActivity.this, "Remove Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DoNotBlameActivity.this, "Remove Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                notificationOJList.clear();
                for(DataSnapshot data : snapshot.getChildren()){

                    key1 = data.getKey();
                    Log.d(TAG, "key1: " + key1);

//                    GenericTypeIndicator<HashMap<String, HiDUserInformation>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, HiDUserInformation>>() {};
//                    Map<String, HiDUserInformation> hiDUserInformationMap = data.getValue(objectsGTypeInd);
//                    ArrayList<HiDUserInformation> hiDUserInformationList = new ArrayList<HiDUserInformation>(hiDUserInformationMap.values());

                    Log.d(TAG,"Load data");
                    HiDUserInformation hiDUserInformation = data.getValue(HiDUserInformation.class);
                    userInforList.add(hiDUserInformation);
                    Log.d(TAG, "userInforList: " + userInforList.get(0).getUserEmail());


                    HiDUserInformation notification = new HiDUserInformation(hiDUserInformation.getUserEmail(), hiDUserInformation.getUserFirstName(), hiDUserInformation.getUserFirstName(), hiDUserInformation.getNotificationDBM());
                    notificationOJList.add(notification);
                    Log.d(TAG, "notificationOJList: " + notificationOJList.get(0).getNotificationDBM());

                    key2 = data.child("NotificationDBM").getKey();
                    Log.d(TAG, "key2: " + key2);


//                    List<NotificationDBM> notificationDBMs = notificationOJList.get(0).getNotificationDBM();
//                    //Log.d(TAG, "notification situation: " + notificationDBMs.get(0).getSituation());

                    getNotification();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load Notification List");
            }
        });
    }

    private void getNotification(){

        int size = notificationOJList.size();
        Log.d(TAG, "getNotification");
        for(int i = 0; i < size; i++){

//            Map<String, NotificationDBM> notificationDBMsMap = notificationOJList.get(i).getNotificationDBMMap();
//            ArrayList<NotificationDBM> objectArrayList = new ArrayList<NotificationDBM>();
//            objectArrayList.add(notificationDBMsMap.get(i));
//            Log.d(TAG, "objectArrayList" + notificationDBMsMap.get(i));

            List<NotificationDBM> notificationDBMs = notificationOJList.get(i).getNotificationDBM();
//            NotificationDBM notificationDBMs = notificationOJList.get(i).getNotificationDBM();
            notificationList.add(notificationDBMs.get(i));
//            notificationList.add(notificationDBMs.get(i));

            situationList.add(notificationList.get(i).getSituation());
//            situation = notificationDBMs.get(i).getSituation();
            Log.d(TAG, "situation: " +situationList.get(i));


//            solution = notificationDBMs.get(i).getSolution();
            solutionsList.add(notificationList.get(i).getSolution());
//            solutionsList.add(notificationDBMsMap.get(i).getSolution());
            Log.d(TAG, "solutions: " + solutionsList.get(i));
//            Log.d(TAG, "Notification Situation: " + notificationDBMs.get(i).getSituation());
//            Log.d(TAG, "Notification Solution: " + notificationDBMs.get(i).getSolution());

        }
    }

    public void sendOnChannel1(View v) {

        String title = situationDB.getText().toString();
        String message = describeDB.getText().toString();

        Intent intent = new Intent(this, DoNotBlameActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, NotificationApp.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.smileicon)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message).setBigContentTitle(title)
                        .setSummaryText("Hug myself"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        notificationManager.notify(1, notification);
    }


}