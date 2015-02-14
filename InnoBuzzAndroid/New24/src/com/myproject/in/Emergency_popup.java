package com.myproject.in;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;


public class Emergency_popup extends Dialog implements OnClickListener {
  
    private Context context;
    Button _siren,_whistle,_cancel;
    MediaPlayer _mPlayer;
 
    public Emergency_popup(Context context){
            super(context);
            this.context = context;
            this._mPlayer=_mPlayer;
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
          
          
    }
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siren_popup);
        this. getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
         TextView text1=(TextView) findViewById(R.id.text1);
         text1.setText("Send Alert");
        _siren= (Button) findViewById(R.id.siren_btn);
        _siren.setText("Personal Emergency");
        _whistle= (Button) findViewById(R.id.whistle_btn);
        _whistle.setText("Medical Emergency");
        _cancel= (Button) findViewById(R.id.cancel__btn);
        _cancel.setText("Cancel");
        _siren.setOnClickListener(this);
        _whistle.setOnClickListener(this);
        _cancel.setOnClickListener(this);
       
       
    }
 
 
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.siren_btn:
			   
			 break;
			 
			case R.id.whistle_btn:
				
				
				break;
				
			case R.id.cancel__btn:
				
				 dismiss();
				break;
				
				
		}
		
	}
    
}




