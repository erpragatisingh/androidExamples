package com.a;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class vedio extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vedio);
		ImageView im1=(ImageView)findViewById(R.id.imageView1);
		ImageView im7=(ImageView)findViewById(R.id.imageView7);
		ImageView im8=(ImageView)findViewById(R.id.imageView8);
		ImageView im9=(ImageView)findViewById(R.id.imageView9);
		ImageView im10=(ImageView)findViewById(R.id.imageView10);
		final TextView product=(TextView)findViewById(R.id.textView10);
		final TextView tv2=(TextView)findViewById(R.id.textView2);
		final TextView tv4=(TextView)findViewById(R.id.textView4);
		final TextView tv7=(TextView)findViewById(R.id.textView7);
		final TextView tv8=(TextView)findViewById(R.id.textView8);
		final TextView name=(TextView)findViewById(R.id.textView9);
		final TextView last=(TextView)findViewById(R.id.textView11);
		Intent ii=getIntent();
		Bundle bunn=ii.getExtras();
		String name1=bunn.getString("new_name");
		String last1=bunn.getString("last_name");
		name.setText(name1);
		last.setText(last1);
		im10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(vedio.this,buy.class);
				i.putExtra("com","SAMSUNG");
				i.putExtra("product",product.getText());
				i.putExtra("price",tv8.getText());
				i.putExtra("name",name.getText());
				i.putExtra("last",last.getText());
				startActivity(i);
			}
		});
		im9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(vedio.this,buy.class);
				i.putExtra("com","SONY");
				i.putExtra("product",product.getText());
				i.putExtra("price",tv7.getText());
				i.putExtra("name",name.getText());
				i.putExtra("last",last.getText());
				startActivity(i);
			}
		});
		im8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(vedio.this,buy.class);
				i.putExtra("com","BLACKBERRY");
				i.putExtra("product",product.getText());
				i.putExtra("price",tv4.getText());
				i.putExtra("name",name.getText());
				i.putExtra("last",last.getText());
				startActivity(i);
			}
		});
		im7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(vedio.this,buy.class);
				i.putExtra("com","APPLE");
				i.putExtra("product",product.getText());
				i.putExtra("price",tv2.getText());
				i.putExtra("name",name.getText());
				i.putExtra("last",last.getText());
				startActivity(i);
			}
		});
		/*im1.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(vedio.this,home.class);
				startActivity(i);
				Toast.makeText(getBaseContext(), "Home All Products", Toast.LENGTH_SHORT).show();
			}
		});*/
		Spinner sp=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> ad=ArrayAdapter.createFromResource(this,R.array.vedio, android.R.layout.simple_spinner_item);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(ad);
sp.setOnItemSelectedListener(new myOnItemSelectedListener() {
            
		});
		
        
    }

public class myOnItemSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), (CharSequence) arg0.getItemAtPosition(arg2), Toast.LENGTH_SHORT).show();
		TextView tv10=(TextView)findViewById(R.id.textView10);
		tv10.setText((CharSequence) arg0.getItemAtPosition(arg2));
		//Toast.makeText(arg0.getContext()," The Selectes is "+ arg0.getItemAtPosition(arg2), Toast.LENGTH_LONG).show();
		int audioid =(int) arg0.getItemIdAtPosition(arg2);
		if(audioid==0)
		{
			//ImageView im=(ImageView)findViewById(R.id.imageView2);
			///int imr=drawable.samsung;
			//im.setImageResource(imr);
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("7500.00/-");
			tv4.setText("8950.00/-");
			tv7.setText("6100.00/-");
			tv8.setText("5550.00/-");
			
			
		}
		else if(audioid==1)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("9500.00/-");
			tv4.setText("8950.00/-");
			tv7.setText("7100.00/-");
			tv8.setText("6550.00/-");
			
		}
		else if(audioid==2)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("8400.00/-");
			tv4.setText("7950.00/-");
			tv7.setText("7200.00/-");
			tv8.setText("6450.00/-");
			
			
		}
		else if(audioid==3)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("No Available");
			tv4.setText("No Available");
			tv7.setText("30500.00/-");
			tv8.setText("27950.00/-");
			
			
		}
		else if(audioid==4)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("No Available");
			tv4.setText("No Available");
			tv7.setText("70680.00/-");
			tv8.setText("65950.00/-");
			
			
			
		}
		else if(audioid==5)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("No Available");
			tv4.setText("No Available");
			tv7.setText("8500.00/-");
			tv8.setText("7550.00/-");
			
			
		}
		else if(audioid==6)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("No Available");
			tv4.setText("No Available");
			tv7.setText("350.00/-");
			tv8.setText("250.00/-");
			
			
		}
		else if(audioid==7)
		{
			TextView tv2=(TextView)findViewById(R.id.textView2);
			TextView tv4=(TextView)findViewById(R.id.textView4);
			TextView tv7=(TextView)findViewById(R.id.textView7);
			TextView tv8=(TextView)findViewById(R.id.textView8);
			tv2.setText("No Available");
			tv4.setText("No Available");
			tv7.setText("250.00/-");
			tv8.setText("350.00/-");
			
			
		}
		
		
	}

	

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	
		
		
	}

		
	}

}
