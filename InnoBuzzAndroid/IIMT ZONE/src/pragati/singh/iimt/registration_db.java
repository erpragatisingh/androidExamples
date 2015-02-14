package pragati.singh.iimt;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registration_db extends Activity{

	private statusDataSource datasource;
		
	//TextView resultAfterSubmit;
	EditText e_id,e_name, e_email, e_mNo, e_dob, e_remark;
	Button submit,cancel,search;
	Spinner spn;
	
	SQLiteDatabase db;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        //Open the Data base
       datasource = new statusDataSource(this);
        datasource.open();
        
        e_id=(EditText)findViewById(R.id.editText1);
		e_name=(EditText)findViewById(R.id.editText2);
		e_dob=(EditText)findViewById(R.id.editText3);
		e_mNo=(EditText)findViewById(R.id.editText4);
		e_email=(EditText)findViewById(R.id.editText5);
		spn=(Spinner)findViewById(R.id.spinner1);
		e_remark=(EditText)findViewById(R.id.editText6);
		submit=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		Spinner spinner=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<?> adapter=ArrayAdapter.createFromResource(getApplicationContext(), R.array.course, android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
     
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Map<String,String> l_info_map = new HashMap<String,String>();
				// TODO Auto-generated method stub
				
				String eid=String.valueOf(e_id.getText()).toString();
				String ename=String.valueOf(e_name.getText()).toString();
				String eemail=String.valueOf(e_email.getText()).toString();
				String emNo=String.valueOf(e_mNo.getText()).toString();
				String edob=String.valueOf(e_dob.getText()).toString();
				//spn.getItemAtPosition(0);
				String eremark=String.valueOf(e_remark.getText()).toString();
				l_info_map.put("serial_number",eid);
				l_info_map.put("emp_name",ename);
				l_info_map.put("dob",edob);
				l_info_map.put("mobile_number",emNo);
				l_info_map.put("email_id",eemail);
				l_info_map.put("remark",eremark);
				
				if(!validateInputInformation(l_info_map)){
					return;				
				}
				
				if((eid== "" && ename== "" && eemail== "" && emNo== "" && edob== "" && eremark== ""))
				{
					Toast.makeText(getApplicationContext(), "Please fill all the information", Toast.LENGTH_SHORT).show();
				}
				else
				{
					sendSMS(emNo,ename);
					insert(eid,ename,eemail,emNo,edob,eremark);
				}
				
			}

			private boolean validateInputInformation(
					Map<String, String> p_info_map) {
				String l_serial_number = p_info_map.get("serial_number");
				if(l_serial_number == null || l_serial_number.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Serial Number First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				String l_emp_name = p_info_map.get("emp_name");
				if(l_emp_name == null || l_emp_name.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Name First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				String l_dob = p_info_map.get("dob");
				if(l_dob == null || l_dob.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Date of birth First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				String l_mobile_number = p_info_map.get("mobile_number");
				if(l_mobile_number == null || l_mobile_number.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Name First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				String l_email_id = p_info_map.get("email_id");
				if(l_email_id == null || l_email_id.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Name First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				String l_remark = p_info_map.get("remark");
				if(l_remark == null || l_remark.equals("")) {
					Toast.makeText(getApplicationContext(), "Please fill Name First", Toast.LENGTH_SHORT).show();
					return false;					
				}
				
				return true;
			}
		});
        
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setType("image/*");
				i.setAction(Intent.ACTION_PICK);
				startActivityForResult(i, 1);
			}
		});
        
    }
    

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==RESULT_OK && requestCode==1)
		{
			
		Uri uri = data.getData();
		String path = getPath(uri);
		Bitmap b= BitmapFactory.decodeFile(path);
		//Bitmap b1 = Bitmap.
		
		File file = new File(path);
		
		
		}
	}

	private String getPath(Uri uri) {
		Cursor mCursor;
		mCursor = managedQuery(uri, new String[] {MediaColumns.DATA}, null, null, null);
		int column_index = mCursor.getColumnIndexOrThrow(MediaColumns.DATA);
		mCursor.moveToFirst();
		String image_path = mCursor.getString(column_index);
		return image_path;
	}


	protected void insert(String _ID, String ename, String eemail, String emNo,String edob, String eremark) 
	{
		long ctr= datasource.insertEnquiry(_ID, ename, eemail, emNo, edob, eremark);
		Toast.makeText(getApplicationContext(),"data inserted successfully",Toast.LENGTH_LONG).show();
	}
	
	@SuppressWarnings("deprecation")
	private void sendSMS(String emNO,String ename)
    {        
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";
 
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
            new Intent(SENT), 0);
 
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
            new Intent(DELIVERED), 0);
        
        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @SuppressWarnings("deprecation")
			@Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", 
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
 
        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered", 
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", 
                                Toast.LENGTH_SHORT).show();
                        break;                        
                }
            }
        }, new IntentFilter(DELIVERED));        
 
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(emNO, null, "Welcome "+ ename + "for Registering with us", sentPI, deliveredPI);      
        
        
    }

	
	public void menuCalling(View v)
	{
		Intent intent=new Intent(this,ProFile1Activity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}
	
	
}