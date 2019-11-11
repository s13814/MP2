package com.example.mp2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private String channel = "chan1";
    private int id = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);

        Toast.makeText(context, "Nazwa: \"" + name + "\" cena: " + price, Toast.LENGTH_LONG).show();
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context, "chan1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Dodano nowy produkt")
                .setContentText("Nazwa: " + name + "Cena: " + price)
                .setAutoCancel(true);

        //if(Buil)
        //NotificationChannel channel = new NotificationChannel("chan1", channel, NotificationManager.IMPORTANCE_DEFAULT);
        //channel.setDescription(description);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(id++, notif.build());
    }
}
