package com.img;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IMGActivity extends Activity {
	
	Button btn1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn1=(Button)findViewById(R.id.btn1);
        
        btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in=new Intent(IMGActivity.this,innobuzz.class);
				startActivity(in);
			}
		});
        
        
    }
}