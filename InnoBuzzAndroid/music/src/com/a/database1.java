package com.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class database1 extends Activity{

	


	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql);
		Button btn=(Button)findViewById(R.id.button1);
		
		
		TextView name=(TextView)findViewById(R.id.textView3);
		TextView product=(TextView)findViewById(R.id.textView5);
		TextView price=(TextView)findViewById(R.id.textView8);
		TextView company=(TextView)findViewById(R.id.textView9);
		TextView bank=(TextView)findViewById(R.id.textView10);
		TextView acchol=(TextView)findViewById(R.id.textView12);
		TextView accpass=(TextView)findViewById(R.id.textView14);
		TextView last=(TextView)findViewById(R.id.textView16);
		Intent sql_i=getIntent();
		Bundle sql_bun=sql_i.getExtras();
		String sql_name=sql_bun.getString("cname");
		String sql_pro=sql_bun.getString("product");
		String sql_pri=sql_bun.getString("price");
		String sql_com=sql_bun.getString("company");
		String sql_bank=sql_bun.getString("bank");
		String sql_acch=sql_bun.getString("account");
		String sql_accp=sql_bun.getString("password");
		String sql_last=sql_bun.getString("lastname");
		
		name.setText(sql_name);
		product.setText(sql_pri);
		price.setText(sql_pro);
		company.setText(sql_com);
		bank.setText(sql_bank);
		acchol.setText(sql_acch);
		accpass.setText(sql_accp);
		last.setText(sql_last);
		
		database db = new database(this,"contactsManager", null, 0);
		
		//public void onClick(View v) {
			// TODO Auto-generated method stub
			 
		        
		        /**
		         * CRUD Operations
		         * */
		        // Inserting Contacts
		        Log.d("Insert: ", "Inserting ..");
		        db.addContact(new Contact("First Name			  :   ", ""+name.getText()));
		        db.addContact(new Contact("Last  Name			  :   ", ""+last.getText()));
		        db.addContact(new Contact("Price of Product       :   ", ""+product.getText()));
		        db.addContact(new Contact("Name of Product        :   ", ""+price.getText()));
		        db.addContact(new Contact("Name of Company        :   ", ""+company.getText()));
		        db.addContact(new Contact("Name of Bank           :   ", ""+bank.getText()));
		        db.addContact(new Contact("Name of Acc/Holder     :   ", ""+acchol.getText()));
		        db.addContact(new Contact("Password of Acc/Holder :   ", ""+accpass.getText()));
		        db.addContact(new Contact("--------------------------","--------------------------"));
		        
		        // Reading all contacts
		        
		  
		        Log.d("Reading: ", "Reading all Data..");
		        //Log.v(""+getString(0), ""+getString(1)); 
		        List <Contact> contacts = db.getAllContacts();      
		 
		        for (Contact cn : contacts) {
		            String log = ""+ cn.getName() + ""+ cn.getPhoneNumber();
		                 //Writing Contacts to log
		        Log.d("Name: ", log);
		        
		    }
		     
		        btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(database1.this,show.class);
						startActivity(i);
					}
				});
		}
	
	
}
