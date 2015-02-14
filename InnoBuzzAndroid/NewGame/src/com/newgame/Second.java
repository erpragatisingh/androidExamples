package com.newgame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity {
	
	EditText ed1;
	Button btn3;
	int num1,num2,inc=1,ch;
	TextView cha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		ed1=(EditText)findViewById(R.id.ed1);
		btn3=(Button)findViewById(R.id.btn3);
		cha=(TextView)findViewById(R.id.cha);
		
		
		//To create a random number 
        num2=(int)(Math.random()*100);
        //random number creation ends here
		

			
        	btn3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					num1=Integer.parseInt(ed1.getText().toString());
				
					if (inc <= 5) {
						ch = 5-inc;	
					
					if (num1 < num2) {
						
						Toast.makeText(Second.this, "your number is less than the guess",Toast.LENGTH_SHORT).show();
						//Toast.makeText(Second.this, "You have "+ ch + " chances left",Toast.LENGTH_SHORT).show();
						
					}
					else if (num1 > num2) {
						//inc++;
						//ch = 5-inc;
						
						Toast.makeText(Second.this, "your number is greater than the guess",Toast.LENGTH_SHORT).show();
						//Toast.makeText(Second.this, "You have "+ ch + " chances left",Toast.LENGTH_SHORT).show();
						
					}
					else if (num1 == num2) {
						Toast.makeText(Second.this, "you won the game",Toast.LENGTH_SHORT).show();
						
						Intent in3= new Intent(Second.this,Thanks.class);
						startActivity(in3);	
						
					}
					
					}
					
					
					else {
						Toast.makeText(Second.this, "you lose the game",Toast.LENGTH_SHORT).show();
						
						Intent in4=new Intent(Second.this,About.class);
						startActivity(in4);
					}
					
					
					inc++;
			
					cha.setText("You have "+ ch + " chances left");
					
					
					
				}
				
				
			});
        	
        	
        
        
            
        
	}

}
