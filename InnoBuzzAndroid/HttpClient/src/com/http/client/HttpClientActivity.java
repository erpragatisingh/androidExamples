package com.http.client;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpClientActivity extends Activity {
	
	TextView httpStaff;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        httpStaff =(TextView)findViewById(R.id.tv);
        GetHttpMethods test= new GetHttpMethods();
        String returned;
		try {
			
			returned = test.getInternetData();
			httpStaff.setText(returned);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
        }
}