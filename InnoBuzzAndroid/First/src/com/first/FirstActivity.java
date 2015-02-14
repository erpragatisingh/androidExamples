package com.first;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity {
	EditText firstnum, secondnum;
	Button add;
	TextView sum;
	int num1,num2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        firstnum =(EditText)findViewById(R.id.ed1);
        secondnum =(EditText)findViewById(R.id.ed2);
        add=(Button)findViewById(R.id.btn1);
        sum=(TextView)findViewById(R.id.tv1);
 
        
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
			       
		      num1=Integer.parseInt(firstnum.getText().toString());
		      num2=Integer.parseInt(secondnum.getText().toString());
				int total=num1+num2;
				sum.setText(String.valueOf(total));
				
				

			}
		});
        
        
    }
}