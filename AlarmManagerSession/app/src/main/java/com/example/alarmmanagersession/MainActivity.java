package com.example.alarmmanagersession;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

    }

    @SuppressLint("ShortAlarm")
    public void startNotification(View view) {
        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(MainActivity.this,MyReceiver.class);
        PendingIntent p1 = PendingIntent.getBroadcast(MainActivity.this,0,i1,PendingIntent.FLAG_UPDATE_CURRENT);

        long timeAtButtonClick = System.currentTimeMillis();
        long intervalInMillis = 1000 * 5;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeAtButtonClick,intervalInMillis,p1);

         /* 1.ELAPSED_REALTIME—Fires the pending intent based on the amount of time since the device was booted, but doesn't wake up the device. The elapsed time includes any time during which the device was asleep.
            2.ELAPSED_REALTIME_WAKEUP—Wakes up the device and fires the pending intent after the specified length of time has elapsed since device boot.
            3.RTC—Fires the pending intent at the specified time but does not wake up the device.
            4.RTC_WAKEUP—Wakes up the device to fire the pending intent at the specified time.  */

    }



    public void stopNotification(View view) {
        Intent intent = new Intent();
        intent.setAction("com.subarno.notification");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent p1 = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
        alarmManager.cancel(p1);

    }


}
