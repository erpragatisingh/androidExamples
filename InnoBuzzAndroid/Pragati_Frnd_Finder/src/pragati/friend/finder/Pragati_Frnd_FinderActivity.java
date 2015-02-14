package pragati.friend.finder;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Pragati_Frnd_FinderActivity extends Activity {
	WebView wb;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        wb=(WebView)findViewById(R.id.webView1);
        wb.loadUrl("https://www.google.com/latitude/");
    }
}