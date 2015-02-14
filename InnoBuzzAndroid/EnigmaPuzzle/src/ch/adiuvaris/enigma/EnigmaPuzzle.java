/**
 * Enigma.java
 *  
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * This is the class for the main activity of EnigmaPuzzle
 */
public class EnigmaPuzzle extends Activity {

	/**
	 * The views for the main layout
	 */
	private TextView m_statusText;
	private BoardView m_view;

	/**
	 * A message handler to refresh state on the screen
	 */
	private Handler m_Handler;

	/**
	 * Creates the main layout and shows it
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Init the static with the preferences
		GamePrefs.Prefs = getSharedPreferences("eg", 0);

		// Fullscreen and no title for main window
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create the main layout
		FrameLayout f = new FrameLayout(this);
		
		// Create the TextView for status texts
		m_statusText = new TextView(this);
		m_statusText.setPadding(1, 3, 1, 3);
		
		if (getScreenOrientation() != Configuration.ORIENTATION_PORTRAIT) {
			m_statusText.setGravity(Gravity.CENTER | Gravity.RIGHT);
		} else {
			m_statusText.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
		}
		m_statusText.setText("EnigmaPuzzle");

		// Create the surface view for the board
		m_view = new BoardView(this);

		// Add the two elements to the layout
		f.addView(m_view);
		f.addView(m_statusText);

		// Set the layout as view for the activity
		setContentView(f);

		/*
		if (f.getWidth() > f.getHeight()) {
			m_statusText.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
		} else {
			m_statusText.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
		}
		*/
		// Add a handler for the refresh of the status text
		m_Handler = new Handler();
		m_Handler.removeCallbacks(RefreshStatusText);
		m_Handler.postDelayed(RefreshStatusText, 100);
	}
	
	/**
	 * Returns the orientation
	 * 
	 * @return Portrait or Landscape
	 */
	public int getScreenOrientation()
	{
	    Display getOrient = getWindowManager().getDefaultDisplay();
	    int orientation = Configuration.ORIENTATION_UNDEFINED;
	    if (getOrient.getWidth() == getOrient.getHeight()) {
	        orientation = Configuration.ORIENTATION_SQUARE;
	    } else{ 
	        if (getOrient.getWidth() < getOrient.getHeight()) {
	            orientation = Configuration.ORIENTATION_PORTRAIT;
	        }else { 
	            orientation = Configuration.ORIENTATION_LANDSCAPE;
	        }
	    }
	    return orientation;
	}

	/**
	 * Handles the menu creation
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// Delegate it to the boardview
		return m_view.onCreateOptionsMenu(menu);
	}

	/**
	 * Enables/disables menu items for the current state
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		// Delegate it to the boardview
		m_view.onPrepareOptionsMenu(menu);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Handles the selected menu item
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		// Delegate it to the boardview
		if (m_view.onMenuItemSelected(featureId, item)) {
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}
	
	/**
	 * Code that may be called asynch to update the status text
	 */
	private Runnable RefreshStatusText = new Runnable() {
		public void run() {
			m_statusText.setText(m_view.getStatusText());

			// Start the next refresh in a second
			m_Handler.postDelayed(this, 1000);
		}
	};

}
