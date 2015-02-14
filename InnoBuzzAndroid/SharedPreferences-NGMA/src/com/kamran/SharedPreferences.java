package com.kamran;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.sataj.sharedpreferences.R;

public class SharedPreferences extends Activity {
    /** Called when the activity is first created. */
	private android.content.SharedPreferences prefs;
	private String prefName = "MyPref";
	private EditText editText;
	private SeekBar seekBar;
	private Button btn;
	
	private static final String FONT_SIZE_KEY = "fontsize";//key for the font size
	private static final String TEXT_VALUE_KEY = "textvalue";//key for the text inputed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        editText = (EditText) findViewById(R.id.editText1);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        btn = (Button) findViewById(R.id.button1);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//---get the SharedPreferences object---
				prefs =  getSharedPreferences(prefName, MODE_PRIVATE);
				Editor editor = prefs.edit();
				
				//---save the values in the EditText view to preferences---
				editor.putFloat(FONT_SIZE_KEY, editText.getTextSize());
				editor.putString(TEXT_VALUE_KEY, editText.getText().toString());
				
				//---saves the values---
				editor.commit();
				//---display file saved message---
				Toast.makeText(getBaseContext(),"Font size saved successfully!",Toast.LENGTH_SHORT).show();
				
			}
		});
        
      //---load the SharedPreferences object---
         prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        //---set the TextView font size to the previously saved values---
        float fontSize = prefs.getFloat(FONT_SIZE_KEY, 0);
        //---init the SeekBar and EditText---
        seekBar.setProgress((int) fontSize);
        editText.setText(prefs.getString(TEXT_VALUE_KEY, ""));
        editText.setTextSize(seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				// TODO Auto-generated method stub
				editText.setTextSize(progress);
			}
		});
    }
}