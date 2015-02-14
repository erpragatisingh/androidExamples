package com.geocoder;


import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeocoderActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText ed=(EditText)findViewById(R.id.ed);
        Button fetch=(Button)findViewById(R.id.btn1);
        
        
        fetch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			Geocoder geo=new Geocoder(GeocoderActivity.this);
			String address=ed.getText().toString();
			
			List<Address> locations=null;
			try {
				
				locations=geo.getFromLocationName(address, 10);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			finally {
				
				double latitude=locations.get(0).getLatitude();
				double longitude=locations.get(0).getLongitude();
				
				TextView tv=(TextView)findViewById(R.id.tv);
				tv.setText("latitude :" + latitude + "longitude :" + longitude);
				
			}
				
			}
		});
    }
}