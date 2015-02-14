package com.status.ngma;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

public class StatusBarActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final int NOTIFICATION_ID = 0;
        int icon = R.drawable.icon;
        CharSequence tickerText = "this is the ticker text";
        Notification note =
        new Notification(icon, tickerText, System.currentTimeMillis());
        
        NotificationManager manager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Context context = getApplicationContext();
        Intent intent = new Intent(context, SomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
        
        note.setLatestEventInfo(context,"a notification", "this is the content text", contentIntent);
        manager.notify(NOTIFICATION_ID, note);
       
        note.sound =Uri.parse(Environment.getExternalStorageDirectory() + "/my_note.mp3");
    }
}