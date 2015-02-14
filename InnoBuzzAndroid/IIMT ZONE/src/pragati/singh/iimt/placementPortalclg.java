package pragati.singh.iimt;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class placementPortalclg extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placementportal);
        WebView wv=(WebView) findViewById(R.id.webView1);
        wv.loadUrl("http://iimtindia.indiacareerportal.com");
    }
}