package com.example.mp2;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationService extends IntentService {

    private int id = 0;

    public NotificationService(){
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String channe1Id = "chan1";
        String channelName = "myChannel";

        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);

        Intent listIntent = new Intent(Intent.ACTION_MAIN);
        //listIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        listIntent.setComponent(new ComponentName("com.example.mp1", "com.example.mp1.ProductListActivity"));

        //Intent listIntent = context.getPackageManager().getLaunchIntentForPackage("com.example.mp1");

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, listIntent, 0);

        Toast.makeText(this, "Nazwa: \"" + name + "\" cena: " + price, Toast.LENGTH_LONG).show();
        NotificationCompat.Builder notif = new NotificationCompat.Builder(this, channe1Id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Dodano nowy produkt")
                .setContentText("Nazwa: \"" + name + "\" Cena: \"" + price + "\"")
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channe1Id, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Simple notification");

            NotificationManager nm = this.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(id++, notif.build());
    }
}
