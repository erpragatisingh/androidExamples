package tele.demo;

 
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class Inno_Teli_DemoActivity extends Activity {
  private TelephonyManager telephonyManager;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        TextView textView=(TextView)findViewById(R.id.text);
     String telephonyString= getTelephonyInformation();
     
     textView.setText(telephonyString);
     
     
     
    }
	private String getTelephonyInformation() {
		 
		// TODO Auto-generated method stub
		
		int callstate = telephonyManager.getCallState();
		String callstateString=null;
		switch (callstate) {
		case TelephonyManager.CALL_STATE_IDLE:
			callstateString = "IDLE";
			
			
			break;
			
		case TelephonyManager.CALL_STATE_OFFHOOK:
			callstateString = "Offhook/On Hold/Waiting";
			
			
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			callstateString = "RINGING";
			
			
			break;
			

		default:
			break;
		}
				 
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("call state"+ callstateString);
		 
		
		return sb.toString();
		
	}
}