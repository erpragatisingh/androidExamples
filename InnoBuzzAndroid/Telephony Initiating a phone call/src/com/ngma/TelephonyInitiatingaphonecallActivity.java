package com.ngma;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class TelephonyInitiatingaphonecallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("content://contacts/people/3"));
        startActivity(intent);
        
    }
}