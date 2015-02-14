package call.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Call_DemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        String url = "tel:5556";
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        startActivity(intent);
    }
}