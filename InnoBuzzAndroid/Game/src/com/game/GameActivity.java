package com.game;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	EditText ed1;
	Button btn1;
	int num1,num2,inc;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        ed1=(EditText)findViewById(R.id.ed1);
        btn1=(Button)findViewById(R.id.btn1);
        
        //To create a random number 
        num2=(int)(Math.random()*100);
        //random number creation ends here
        
        
        
        if (inc < 5) {
            //creating an onclicklistener for the button
            btn1.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				 num1=Integer.parseInt(ed1.getText().toString());
    				
    				 if (num1 > num2) {
    					 
    					 Toast.makeText(GameActivity.this, "your number is greater than the guess : "+ num1,Toast.LENGTH_SHORT).show();
    					 Toast.makeText(GameActivity.this, "Try again",Toast.LENGTH_SHORT).show();
    				}
    				 else if (num2 > num1) {
    					 Toast.makeText(GameActivity.this, "your number is less than the guess",Toast.LENGTH_SHORT).show();
    				}
    				 
    				 else if (num1 == num2) {
    					 Toast.makeText(GameActivity.this, "you won the game",Toast.LENGTH_SHORT).show();
					}
    				 
    				 
    			}
    		});
             inc++;
            
		}
        
        else {
        	Toast.makeText(GameActivity.this, "you lost the game",Toast.LENGTH_SHORT).show();
		}
        

        
        
    }
}