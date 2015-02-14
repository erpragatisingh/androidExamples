package com.yogi;

 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class YogiActivity extends Activity {
	Button submit;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
       
        submit=(Button)findViewById(R.id.btn1);
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				Intent in = new Intent(YogiActivity.this,Second.class);
				startActivity(in);
				
				
				
			}
		});
      
        
    }
}