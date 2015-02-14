package com.Innobuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class InnobuzzActivity extends Activity {
	private static int progress = 0;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
      
      
        
        
        new Thread(new Runnable() 
        {
            public void run() 
            {
                //---do some work here---
                while (progressStatus < 10) 
                {
                    progressStatus = taskToDo();
                }
                
                handler.post(new Runnable() 
                {
                    public void run() 
                    {
                        progressBar.setVisibility(View.GONE);
                        Intent in= new Intent(InnobuzzActivity.this,Inno.class);
                        startActivity(in);
                    }
                });
            }    
            
            private int taskToDo() 
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                return ++progress;
            }
 
        }).start(); 
        
       
 
        
        
    }
}