/**
 * GamePrefs.java
 *  
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CheckBox;

/**
 * A class that manages all the settings in EnigmaPuzzle
 */
public class GamePrefs extends Activity implements OnClickListener {

	/**
	 * A pointer to the shared preferences
	 */
	public static SharedPreferences Prefs = null;
	public static boolean PrefsChanged = false;

	/**
	 * The view items in the preferences layout
	 */
	private TextView tvLevel;
	private ImageView ivLevelUp;
	private ImageView ivLevelDown;
	private int m_level;

	private TextView tvTurns;
	private ImageView ivTurnsUp;
	private ImageView ivTurnsDown;
	private int m_turns;

	private TextView tvDelay;
	private ImageView ivDelayUp;
	private ImageView ivDelayDown;
	private int m_delay;

	private TextView tvSteps;
	private ImageView ivStepsUp;
	private ImageView ivStepsDown;
	private int m_steps;

	private TextView tvSwings;
	private ImageView ivSwingsUp;
	private ImageView ivSwingsDown;
	private int m_swings;

	private CheckBox cbShowTurns;
	private boolean m_showTurns;

	private CheckBox cbShowSwings;
	private boolean m_showSwings;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PrefsChanged = true;
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Set the layout for this activity
		setContentView(R.layout.settings);

		// Get all the views
		m_level = Prefs.getInt(GamePrefs.C_Level, GamePrefs.C_Level_Default);
		m_turns = Prefs.getInt(GamePrefs.C_Turns, GamePrefs.C_Turns_Default);
		m_delay = Prefs.getInt(GamePrefs.C_Delay, GamePrefs.C_Delay_Default);
		m_steps = Prefs.getInt(GamePrefs.C_Steps, GamePrefs.C_Steps_Default);
		m_swings = Prefs.getInt(GamePrefs.C_Swings, GamePrefs.C_Swings_Default);
		m_showTurns = Prefs.getBoolean(GamePrefs.C_ShowTurns, GamePrefs.C_ShowTurns_Default);
		m_showSwings = Prefs.getBoolean(GamePrefs.C_ShowSwings, GamePrefs.C_ShowSwings_Default);

		// Fill the current values to the views and set the click listener
		tvLevel = (TextView) findViewById(R.id.level);
		tvLevel.setText(Long.toString(m_level));

		tvTurns = (TextView) findViewById(R.id.turns);
		tvTurns.setText(Long.toString(m_turns));

		tvDelay = (TextView) findViewById(R.id.delay);
		tvDelay.setText(Long.toString(m_delay));

		tvSteps = (TextView) findViewById(R.id.steps);
		tvSteps.setText(Long.toString(m_steps));

		tvSwings = (TextView) findViewById(R.id.swings);
		tvSwings.setText(Long.toString(m_swings));

		ivLevelUp = (ImageView) findViewById(R.id.levelup);
		ivLevelUp.setOnClickListener(this);
		ivLevelDown = (ImageView) findViewById(R.id.leveldown);
		ivLevelDown.setOnClickListener(this);

		ivTurnsUp = (ImageView) findViewById(R.id.turnsup);
		ivTurnsUp.setOnClickListener(this);
		ivTurnsDown = (ImageView) findViewById(R.id.turnsdown);
		ivTurnsDown.setOnClickListener(this);

		ivDelayUp = (ImageView) findViewById(R.id.delayup);
		ivDelayUp.setOnClickListener(this);
		ivDelayDown = (ImageView) findViewById(R.id.delaydown);
		ivDelayDown.setOnClickListener(this);

		ivStepsUp = (ImageView) findViewById(R.id.stepsup);
		ivStepsUp.setOnClickListener(this);
		ivStepsDown = (ImageView) findViewById(R.id.stepsdown);
		ivStepsDown.setOnClickListener(this);

		ivSwingsUp = (ImageView) findViewById(R.id.swingsup);
		ivSwingsUp.setOnClickListener(this);
		ivSwingsDown = (ImageView) findViewById(R.id.swingsdown);
		ivSwingsDown.setOnClickListener(this);

		cbShowTurns = (CheckBox) findViewById(R.id.showTurns);
		cbShowTurns.setChecked(m_showTurns);
		cbShowTurns.setOnClickListener(this);

