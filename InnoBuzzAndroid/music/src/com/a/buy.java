package com.a;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class buy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);
		Button btn=(Button)findViewById(R.id.button1);
		final TextView cname=(TextView)findViewById(R.id.textView3);
		final TextView pro=(TextView)findViewById(R.id.textView5);
		final TextView pri=(TextView)findViewById(R.id.textView8);
		final TextView com=(TextView)findViewById(R.id.textView9);
		final TextView bank=(TextView)findViewById(R.id.textView13);
		final TextView last=(TextView)findViewById(R.id.textView14);
		//final Spinner bank=(Spinner)findViewById(R.id.spinner1);
		final EditText acchol=(EditText)findViewById(R.id.editText2);
		final EditText accpass=(EditText)findViewById(R.id.editText1);
		
				Intent com_i=getIntent();
				Bundle com_bun=com_i.getExtras();
				
				String com_beltak=com_bun.getString("com");
				com.setText(com_beltak);
				
				String com_lg=com_bun.getString("com");
				com.setText(com_lg);
			
				String com_samsung=com_bun.getString("com");
				com.setText(com_samsung);
								
				String com_sony=com_bun.getString("com");
				com.setText(com_sony);
				
				String product=com_bun.getString("product");
				pro.setText(product);
				
				String price=com_bun.getString("price");
				pri.setText(price);
				
				String name=com_bun.getString("name");
				cname.setText(name);
				
				String lastname=com_bun.getString("last");
				last.setText(lastname);
				
				btn.setOnClickListener(new OnClickListener() {
					
					
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in=new Intent(buy.this,database1.class);
						in.putExtra("cname",cname.getText());
						in.putExtra("product",pri.getText());
						in.putExtra("price",pro.getText());
						in.putExtra("company",com.getText());
						in.putExtra("bank",bank.getText());
						in.putExtra("account",acchol.getText().toString());;
						in.putExtra("password",accpass.getText().toString());;
						in.putExtra("lastname",last.getText());
						
						
						
						startActivity(in);
					}
				});
		
		
		final RadioButton rb1=(RadioButton)findViewById(R.id.radioButton1);
		final RadioButton rb2=(RadioButton)findViewById(R.id.radioButton2);
		
		rb2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (rb1.isChecked()==true)
				{
				Toast.makeText(getBaseContext(),"ch"+ rb2.getText(), Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getBaseContext(), "not"+rb2.getText(), Toast.LENGTH_SHORT).show();
				}
					
			}
		});
		rb1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), rb1.getText(), Toast.LENGTH_SHORT).show();
			}
		});			
		
		Spinner sp=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> ad=ArrayAdapter.createFromResource(this, R.array.buy, android.R.layout.simple_spinner_item);
		ad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(ad);
		sp.setOnItemSelectedListener(new myOnItemSelectedListener() {
		});
		
		
      
			
	}
	class myOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView tv13=(TextView)findViewById(R.id.textView13);
			tv13.setText((CharSequence) arg0.getItemAtPosition(arg2));
			
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	


	

}

