package demo.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class outgoingcall extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		 

		
		if(Intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))
			
		{
		String phonenumber =Intent.getIntentOld(uri)
		
		}
		
	 
	}

}
