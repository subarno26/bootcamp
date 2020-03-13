package com.example.fcmsession;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.URL;

public class MessagingService extends FirebaseMessagingService {
    private static final String TAG = "ASHUTOSH";
    private static final int NOTIFICATION_ID =1 ;
    private String CHANNEL_ID = "Channel1";
    NotificationCompat.Builder builder;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title;
        String data;
        String imgURL;

        title = remoteMessage.getData().get("title");
        data = remoteMessage.getData().get("data");
        imgURL = remoteMessage.getData().get("url");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel",NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title",title);
        intent.putExtra("data",data);
        intent.putExtra("URL",imgURL);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(data)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getImageFromURL(imgURL)));

        manager.notify(NOTIFICATION_ID,builder.build());

    }

    private Bitmap getImageFromURL(String imgURL) {
        try{
            Bitmap bitmap = Glide.with(this).asBitmap().load(imgURL).into(100, 100).get();
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

    }
}
