package pragati.singh.iimt;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class upturesult extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upturesult);
        WebView wv=(WebView) findViewById(R.id.web);
        wv.loadUrl("http://www.uptu.ac.in/results/results_odd_11_12.htm");
    }
}