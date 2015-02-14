package com.text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class TextActivity extends Activity {
    /** Called when the activity is first created. */
   Button abc;
	@Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    abc= (Button)findViewById(R.id.abc);
    abc.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent aa=new Intent(TextActivity.this,text1.class);
			startActivity(aa);
			
		}
    });
    
    
    }
}