		cbShowSwings = (CheckBox) findViewById(R.id.showSwings);
		cbShowSwings.setChecked(m_showSwings);
		cbShowSwings.setOnClickListener(this);
	}

	/**
	 * Saves a changed preference
	 * 
	 * @param key
	 *            is the name of the preference
	 * @param val
	 *            is the new value
	 */
	private void setPref(String key, int val) {
		SharedPreferences.Editor editor = Prefs.edit();
		editor.putInt(key, val);
		editor.commit();
	}

	/**
	 * Saves a changed preference
	 * 
	 * @param key
	 *            is the name of the preference
	 * @param val
	 *            is the new value
	 */
	private void setPref(String key, boolean val) {
		SharedPreferences.Editor editor = Prefs.edit();
		editor.putBoolean(key, val);
		editor.commit();
	}

	/**
	 * Handle clicks on the different views
	 */
	public void onClick(View v) {
		if (v == ivLevelUp) {
			if (m_level < 10) {
				m_level++;
				tvLevel.setText(Long.toString(m_level));
				setPref(GamePrefs.C_Level, m_level);
			}
		} else if (v == ivLevelDown) {
			if (m_level > 0) {
				m_level--;
				tvLevel.setText(Long.toString(m_level));
				setPref(GamePrefs.C_Level, m_level);
			}
		}

		if (v == ivTurnsUp) {
			if (m_turns < 99) {
				m_turns++;
				tvTurns.setText(Long.toString(m_turns));
				setPref(GamePrefs.C_Turns, m_turns);
			}
		} else if (v == ivTurnsDown) {
			if (m_turns > 2) {
				m_turns--;
				tvTurns.setText(Long.toString(m_turns));
				setPref(GamePrefs.C_Turns, m_turns);
			}
		}

		if (v == ivDelayUp) {
			if (m_delay < 99) {
				m_delay++;
				tvDelay.setText(Long.toString(m_delay));
				setPref(GamePrefs.C_Delay, m_delay);
			}
		} else if (v == ivDelayDown) {
			if (m_delay > 0) {
				m_delay--;
				tvDelay.setText(Long.toString(m_delay));
				setPref(GamePrefs.C_Delay, m_delay);
			}
		}

		if (v == ivStepsUp) {
			if (m_steps < 18) {
				m_steps++;
				tvSteps.setText(Long.toString(m_steps));
				setPref(GamePrefs.C_Steps, m_steps);
			}
		} else if (v == ivStepsDown) {
			if (m_steps > 0) {
				m_steps--;
				tvSteps.setText(Long.toString(m_steps));
				setPref(GamePrefs.C_Steps, m_steps);
			}
		}

		if (v == ivSwingsUp) {
			if (m_swings < 5) {
				m_swings++;
				tvSwings.setText(Long.toString(m_swings));
				setPref(GamePrefs.C_Swings, m_swings);
			}
		} else if (v == ivSwingsDown) {
			if (m_swings > 0) {
				m_swings--;
				tvSwings.setText(Long.toString(m_swings));
				setPref(GamePrefs.C_Swings, m_swings);
			}
		}

		if (v == cbShowTurns) {
			m_showTurns = !m_showTurns;
			setPref(GamePrefs.C_ShowTurns, m_showTurns);
		}

		if (v == cbShowSwings) {
			m_showSwings = !m_showSwings;
			setPref(GamePrefs.C_ShowSwings, m_showSwings);
		}
	}

	/**
	 * Key for the Level
	 */
	public static final String C_Level = "Level";
	public static final int C_Level_Default = 1;

	/**
	 * Key for number of turns on new game
	 */
	public static final String C_Turns = "Turns";
	public static final int C_Turns_Default = 20;

	/**
	 * Key for the rotation delay
	 */
	public static final String C_Delay = "Delay";
	public static final int C_Delay_Default = 0;

	/**
	 * Key for the rotations steps
	 */
	public static final String C_Steps = "Steps";
	public static final int C_Steps_Default = 4;

	/**
	 * Key for the swing steps
	 */
	public static final String C_Swings = "Swings";
	public static final int C_Swings_Default = 1;

	/**
	 * Key for the swing setting
	 */
	public static final String C_ShowSwings = "ShowSwings";
	public static final boolean C_ShowSwings_Default = true;

	/**
	 * Key for the flag if turns shall be shown
	 */
	public static final String C_ShowTurns = "ShowTurns";
	public static final boolean C_ShowTurns_Default = false;

	/**
	 * Key for the flag if a game is active
	 */
	public static final String C_GameActive = "GameActive";
	public static final int C_GameActive_Default = -1;

	/**
	 * Key for the flag if a game is active
	 */
	public static final String C_GameLevel = "GameLevel";
	public static final int C_GameLevel_Default = 0;

	/**
	 * The rotations to restore the current game
	 */
	public static final String C_Rotations = "Rotations";
	public static final String C_Rotations_Default = "";

	/**
	 * The elapsed time of a game
	 */
	public static final String C_Time = "Time";
	public static final long C_Time_Default = 0;

}
