/**
 * BoardView.java
 * 
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 */
public class BoardView extends SurfaceView implements SurfaceHolder.Callback {

	/**
	 * The id's for the menu items
	 */
	private static final int C_CmdNewGame = Menu.FIRST;
	private static final int C_CmdStopGame = Menu.FIRST + 1;
	private static final int C_CmdSettings = Menu.FIRST + 2;
	private static final int C_CmdLevelUp = Menu.FIRST + 3;
	private static final int C_CmdHelp = Menu.FIRST + 4;
	private static final int C_CmdLevelDown = Menu.FIRST + 5;

	/**
	 * Range for the game levels
	 */
	private static final int C_MinLevel = 0;
	private static final int C_MaxLevel = 10;

	/**
	 * The possible game state
	 */
	public enum eState {
		Inactive, Active, Solved
	}

	/**
	 * The current game state
	 */
	private eState m_gameState;

	/**
	 * Current game level
	 */
	private int m_level;

	/**
	 * Starttime for a game to calc the elapsed time
	 */
	private long m_startTime;

	/**
	 * Endtime for a game to calc the elapsed time
	 */
	private long m_endTime;

	/**
	 * Number of moves to solve a board
	 */
	private long m_movesToSolve;

	/**
	 * The game board
	 */
	private Board m_board = null;

	/**
	 * The thread to draw the animation
	 */
	private GameThread m_thread = null;

	/**
	 * The main activity
	 */
	private Context m_ctx = null;

	/**
	 * Paint structure, used to draw the bitmaps
	 */
	private Paint m_paint = null;

	/**
	 * Flag if dragging is active
	 */
	private boolean m_bDrag = false;

	/**
	 * Starting coordinates for dragging
	 */
	private float m_dragx;
	private float m_dragy;

	/**
	 * Current sizes of the board view
	 */
	public int BoardWidth;
	public int BoardHeight;
	public int UsedWidth;

	/**
	 * Constructor
	 */
	public BoardView(Context context) {
		super(context);

		// Init the view
		m_ctx = context;
		getHolder().addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);

		// Create the thread
		m_thread = new GameThread(this);

		// Create the paint
		m_paint = new Paint();
		m_paint.setColor(Color.BLACK);
		m_paint.setAntiAlias(true);
		m_paint.setStyle(Style.FILL_AND_STROKE);
		m_paint.setTextSize(16);
		m_paint.setStrokeWidth(1);
		m_paint.setTextAlign(Align.CENTER);

		// Create the game board for the current level
		m_board = new Board();
		m_gameState = eState.Inactive;

