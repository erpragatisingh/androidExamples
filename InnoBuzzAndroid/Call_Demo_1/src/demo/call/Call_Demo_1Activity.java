package demo.call;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Call_Demo_1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText num=(EditText)findViewById(R.id.editText1); 
		        String number = "tel:" + num.getText().toString().trim();
		        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number)); 
		        startActivity(callIntent);	
				
				
			}
		});
        
        
        
    }
}