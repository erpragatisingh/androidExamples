/**
 * GameThread.java
 * 
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.graphics.Canvas;

/**
 * The thread class for the EnigmaPuzzle. The thead is needed to refresh the
 * screen to create the effect of a little animation.
 */
public class GameThread extends Thread {

	/**
	 * Pointer to the view to redraw
	 */
	private BoardView m_view;

	/**
	 * Flag if the thread is running
	 */
	private boolean m_run = false;

	/**
	 * Constructor
	 * 
	 * @param view
	 *            a reference to the view
	 */
	public GameThread(BoardView view) {
		m_view = view;
	}

	/**
	 * Set the running state of the thread
	 * 
	 * @param run
	 *            the new state for the thread
	 */
	public void setRunning(boolean run) {
		m_run = run;
	}

	/**
	 * Refresh the game board
	 */
	public void repaint() {
		if (!m_run) {
			return;
		}

		// Get the canvas and lock it
		Canvas c = null;
		try {
			c = m_view.getHolder().lockCanvas(null);
			synchronized (m_view.getHolder()) {
				m_view.onDraw(c);
			}
		} finally {

			// Make sure we don't leave the Surface in an inconsistent state
			if (c != null) {
				m_view.getHolder().unlockCanvasAndPost(c);
			}
		}
	}

	/**
	 * Main loop of the thread - does nothing really
	 */
	@Override
	public void run() {
		repaint();

		while (m_run) {
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
		}
	}

}
