/**
 * GameHelp.java
 *  
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Simple display activity to show the help screen
 */
public class GameHelp extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Set the layout for this activity
		setContentView(R.layout.help);
	}

}
