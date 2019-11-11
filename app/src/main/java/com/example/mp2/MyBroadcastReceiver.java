package com.example.mp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);

        Toast.makeText(context, "Nazwa: \"" + name + "\" cena: " + price, Toast.LENGTH_LONG).show();
    }
}
