package abc.login;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class profile1 extends Activity {
	 /** Called when the activity is first created. */
	   Button abc;
	   TextView tv;
		@Override
	   
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.profile1);
	        String s=this.getIntent().getExtras().getString("mykey");
	        tv=(TextView)findViewById(R.id.t);
	        tv.setText(s);

}}
