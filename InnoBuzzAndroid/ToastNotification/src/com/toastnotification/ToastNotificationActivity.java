package com.toastnotification;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class ToastNotificationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Context context = this;
        CharSequence chrSeq = "this is a toast";
        int time = Toast.LENGTH_LONG;
        
        Toast toast = Toast.makeText(context, chrSeq, time);
        //for setting position of toast
       toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 30);
        
        toast.show();
    }
}