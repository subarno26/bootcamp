package com.example.alarmmanagersession;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class App extends Application {

    public static final String CHANNEL_ID_1 ="CHANNEL_1" ;
    public static final String CHANNEL_ID_2 = "CHANNEL_2" ;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID_1, "channel1", NotificationManager.IMPORTANCE_HIGH);
            //IMPORTANCE_HIGH : Makes a sounds and creates a pop up notification

            channel1.setDescription("Notification channel 1");



            NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID_2, "channel2", NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("Notification channel 1");
            //IMPORTANCE_LOW :  Used for creating a silent notification with no sound.

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel1);

        }
    }
}