		// Check if a game is running and reconstruct the state
		m_board.GameMoves = GamePrefs.Prefs.getInt(GamePrefs.C_GameActive, GamePrefs.C_GameActive_Default);
		if (m_board.GameMoves > 0) {
			m_gameState = eState.Active;
			m_level = GamePrefs.Prefs.getInt(GamePrefs.C_GameLevel, GamePrefs.C_GameLevel_Default);
			m_startTime = System.currentTimeMillis() - GamePrefs.Prefs.getLong(GamePrefs.C_Time, GamePrefs.C_Time_Default);

			m_board.initBoard(m_level);
			m_board.GameMoves = GamePrefs.Prefs.getInt(GamePrefs.C_GameActive, GamePrefs.C_GameActive_Default);
			m_board.setBoard(GamePrefs.Prefs.getString(GamePrefs.C_Rotations, GamePrefs.C_Rotations_Default), this);

		} else {
			m_level = GamePrefs.Prefs.getInt(GamePrefs.C_Level, GamePrefs.C_Level_Default);
			m_board.initBoard(m_level);
		}
	}

	/**
	 * Handles the menu creation
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item;

		// Add the menu items
		item = menu.add(0, C_CmdNewGame, 0, R.string.menuNewGame);
		item.setIcon(R.drawable.newgame);

		item = menu.add(0, C_CmdStopGame, 0, R.string.menuStopGame);
		item.setIcon(R.drawable.resetgame);

		item = menu.add(0, C_CmdSettings, 0, R.string.menuSettings);
		item.setIcon(R.drawable.settings);

		item = menu.add(0, C_CmdLevelDown, 0, R.string.menuLevelDown);
		item.setIcon(R.drawable.levdown);

		item = menu.add(0, C_CmdHelp, 0, R.string.menuHelp);
		item.setIcon(R.drawable.help);
		
		item = menu.add(0, C_CmdLevelUp, 0, R.string.menuLevelUp);
		item.setIcon(R.drawable.levup);

		return true;

	}

	/**
	 * Enables/disables menu items for the current state
	 */
	public void onPrepareOptionsMenu(Menu menu) {

		// Check which menu items have to be disabled
		if (m_gameState == eState.Active) {
			
			// The game is running so disable all but the stop item
			menu.findItem(C_CmdNewGame).setEnabled(false);
			menu.findItem(C_CmdLevelDown).setEnabled(false);
			menu.findItem(C_CmdLevelUp).setEnabled(false);
			menu.findItem(C_CmdHelp).setEnabled(false);
			menu.findItem(C_CmdSettings).setEnabled(false);
			menu.findItem(C_CmdStopGame).setEnabled(true);
		} else {
			menu.findItem(C_CmdNewGame).setEnabled(true);
			menu.findItem(C_CmdHelp).setEnabled(true);
			menu.findItem(C_CmdSettings).setEnabled(true);
			menu.findItem(C_CmdStopGame).setEnabled(false);

			// The level up/down items depend on the current level
			if (m_level <= C_MinLevel) {
				menu.findItem(C_CmdLevelDown).setEnabled(false);
				menu.findItem(C_CmdLevelUp).setEnabled(true);
			} else if (m_level >= C_MaxLevel) {
				menu.findItem(C_CmdLevelDown).setEnabled(true);
				menu.findItem(C_CmdLevelUp).setEnabled(false);
			} else {
				menu.findItem(C_CmdLevelDown).setEnabled(true);
				menu.findItem(C_CmdLevelUp).setEnabled(true);
			}
		}
	}

	/**
	 * Handles the selected menu item
	 */
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		// Do the action for the selected menu item
		switch (item.getItemId()) {
		case C_CmdNewGame:
			newGame();
			return true;

		case C_CmdStopGame:
			reset();
			return true;

		case C_CmdLevelUp:
			levelUp();
			return true;

		case C_CmdLevelDown:
			levelDown();
			return true;

		case C_CmdHelp: {
			Intent intent = new Intent();
			intent.setClass(m_ctx, GameHelp.class);
			m_ctx.startActivity(intent);
			return true;
		}

		case C_CmdSettings: {
			Intent intent = new Intent();
			intent.setClass(m_ctx, GamePrefs.class);
			m_ctx.startActivity(intent);
			return true;
		}
		
		}
		return false;
	}

	
	
	/**
	 * Returns the current text for the status bar
	 */
	public String getStatusText() {
		String msg = "";

		Resources res = getResources();
		
		// Prepare the current status text
		switch (m_gameState) {
		case Inactive:
			
			msg = res.getString(R.string.gameInactive, m_level);
			return msg;

		case Active: {
			int moves = m_board.getNumMoves();
			
			// With the first move the time starts tu run
			if (moves == 1) {
				m_startTime = System.currentTimeMillis();
			}
			if (moves > 0) {
				long secs = (System.currentTimeMillis() - m_startTime) / 1000;
				msg = res.getString(R.string.gameActive, moves, secs);
			} else {
				msg = res.getString(R.string.gameStarted);
			}

			return msg;
		}

		case Solved: {
			long secs = (m_endTime - m_startTime) / 1000;
			msg = res.getString(R.string.gameSolved, secs, m_movesToSolve);
			msg += "\n\n";
			msg += res.getString(R.string.gameInactive, m_level);
			return msg;
		}
		
		}

		return msg;

	}

	/**
	 * Calculates the boardsize depending on the orientation of the device
	 * 
	 * @param w
	 *            the current screenwidth
	 * @param h
	 *            the current screenheight
	 */
	private void calcBoard(int w, int h) {

		// Calculate the board-size depending on the orientation
		if (h > w) {
			BoardWidth = w;
			BoardHeight = (int) (h * Board.C_BoardWidth / Board.C_BoardHeight);
			UsedWidth = getWidth();
		} else {
			BoardHeight = h;
			BoardWidth = (int) (w * Board.C_BoardHeight / Board.C_BoardWidth);
			UsedWidth = BoardWidth / 2;
		}
	}

	/**
	 * Start a new game
	 */
	public void newGame() {

		// Start a new game
		m_startTime = System.currentTimeMillis();
		createBoard();
		m_board.newGame(this);
		m_gameState = eState.Active;
	}

	/**
	 * Stop a running game
	 */
	public void reset() {
		m_gameState = eState.Inactive;
		createBoard();
	}

	/**
	 * Increase the game level
	 */
	public void levelUp() {
		m_gameState = eState.Inactive;

		// Increment the level
		if (m_level == C_MaxLevel) {
			return;
		}

		m_level++;
		createBoard();
	}

	/**
	 * Reduce the game level
	 */
	public void levelDown() {
		m_gameState = eState.Inactive;

		if (m_level == C_MinLevel) {
			return;
		}
		m_level--;
		createBoard();
	}

	/**
	 * Do a repaint in the thread
	 */
	public void repaint() {

		// Refresh the board
		m_thread.repaint();
	}

	/**
	 * Draw the board to the surface view
	 */
	@Override
	public void onDraw(Canvas canvas) {
		if (m_board == null || canvas == null) {
			return;
		}

		super.onDraw(canvas);

		// Draw the bitmaps in the right order
		canvas.drawBitmap(m_board.getBackground(), 0, 0, m_paint);
		if (m_board.getRotDisk() == Board.eDisc.UpperDisc) {
			canvas.drawBitmap(m_board.getLowerDisk(), 0, 0, m_paint);
			canvas.drawBitmap(m_board.getUpperDisk(), 0, 0, m_paint);
		} else {
			canvas.drawBitmap(m_board.getUpperDisk(), 0, 0, m_paint);
			canvas.drawBitmap(m_board.getLowerDisk(), 0, 0, m_paint);
		}
	}

	/**
	 * Creates a new board for the given level and the current screen size
	 */
	private void createBoard() {
		m_board.initBoard(m_level);
		m_board.onResize(BoardWidth, BoardHeight, UsedWidth);
		repaint();
	}

	/**
	 * Check if the game has been solved
	 */
	private void checkGameSolved() {
		// Check if the game has been solved if one is running
		if (m_gameState == eState.Active) {

			// Get colorstrings for the original board and the current state
			String s = m_board.getColorString();
			String s1 = Block.getColorString();

			// If the puzzle has been solved stop it and initialize the board
			if (s.equals(s1)) {

				m_endTime = System.currentTimeMillis();
				m_movesToSolve = m_board.getNumMoves();
				m_gameState = eState.Solved;

				m_board.colorStones(this);
				if (m_level < C_MaxLevel) {
					m_level++;
					
					// Save the current level in the preferences
					SharedPreferences.Editor editor = GamePrefs.Prefs.edit();
					editor.putInt(GamePrefs.C_Level, m_level);
					editor.commit();
				}
				createBoard();
			}
		}
	}

	/**
	 * Resize the board
	 */
	@Override
	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		calcBoard(w, h);

		if (m_board != null) {
			m_board.onResize(BoardWidth, BoardHeight, UsedWidth);
		}
	}

	/**
	 * Handle touchevents to turn the discs
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (getHolder()) {

			if (event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_UP) {
				return super.onTouchEvent(event);
			}

			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				// Save the position
				PointF point = new PointF();
				point.x = event.getX();
				point.y = event.getY();

				// Check if it was a clik on one of the four arrow-buttons
				if (m_board.onMouseDown(point, this)) {

					// Check the board and leave the function
					checkGameSolved();
					return true;
				}

				// Init the dragging
				m_dragx = event.getX();
				m_dragy = event.getY();
				m_bDrag = true;

			}

			if (event.getAction() == MotionEvent.ACTION_UP) {

				// Check if dragging is active
				if (m_bDrag) {
					float x2 = event.getX();
					float y2 = event.getY();

					// Try to turn the disc
					if (m_board.turnDisk(m_dragx, m_dragy, x2, y2, this)) {

						// Check the board
						checkGameSolved();
					}

					m_bDrag = false;
				}
			}

			return true;
		}
	}

	/**
	 * Called if you change the configuration like open the keypad.
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * Called on creation of the SurfaceView. Which could be on first start or
	 * relaunch.
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		if (m_thread == null) {
			m_thread = new GameThread(this);
		}
		m_thread.setRunning(true);
		m_thread.start();
	}

	/**
	 * Called if the SurfaceView should be destroyed. We try to finish the game
	 * loop thread here and save the current state of the game to the shared
	 * preferences
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;

		// Stop the thread
		m_thread.setRunning(false);
		while (retry) {
			try {
				m_thread.join();
				retry = false;
				m_thread = null;
				System.gc();
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}

		// Save the state to the prefernces
		SharedPreferences.Editor editor = GamePrefs.Prefs.edit();
		if (m_gameState == eState.Active) {

			// Save the current game in the preferences
			editor.putInt(GamePrefs.C_GameLevel, m_level);
			editor.commit();

			editor.putLong(GamePrefs.C_Time, System.currentTimeMillis() - m_startTime);
			editor.commit();

			editor.putInt(GamePrefs.C_GameActive, m_board.GameMoves);
			editor.commit();

			editor.putString(GamePrefs.C_Rotations, m_board.getMoves());
			editor.commit();
		} else {

			// Reset the preferences
			editor.putInt(GamePrefs.C_GameLevel, GamePrefs.C_GameLevel_Default);
			editor.commit();

			editor.putLong(GamePrefs.C_Time, GamePrefs.C_Time_Default);
			editor.commit();

			editor.putInt(GamePrefs.C_GameActive, GamePrefs.C_GameActive_Default);
			editor.commit();

			editor.putString(GamePrefs.C_Rotations, GamePrefs.C_Rotations_Default);
			editor.commit();
		}
	}

}
