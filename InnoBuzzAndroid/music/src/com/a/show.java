package com.a;

import java.util.Locale;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class show extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		TextView tv1=(TextView)findViewById(R.id.TextView01);
		TextView tv2=(TextView)findViewById(R.id.textView1);
	     SQLiteDatabase db;
	     db=openOrCreateDatabase("contactsManager", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	     db.setVersion(1);
	     db.setLocale(Locale.getDefault());
	     db.setLockingEnabled(true);
	     Cursor cur=db.query("contacts", null, null, null, null, null, null);
	     cur.moveToFirst();
	     while (cur.isAfterLast()==false) {
	    	 tv1.append(cur.getString(1)+"\n");
	    	 tv2.append(cur.getString(2)+"\n"); 
	    	 cur.moveToNext();
	     
			
		}
	}

}
