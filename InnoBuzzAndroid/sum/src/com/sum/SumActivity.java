package com.sum;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SumActivity extends Activity {

	/** Called when the activity is first created. */
    EditText ed1,ed2;
    Button btn1;
	int num1,num2,sum;
	TextView tv1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ed1=(EditText)findViewById(R.id.et1);
        ed2=(EditText)findViewById(R.id.et2);
        btn1=(Button)findViewById(R.id.button);
        

       tv1=(TextView)findViewById(R.id.tv1);
       
       
      btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			num1= Integer.parseInt(ed1.getText().toString());
			num2= Integer.parseInt(ed2.getText().toString());
			sum=num1+num2;
			String str =String.valueOf(sum);
			tv1.setText(str);	
			
			
			Intent i=new Intent(SumActivity.this,second.class);
				Bundle bl= new Bundle();
				bl.putString("mykey", str);
				i.putExtras(bl);
				startActivity(i);
			}
		}); 
        
    }
}