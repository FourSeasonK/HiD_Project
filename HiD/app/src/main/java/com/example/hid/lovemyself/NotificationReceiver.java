package com.example.hid.lovemyself;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.hid.R;
import com.example.hid.activities.LogInOutActivity;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String TAG = NotificationReceiver.class.getSimpleName();

    String situation;
    String solution;

    @Override
    public void onReceive(Context context, Intent intent) {

//        String message = intent.getStringExtra("toastMessage");
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        situation = intent.getStringExtra("SITUATION");
        solution = intent.getStringExtra("SOLUTION");

        Intent repeating_Intent = new Intent(context, LogInOutActivity.class);
        repeating_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeating_Intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "HiD_Notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.smileicon)
                .setContentTitle(situation)
                .setContentText(solution)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(solution).setBigContentTitle(situation)
                        .setSummaryText("Hug myself"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());

    }

}

