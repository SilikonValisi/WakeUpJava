package com.example.wakeupjava;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("ALARM IS RINGING");
        Intent secondActivity = new Intent(context,SecondActivity.class);
        secondActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(secondActivity);
    }
}
