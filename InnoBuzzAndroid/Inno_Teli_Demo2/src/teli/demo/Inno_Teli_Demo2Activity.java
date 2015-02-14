 package teli.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

 
public class Inno_Teli_Demo2Activity extends Activity {
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
		GsmCellLocation cellLocation=(GsmCellLocation)telephonyManager.getCellLocation();
		String celllocationString=cellLocation.getLac()+" " + cellLocation.getCid();
		String deviceId = telephonyManager.getDeviceId();
		String devicesoftinfo = telephonyManager.getDeviceSoftwareVersion();
		String lineNumber = telephonyManager.getLine1Number();
		String networkcountry=telephonyManager.getNetworkCountryIso();
		String networkOpertor=telephonyManager.getNetworkOperator();
		String networkOperatorName=telephonyManager.getNetworkOperatorName();
		String simOperatirname=telephonyManager.getSimOperator();
		 
		StringBuilder sb=new StringBuilder();
		
		sb.append("call state"+ callstateString);
		sb.append("\n Cell Location "+ celllocationString);
		sb.append("\n device Id  " + deviceId);
		sb.append("\n Device software version " +devicesoftinfo);
		sb.append("\n Line number " +lineNumber);
		sb.append("networkcountry " + networkcountry);
		sb.append("NetworkoperatorName  " + networkOperatorName);
		sb.append(" SIM Operator Name" + simOperatirname);
		 
		
		return sb.toString();
		
	}
}