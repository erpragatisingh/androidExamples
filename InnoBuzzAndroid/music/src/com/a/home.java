package com.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ImageView im1=(ImageView)findViewById(R.id.imageView1);
        //ImageView im3=(ImageView)findViewById(R.id.imageView3);
        ImageView im2=(ImageView)findViewById(R.id.imageView2);
        ImageView im4=(ImageView)findViewById(R.id.imageView5);
        TextView name1=(TextView)findViewById(R.id.textView3);
        TextView lname=(TextView)findViewById(R.id.textView8);
        //TextView location1=(TextView)findViewById(R.id.textView9);
        
        Intent i=getIntent();
        Bundle bun=i.getExtras();
        String name=bun.getString("name");
        String email=bun.getString("email");
       // String location=bun.getString("location");
        name1.setText(name);
        lname.setText(email);
        //location1.setText(location);
        im4.setOnClickListener(new OnClickListener() {
        	 TextView newname=(TextView)findViewById(R.id.textView3);
        	 TextView lname=(TextView)findViewById(R.id.textView8);
        	 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(home.this,speakers.class);
				//TextView newname=(TextView)findViewById(R.id.textView3);
				i.putExtra("new_name", newname.getText());
				i.putExtra("last_name",lname.getText());
				startActivity(i);
				Toast.makeText(getBaseContext(), "Speakers Products", Toast.LENGTH_SHORT).show();
			}
		});
        im2.setOnClickListener(new OnClickListener() {
        	 TextView newname=(TextView)findViewById(R.id.textView3);
        	 TextView lname=(TextView)findViewById(R.id.textView8);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(home.this,vedio.class);
				//TextView newname=(TextView)findViewById(R.id.textView3);
				i.putExtra("new_name", newname.getText());
				i.putExtra("last_name",lname.getText());
				startActivity(i);
				
				
				Toast.makeText(getBaseContext(), "Vedio Products", Toast.LENGTH_SHORT).show();
			}
		});
       
        im1.setOnClickListener(new OnClickListener() {
        TextView newname=(TextView)findViewById(R.id.textView3);
        TextView lname=(TextView)findViewById(R.id.textView8);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(home.this,audio.class);
				
				i.putExtra("new_name",newname.getText());
				i.putExtra("last_name",lname.getText());
				
				startActivity(i);
				
				
				Toast.makeText(getBaseContext(), "Audio Products", Toast.LENGTH_SHORT).show();
				
			}
		});
        
    }
}