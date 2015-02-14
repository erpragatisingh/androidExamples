package com.myproject.in;

import java.io.IOException;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;


public class Siren_popup extends Dialog implements OnClickListener {
  
    private Context context;
    Button _siren,_whistle,_cancel;
    MediaPlayer _mPlayer;
 
    public Siren_popup(Context context,MediaPlayer _mPlayer){
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
        _siren= (Button) findViewById(R.id.siren_btn);
        _whistle= (Button) findViewById(R.id.whistle_btn);
        _cancel= (Button) findViewById(R.id.cancel__btn);
        _siren.setOnClickListener(this);
        _whistle.setOnClickListener(this);
        _cancel.setOnClickListener(this);
       
       
    }
 
 
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.siren_btn:
			    _mPlayer = MediaPlayer.create(context, R.raw.stop);
			try {_mPlayer.stop();
			    _mPlayer = MediaPlayer.create(context, R.raw.stop);
				_mPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_mPlayer.start();
			 break;
			 
			case R.id.whistle_btn:
				_mPlayer.stop();
				_mPlayer = MediaPlayer.create(context, R.raw.stop);
				try {
					_mPlayer.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_mPlayer.start();
				
				break;
				
			case R.id.cancel__btn:
				_mPlayer.stop();
				 dismiss();
				break;
				
				
		}
		
	}
    
}



