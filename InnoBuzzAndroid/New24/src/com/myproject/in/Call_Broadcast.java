package com.myproject.in;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Call_Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		  Intent i=new Intent(context, Fake_Incoming_call_Screen.class);
		  Bundle bundle=intent.getExtras();
		  i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		  i.putExtras(bundle);
	      context.startActivity(i);
	}
}
