package com.example.hid.lovemyself;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.hid.R;
import com.example.hid.activities.HomeActivity;
import com.example.hid.activities.HomeActivityLogInD;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.boxbreath.BoxBreathingActivityLogIn;
import com.example.hid.created.CreateMyDActivity;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityDoNotBlameBinding;
import com.example.hid.dialog.DoNotBlameDialog;
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
//public class DoNotBlameActivity extends NavigationActivity implements DoNotBlameDialog.DoNotBlameStartListener {

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
    boolean doNotShowAgian;

    private NotificationManagerCompat notificationManager;
    private EditText situationDB;
    private EditText describeDB;
    private Button btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_do_not_blame);

        notificationManager = NotificationManagerCompat.from(this);
        dao = new HiDUserInformationDAO();

//        int sizeOfList = solutionsList.size();
//        int randomNum = ThreadLocalRandom.current().nextInt(0,sizeOfList - 1);

        activityDoNotBlameBinding = ActivityDoNotBlameBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_do_not_blame, frameLayout);

        situationDB = rootView.findViewById(R.id.edittxtDBSituation);
        describeDB = rootView.findViewById(R.id.edittxtDBDescribe);
        btnNotification = rootView.findViewById(R.id.btnDoNotBNotification);

        /////To Test the App/////
//        removeDataFromPref(DoNotBlameActivity.this);
        openDoNotBlameDialog();

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                situation = situationDB.getText().toString();
                solution = describeDB.getText().toString();

                createNotificationChannels();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,21);
                calendar.set(Calendar.MINUTE,15);
                calendar.set(Calendar.SECOND,00);

                if(Calendar.getInstance().after(calendar)){
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                if(situation.equals("") && solution.equals("")){
                    Toast.makeText(DoNotBlameActivity.this, "Please type the situation and solution", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(DoNotBlameActivity.this, NotificationReceiver.class);
                    intent.putExtra("toastMessage", "Happy Day");
                    intent.putExtra("SITUATION", situation);
                    intent.putExtra("SOLUTION", solution);
                    //                intent.putExtra("SITUATION", situationList.get(0));
                    //                intent.putExtra("SOLUTION", solutionsList.get(0));
                    Log.d(TAG, "situation intent: " + situation);
                    Log.d(TAG, "solutions intent: " + solution);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        Toast.makeText(DoNotBlameActivity.this, "Set Notification Success", Toast.LENGTH_SHORT).show();
                    }
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

    public void openDoNotBlameDialog(){
//        DoNotBlameDialog doNotBlameDialog = new DoNotBlameDialog();
//        doNotBlameDialog.show(getSupportFragmentManager(), "Start Love yourself");

        final AlertDialog.Builder adb = new AlertDialog.Builder(DoNotBlameActivity.this);
        LayoutInflater adbInflater = LayoutInflater.from(DoNotBlameActivity.this);
        View eulaLayout = adbInflater.inflate(R.layout.layout_donotblame_dialog, null);

        adb.setView(eulaLayout);
        adb.setTitle("Hug Myself");
        adb.setMessage("\nDo you blame yourself for something or find yourself in an unbearable situation? \nLeave yourself a message of encouragement. \n\n" +
                "By clicking the \"SET NOTIFICATION\" button, you will receive a notification of the content you wrote every morning at 8:00 AM.");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        adb.setNegativeButton("Do not show again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                doNotShowAgian = true;
                SharedPreferences settings = getSharedPreferences("DoNotBlame", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("skipMessage", doNotShowAgian);
                editor.commit();
                dialog.cancel();
            }
        });
        SharedPreferences settings = getSharedPreferences("DoNotBlame", 0);
        Boolean skipMessage = settings.getBoolean("skipMessage", false);

        if (skipMessage.equals(false)) {
            adb.show();
        }
    }

    //to test the app
    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("DoNotBlame", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("skipMessage");
        editor.commit();
    }

//    @Override
//    public void startDoNotBlame(boolean checkCancel) {
//
//        if(checkCancel){
//            Intent intent = new Intent(DoNotBlameActivity.this, HomeActivity.class);
//            startActivity(intent);
//        }
//    }
}