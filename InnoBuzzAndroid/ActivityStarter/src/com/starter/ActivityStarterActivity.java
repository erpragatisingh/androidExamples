package com.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityStarterActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startActivity();
        
    }
    void startActivity(){
    	Intent myIntent=new Intent();
        myIntent.setAction(Intent.ACTION_CALL_BUTTON);
        startActivity(myIntent);
    }
}

