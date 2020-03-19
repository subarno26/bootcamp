package com.example.alarmmanagersession;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.alarmmanagersession.App.CHANNEL_ID_1;
import static com.example.alarmmanagersession.App.CHANNEL_ID_2;

public class MyReceiver extends BroadcastReceiver {



//    private static final String CHANNEL_ID = "channel";
//    private static final String CHANNEL_ID_1 ="CHANNEL_1" ;
//    private static final String CHANNEL_ID_2 = "CHANNEL_2" ;

    NotificationManagerCompat manager;
    @Override
    public void onReceive(Context context, Intent intent) {



        Toast.makeText(context, "Receiver called", Toast.LENGTH_SHORT).show();
        manager = NotificationManagerCompat.from(context);
        //createChannels();
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context,CHANNEL_ID_1);
        builder1.setContentTitle("Notification 1 ")
                .setContentText("Sample notify 1")
               // .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background);

        Intent i1 = new Intent(context,MainActivity.class);
        PendingIntent pd1 = PendingIntent.getActivity(context,0,i1,0);
        builder1.setContentIntent(pd1);

        manager.notify(1,builder1.build());


//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context,CHANNEL_ID_2);
        builder2.setContentTitle("Notification 2 ")
                .setContentText("Sample notify 2")
                .setAutoCancel(true)
                //.setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_launcher_background);

        Intent i2 = new Intent(context,MainActivity.class);
        PendingIntent pd2 = PendingIntent.getActivity(context,0,i2,0);
        builder2.setContentIntent(pd2);

        manager.notify(2,builder2.build());






    }

//    private void createChannels() {
//
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
//            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID_1, "channel1", NotificationManager.IMPORTANCE_HIGH);
//            channel1.setDescription("Notification channel 1");
//            manager.createNotificationChannel(channel1);
//
//
//            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, "channel2", NotificationManager.IMPORTANCE_LOW);
//            channel2.setDescription("Notification channel 1");
//            manager.createNotificationChannel(channel2);
//
//        }
//    }


}
