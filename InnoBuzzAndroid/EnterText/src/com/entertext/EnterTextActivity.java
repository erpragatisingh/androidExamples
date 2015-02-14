package com.entertext;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterTextActivity extends Activity {
    /** Called when the activity is first created. */
	EditText ed1,ed2;
	Button btn;
	int num1, num2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ed1=(EditText)findViewById(R.id.ed1);
        
        ed2=(EditText)findViewById(R.id.ed2);
        
        btn=(Button)findViewById(R.id.btn1);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				num1=Integer.parseInt(ed1.getText().toString());
				num2=Integer.parseInt(ed2.getText().toString());
				
				int sum=num1+num2;
				String str=String.valueOf(sum);
				
				Intent in =new Intent(EnterTextActivity.this,Second.class); 
				Bundle bl=new Bundle();
				bl.putString("mykey", str);
				in.putExtras(bl);				
				startActivity(in);
				Toast ta=Toast.makeText(EnterTextActivity.this,"Add", Toast.LENGTH_SHORT);
				ta.show();
				
			}
		});
    }
}