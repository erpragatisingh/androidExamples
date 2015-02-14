package pragati.singh.iimt;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class unilogin extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universitylogin);
        WebView wv=(WebView) findViewById(R.id.web);
        wv.loadUrl("http://www.uptu.ac.in/college_login.htm");
    }
}