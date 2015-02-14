package com.ngma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class TelephonyListningForPhoneEventsActivity extends Activity {
	 PhoneStateListener phoneListener;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView textView =(TextView) findViewById(R.id.text_view);
        final TelephonyManager phoneManager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        
        Intent intent = new Intent(Intent.ACTION_CALL_BUTTON);
        startActivity(intent);
        
       
		phoneManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
        
        PhoneStateListener phoneListener = new PhoneStateListener() {
        	@Override
        	public void onCallStateChanged(int state, String number) {
        	String phoneState = number;
        	switch (state) {
        	case TelephonyManager.CALL_STATE_IDLE:
        	phoneState += " idle\n";
        	case TelephonyManager.CALL_STATE_RINGING:
        	phoneState += " ringing\n";
        	case TelephonyManager.CALL_STATE_OFFHOOK:
        	phoneState += " off hook\n";
        	}
        	textView.append(phoneState);
        	}
        	};
        }
         
    }
