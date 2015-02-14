package com.progressbar1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class ProgressBar1Activity extends Activity {
	int myprogress=10;
	ProgressBar bar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bar=(ProgressBar) findViewById(R.id.progressbar1);
       new Thread(mythread).start();
       
    }
    Runnable mythread=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
                myHandle.sendMessage(myHandle.obtainMessage());
                Thread.sleep(1000);
        }
        catch(Throwable t){ }
		}
	};
	Handler myHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                myprogress++;
                bar.setProgress(myprogress);
        }
};
}