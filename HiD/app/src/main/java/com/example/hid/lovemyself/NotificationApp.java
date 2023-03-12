package com.example.hid.lovemyself;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationApp extends Application {

    //create the channel
    public static final String CHANNEL_1_ID = "channel1";
    //public static final String CHANNEL_2_ID = "channel2";

    //start the activity
    @Override
    public void onCreate() {
        super.onCreate();
        
        createNotificationChannels();
    }

    private void createNotificationChannels() {

        //check the version because the certain version above the support this
        //the channel name will be displayed to the user
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Love myself",
                    NotificationManager.IMPORTANCE_HIGH
            );

           channel1.setDescription("Do not blame me");

           NotificationManager manager = getSystemService(NotificationManager.class);
           manager.createNotificationChannel(channel1);
        }
    }
}
