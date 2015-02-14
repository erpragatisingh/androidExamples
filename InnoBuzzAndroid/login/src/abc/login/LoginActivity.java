package abc.login;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
   Button abc;
   EditText ed1,ed2;
   TextView tv;
	@Override
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    abc=(Button)findViewById(R.id.abc);
    
    ed1=(EditText)findViewById(R.id.ed1);
    ed2=(EditText)findViewById(R.id.ed2);
    tv=(TextView)findViewById(R.id.tv);
     abc.setOnClickListener(new OnClickListener() {
	
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//String st=ed1.getText().toString();
			//Intent aa=new Intent(LoginActivity.this,profile1.class);
		
			//tv.setText(st);
			
			//Bundle bl=new Bundle();
			//bl.putString("mykey", st);
		   // aa.putExtras(bl);
			//startActivity(aa);
			//Toast t=Toast.makeText(LoginActivity.this, "clicked",Toast.LENGTH_LONG );
			//t.show();
			
			MediaPlayer mp=MediaPlayer.create(LoginActivity.this,R.raw.test_cbr); 
			mp.start();
			
			
		}
    });
    
    
    }
}