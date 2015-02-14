package phone.utility.demo;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Phone_Utility_DemoActivity extends Activity {
 private EditText editText;
 private TextView textView1;
 private TextView textView2;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        editText=(EditText)findViewById(R.id.editText1);
        textView1= (TextView)findViewById(R.id.textView1);
        textView2= (TextView)findViewById(R.id.textView2);
        Button btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phoneNumber= PhoneNumberUtils.formatNumber(editText.getText().toString());
				phoneNumber=PhoneNumberUtils.convertKeypadLettersToDigits(phoneNumber);
				StringBuilder sb = new StringBuilder();
				sb.append(phoneNumber);
				sb.append("\n globle-"+PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber));
				sb.append("\n is Emargency - "+ PhoneNumberUtils.isEmergencyNumber(phoneNumber));
				sb.append("\n compare to 123-456-789"+ PhoneNumberUtils.compare(phoneNumber,"123-456-789"));
			
			textView2.setText(sb.toString());
			}
		});
        
        
        
    }
}