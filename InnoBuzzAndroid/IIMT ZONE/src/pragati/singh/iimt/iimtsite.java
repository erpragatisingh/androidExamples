package pragati.singh.iimt;
 import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


public class iimtsite extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iimtsite);
        WebView wv=(WebView) findViewById(R.id.web);
        wv.loadUrl("http://www.iimtindia.net");
    }
}