package com.example.mp2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private int id = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        String channe1Id = "chan1";
        String channelName = "myChannel";

        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);

        Intent listIntent = new Intent(Intent.ACTION_MAIN);
        //listIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        listIntent.setComponent(new ComponentName("com.example.mp1", "com.example.mp1.ProductListActivity"));

        //Intent listIntent = context.getPackageManager().getLaunchIntentForPackage("com.example.mp1");

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, listIntent, 0);

        Toast.makeText(context, "Nazwa: \"" + name + "\" cena: " + price, Toast.LENGTH_LONG).show();
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context, channe1Id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Dodano nowy produkt")
                .setContentText("Nazwa: \"" + name + "\" Cena: \"" + price + "\"")
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channe1Id, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Simple notification");

            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(id++, notif.build());
    }
}
