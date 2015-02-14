/**
 * Board.java
 *  
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import java.util.ArrayList;
import java.util.Random;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.graphics.Bitmap;

/**
 * This class represents the game board for the EnigmaPuzzle
 */
public class Board {

	/**
	 * Default-Sizes for the board
	 */
	public static final int C_BoardWidth = 230;
	public static final int C_BoardHeight = 300;

	/**
	 * The disk positions
	 */
	public enum eDisc {
		UpperDisc, LowerDisc
	}

	/**
	 * The disc rotations
	 */
	public enum eDirection {
		Left, Right
	}

	/**
	 * Struct to hold a move
	 */
	public class Move {
		public eDisc m_disc;
		public eDirection m_dir;

		public Move(eDisc disc, eDirection dir) {
			m_disc = disc;
			m_dir = dir;
		}
	}

	/**
	 * A list of moves for a game
	 */
	private ArrayList<Move> m_moves;

	/**
	 * State variables for the board
	 */
	private int m_rotationDelay;
	private int m_rotationSteps;
	private int m_swingSteps;
	private boolean m_bSwing;
	private int m_numTurns;
	private boolean m_bShowTurns;

	/**
	 * Current size of the board
	 */
	private int m_h = C_BoardHeight;
	private int m_w = C_BoardWidth;

	/**
	 * Current positions of the stones and bones
	 */
	private int[] m_upperBones = new int[6];
	private int[] m_lowerBones = new int[6];
	private int[] m_upperStones = new int[6];
	private int[] m_lowerStones = new int[6];

	/**
	 * Figures in the board (stones and bones)
	 */
	private Figure[] m_stones = new Figure[10];
	private Figure[] m_bones = new Figure[11];
	private Figure[] m_frames = new Figure[18];

	/**
	 * Coordinates of the center of the two discs
	 */
	private PointF m_upperCenter = new PointF(116.60254F, 100);
	private PointF m_lowerCenter = new PointF(116.60254F, 200);

	/**
	 * Bitmaps for the board
	 */
	private Bitmap m_upperDisk = null;
	private Bitmap m_lowerDisk = null;
	private Bitmap m_background = null;

	/**
	 * The last disk that has been rotated
	 */
	private eDisc m_rotDisc;

	/**
	 * Number of the random moves in the current game
	 */
	public int GameMoves;

	/**
	 * The moves of the puzzler (without the random moves)
	 */
	public int getNumMoves() {
		return m_moves.size() - GameMoves;
	}

	/**
	 * Returns the X-part of the center of the board
	 */
	private float getMiddleX() {
		return (m_upperCenter.x + m_lowerCenter.x) / 2.0f;
	}

	/**
	 * Returns the Y-part of the center of the board
	 */
	private float getMiddleY() {
		return (m_upperCenter.y + m_lowerCenter.y) / 2.0f;
	}

	/**
	 * Background Image bitmap
	 */
	public Bitmap getBackground() {
		return m_background;
	}

	/**
	 * Uppder disc Bitmap
	 */
	public Bitmap getUpperDisk() {
		return m_upperDisk;
	}

	/**
	 * Lower disc bitmap
	 */
	public Bitmap getLowerDisk() {
		return m_lowerDisk;
	}

	/**
	 * The disc currently rotating
	 */
	public eDisc getRotDisk() {
		return m_rotDisc;
	}

	/**
	 * Constructor
	 * 
	 * Inits a board with the current startup level
	 */
	public Board() {
	}

	/**
	 * Start a new game by random rotation of the discs
	 * 
	 * @param view
	 *            to refresh the screen
	 */
	public void newGame(BoardView view) {

		// Get the settings for new games
		m_numTurns = GamePrefs.Prefs.getInt(GamePrefs.C_Turns, GamePrefs.C_Turns_Default);
		m_bShowTurns = GamePrefs.Prefs.getBoolean(GamePrefs.C_ShowTurns, GamePrefs.C_ShowTurns_Default);
		m_moves = new ArrayList<Move>();
		GameMoves = 0;

		Random r = new Random();
		for (int i = 0; i < m_numTurns; i++) {
			eDirection dir = eDirection.Right;
			eDisc disk = eDisc.UpperDisc;
			if (i % 2 == 0) {
				disk = eDisc.LowerDisc;
			}

			int anz = r.nextInt(5) + 1;
			if (anz > 3) {
				dir = eDirection.Left;
				anz = anz - 3;
			}

			GameMoves += anz;

			for (int j = 0; j < anz; j++) {
				rotate(disk, dir, m_bShowTurns, view);
				if (m_bShowTurns) {
					try {
						Thread.sleep(m_rotationDelay);
					} catch (Exception ex) {
					}
				}
			}
		}

		// Check if the board is solved yet by accident
		String s = getColorString();
		String s1 = Block.getColorString();
		if (s.equals(s1)) {
			// Do some rotations
			rotate(eDisc.LowerDisc, eDirection.Right, false, view);
			rotate(eDisc.LowerDisc, eDirection.Right, false, view);
			rotate(eDisc.LowerDisc, eDirection.Right, false, view);
			rotate(eDisc.UpperDisc, eDirection.Left, false, view);
			rotate(eDisc.UpperDisc, eDirection.Left, false, view);
			rotate(eDisc.UpperDisc, eDirection.Left, false, view);

			GameMoves += 6;
		}

		// If the turns have not been shown, we have to rebuild the
		// bitmaps here
		if (!m_bShowTurns) {
			createUpperDisk();
			createLowerDisk();
			view.repaint();
		}

	}

	/**
	 * Init the board for a given level
	 * 
	 * @param level
	 *            the new game level for the board
	 */
	public void initBoard(int level) {

		// Get settings
		m_rotationDelay = GamePrefs.Prefs.getInt(GamePrefs.C_Delay, GamePrefs.C_Delay_Default);
		m_rotationSteps = GamePrefs.Prefs.getInt(GamePrefs.C_Steps, GamePrefs.C_Steps_Default);
		m_swingSteps = GamePrefs.Prefs.getInt(GamePrefs.C_Swings, GamePrefs.C_Swings_Default);
		m_bSwing = GamePrefs.Prefs.getBoolean(GamePrefs.C_ShowSwings, GamePrefs.C_ShowSwings_Default);
		m_numTurns = GamePrefs.Prefs.getInt(GamePrefs.C_Turns, GamePrefs.C_Turns_Default);
		m_bShowTurns = GamePrefs.Prefs.getBoolean(GamePrefs.C_ShowTurns, GamePrefs.C_ShowTurns_Default);
		m_upperCenter = new PointF(116.60254F, 100);
		m_lowerCenter = new PointF(116.60254F, 200);

		// Reset active game
		m_moves = new ArrayList<Move>();
		GameMoves = 0;

		// Init the stones and bones
		m_stones = new Figure[10];
		m_bones = new Figure[11];
		m_frames = new Figure[18];

		// Build the blocks and color them
		Block.Init(level);

		// Build the stones and bones with the blocks
		m_bones[0] = new Figure();
		m_bones[0].addBlock(28);
		m_bones[0].addBlock(29);

		for (int i = 1; i < 6; i++) {
			m_bones[i] = new Figure();
			m_bones[i].addBlock(5 * (i - 1) + 3);
			m_bones[i].addBlock(5 * (i - 1) + 4);
		}
		for (int i = 0; i < 6; i++) {
			m_stones[i] = new Figure();
			m_stones[i].addBlock(5 * i);
			m_stones[i].addBlock(5 * i + 1);
			m_stones[i].addBlock(5 * i + 2);
		}

		for (int i = 6; i < 11; i++) {
			m_bones[i] = new Figure();
			m_bones[i].addBlock(5 * i);
			m_bones[i].addBlock(5 * i + 1);

		}
		for (int i = 6; i < 10; i++) {
			m_stones[i] = new Figure();
			m_stones[i].addBlock(5 * i + 2);
			m_stones[i].addBlock(5 * i + 3);
			m_stones[i].addBlock(5 * i + 4);
		}

		// Create the frames
		for (int i = 0; i < 18; i++) {
			m_frames[i] = new Figure();
			m_frames[i].addBlock(i + 52);
		}

		// Build the default array of the stones and bones-numbers of
		// the upper and lower disc
		for (int i = 0; i < 6; i++) {
			m_upperBones[i] = i;
			m_lowerBones[i] = i + 5;
			m_upperStones[i] = i;
			m_lowerStones[i] = i + 4;
		}

		m_w = C_BoardWidth;
		m_h = C_BoardHeight;

	}

	/**
	 * Paints a disk depending on the current position
	 * 
	 * @param canvas
	 *            to draw into
	 * @param disc
	 *            which should be painted
	 */
	private void paintDisk(Canvas canvas, eDisc disc) {
		if (disc == eDisc.UpperDisc) {
			for (int i = 0; i < 6; i++) {
				m_bones[m_upperBones[i]].onPaint(canvas);
				m_stones[m_upperStones[i]].onPaint(canvas);
			}
		} else {
			for (int i = 0; i < 6; i++) {
				m_bones[m_lowerBones[i]].onPaint(canvas);
				m_stones[m_lowerStones[i]].onPaint(canvas);
			}
		}
	}

	/**
	 * Paints the background
	 * 
	 * @param canvas
	 *            to draw into
	 */
	private void paintBackground(Canvas canvas) {
		// Show all frames
		for (int i = 0; i < 18; i++) {
			m_frames[i].onPaint(canvas);
		}
	}

	/**
	 * Get the colorstring for all blocks in the board.
	 * 
	 * The colorstring can be used to check if the board is equal to the
	 * original board - puzzle solved!
	 * 
	 * @return a color string for the current board
	 */
	public String getColorString() {
		StringBuilder sb = new StringBuilder();

		// First the stones and bones of the upper disc
		for (int i = 0; i < 5; i++) {
			sb.append(m_stones[m_upperStones[i]].getColorString());
			sb.append(m_bones[m_upperBones[i + 1]].getColorString());
		}
		sb.append(m_stones[m_upperStones[5]].getColorString());
		sb.append(m_bones[m_upperBones[0]].getColorString());

		// .. and now the lower disc
		sb.append(m_bones[m_lowerBones[1]].getColorString());
		for (int i = 2; i < 6; i++) {
			sb.append(m_stones[m_lowerStones[i]].getColorString());
			sb.append(m_bones[m_lowerBones[i]].getColorString());
		}

		return sb.toString();
	}

	/**
	 * Resize the graphics
	 * 
	 * @param newW
	 *            is the new width of the board
	 * @param newH
	 *            is the neu height of the board
	 * @param screenW
	 *            is the width of the full screen
	 */
	public void onResize(int newW, int newH, int screenW) {
		// Calculate the transform matrix
		float dy = 1.0f * newH / m_h;
		Matrix m = new Matrix();
		m.setScale(dy, dy);

		// Scale all blocks
		Block.transformAll(m);

		// Get the center of the uppder disc using the bounds
		RectF rfu1 = new RectF();
		Block.getBlocks()[52].GP.computeBounds(rfu1, true);

		float xu1 = rfu1.left + (rfu1.width() / 2.0f);

		// Move the blocks to the center
		// of the screen
		float dd = screenW / 2.0f - xu1;
		m.reset();
		m.setTranslate(dd, 0f);

		// Translate all blocks
		Block.transformAll(m);

		// adjust the centers of the discs with the center blocks
		// of both discs
		RectF rfu = new RectF();
		Block.getBlocks()[52].GP.computeBounds(rfu, true);

		RectF rfl = new RectF();
		Block.getBlocks()[53].GP.computeBounds(rfl, true);

		m_upperCenter.x = rfu.left + (rfu.width() / 2.0f);
		m_upperCenter.y = rfu.top + (rfu.height() / 2.0f);
		m_lowerCenter.x = rfl.left + (rfl.width() / 2.0f);
		m_lowerCenter.y = rfl.top + (rfl.height() / 2.0f);

		// save the current size
		m_h = newH;
		m_w = newW;

		// Create all the bitmaps newly
		createBackground();
		createUpperDisk();
		createLowerDisk();
	}

	/**
	 * Creates bitmap for upper disc
	 */
	private void createUpperDisk() {
		// Create a bitmap
		if (m_upperDisk != null) {
			m_upperDisk.recycle();
			m_upperDisk = null;
		}
		m_upperDisk = Bitmap.createBitmap(m_w, m_h, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas();
		canvas.setBitmap(m_upperDisk);

		// Paint the disk
		paintDisk(canvas, eDisc.UpperDisc);

		// Clean up
		canvas = null;
		System.gc();
	}

	/**
	 * Creates bitmap for lower disc
	 */
	private void createLowerDisk() {
		// Create a bitmap
		if (m_lowerDisk != null) {
			m_lowerDisk.recycle();
			m_lowerDisk = null;
		}
		m_lowerDisk = Bitmap.createBitmap(m_w, m_h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas();
		canvas.setBitmap(m_lowerDisk);

		// Paint the disk
		paintDisk(canvas, eDisc.LowerDisc);

		// Clean up
		canvas = null;
		System.gc();
	}

	/**
	 * Creates bitmap for the background
	 */
	private void createBackground() {
		// Create a bitmap
		if (m_background != null) {
			m_background.recycle();
			m_background = null;
		}
		m_background = Bitmap.createBitmap(m_w, m_h, Bitmap.Config.ARGB_8888);
		Canvas g = new Canvas();
		g.setBitmap(m_background);

		// Paint the board
		paintBackground(g);

		// Clean up
		g = null;
		System.gc();
	}

	/**
	 * React on MouseDown for the graphic-buttons to turn the discs
	 * 
	 * @param point
	 *            is the clicke point
	 * @param view
	 *            to refresh the screen
	 * 
	 * @return true if the click has been handled, false otherwise
	 */
	public boolean onMouseDown(PointF point, BoardView view) {

		// Check the four buttons
		if (Block.getBlocks()[62].isPointInBlock(point)) {

			// Upper disc right
			rotate(Board.eDisc.UpperDisc, Board.eDirection.Right, true, view);
			return true;
		} else if (Block.getBlocks()[63].isPointInBlock(point)) {

			// Upper disc left
			rotate(Board.eDisc.UpperDisc, Board.eDirection.Left, true, view);
			return true;
		} else if (Block.getBlocks()[64].isPointInBlock(point)) {

			// Lower disc left
			rotate(Board.eDisc.LowerDisc, Board.eDirection.Left, true, view);
			return true;
		} else if (Block.getBlocks()[65].isPointInBlock(point)) {

			// Lower disc right
			rotate(Board.eDisc.LowerDisc, Board.eDirection.Right, true, view);
			return true;
		}

		// Click not handled
		return false;
	}

	/**
	 * Rotates a disc graphically with the possibility of overswing
	 * 
	 * @param bones
	 *            Array with the numbers of bones
	 * @param stones
	 *            Array with the numbers of stones
	 * @param c
	 *            Rotation center
	 * @param deg
	 *            Rotation degrees per step
	 * @param steps
	 *            Rotation steps
	 * @param zi
	 *            Swing
	 * @param de
	 *            Delta for swing
	 * @param bShow
	 *            If true the rotation will be made visible
	 * @param disc
	 *            the disc to rotate
	 * @param view
	 *            to refresh the screen
	 */
	private void rotateDisk(int[] bones, int[] stones, PointF c, float deg, int steps, float zi, float de, boolean bShow,
			eDisc disc, BoardView view) {

		// Create the necessary matrix
		Matrix mat1 = new Matrix();
		mat1.setRotate(deg, c.x, c.y);

		// Do the rotation of all bones and stones
		for (int s = 0; s < steps; s++) {
			for (int i = 0; i < 6; i++) {
				m_bones[bones[i]].transform(mat1);
				m_stones[stones[i]].transform(mat1);
			}

			if (bShow) {
				if (s < steps - 1) {
					try {
						Thread.sleep(m_rotationDelay);
					} catch (Exception ex) {
					}

				}
				if (disc == eDisc.UpperDisc) {
					createUpperDisk();
				} else {
					createLowerDisk();
				}
				view.repaint();
			}
		}

		// Show the swing
		if (m_bSwing) {

			// Matrices for the swing
			Matrix mat2 = new Matrix();
			Matrix mat3 = new Matrix();

			float s1 = 0 + zi;
			float s2 = 0 - (zi + de);

			mat2.setRotate(s1 - s2, c.x, c.y);
			mat3.setRotate(-s1, c.x, c.y);

			while (zi != 0) {

				for (int i = 0; i < 6; i++) {
					m_bones[bones[i]].transform(mat3);
					m_stones[stones[i]].transform(mat3);
				}
				if (bShow) {
					if (disc == eDisc.UpperDisc) {
						createUpperDisk();
					} else {
						createLowerDisk();
					}
					view.repaint();
					try {
						Thread.sleep(m_rotationDelay);
					} catch (Exception ex) {
					}
				}

				for (int i = 0; i < 6; i++) {
					m_bones[bones[i]].transform(mat2);
					m_stones[stones[i]].transform(mat2);
				}
				if (bShow) {
					if (disc == eDisc.UpperDisc) {
						createUpperDisk();
					} else {
						createLowerDisk();
					}

					view.repaint();
					try {
						Thread.sleep(m_rotationDelay);
					} catch (Exception ex) {
					}
				}

				zi += de;

				s1 = 0 + zi;
				s2 = 0 - (zi + de);

				mat3.reset();
				mat3.setRotate(-s1 - zi, c.x, c.y);
				mat2.reset();
				mat2.setRotate(s1 - s2, c.x, c.y);

			}
		}
	}

	/**
	 * Prepare params for the graphic disc rotation
	 * 
	 * @param deg
	 *            Rotation degrees
	 * @param disc
	 *            the disc to rotate
	 * @param dir
	 *            the rotation direction
	 * @param bShow
	 *            If true the rotation will be made visible
	 * @param view
	 *            to refresh the screen
	 */
	private void rotateDisk(float deg, eDisc disc, eDirection dir, boolean bShow, BoardView view) {
		float z;
		float d;
		int steps = 1;

		// When show the rotation calculate
		// how many degrees per step
		if (bShow) {
			steps = m_rotationSteps;
			deg = deg / steps;
		}

		// Calc the parameter for the real rotation
		if (disc == eDisc.UpperDisc) {
			if (dir == eDirection.Left) {
				deg = -deg;
				z = m_swingSteps;
				d = -1;
			} else {
				z = -m_swingSteps;
				d = 1;
			}
			rotateDisk(m_upperBones, m_upperStones, m_upperCenter, deg, steps, z, d, bShow, disc, view);
		} else {
			if (dir == eDirection.Left) {
				deg = -deg;
				z = m_swingSteps;
				d = -1;
			} else {
				z = -m_swingSteps;
				d = 1;
			}
			rotateDisk(m_lowerBones, m_lowerStones, m_lowerCenter, deg, steps, z, d, bShow, disc, view);
		}
	}

	/**
	 * Rotate a disc. Grphically an logically.
	 * 
	 * @param disc
	 *            the disc to rotate
	 * @param dir
	 *            the rotation direction
	 * @param bShow
	 *            If true the rotation will be made visible
	 * @param view
	 *            to refresh the screen
	 */
	private void rotate(eDisc disc, eDirection dir, boolean bShow, BoardView view) {

		// Check if prefs has been changed
		if (GamePrefs.PrefsChanged) {
			GamePrefs.PrefsChanged = false;
			m_rotationDelay = GamePrefs.Prefs.getInt(GamePrefs.C_Delay, GamePrefs.C_Delay_Default);
			m_rotationSteps = GamePrefs.Prefs.getInt(GamePrefs.C_Steps, GamePrefs.C_Steps_Default);
			m_swingSteps = GamePrefs.Prefs.getInt(GamePrefs.C_Swings, GamePrefs.C_Swings_Default);
			m_bSwing = GamePrefs.Prefs.getBoolean(GamePrefs.C_ShowSwings, GamePrefs.C_ShowSwings_Default);
		}
		
		// Local vars for the new figure positions
		int[] newD = new int[6];
		int[] newD2 = new int[6];

		// Remember the rotated disk
		m_rotDisc = disc;

		// Add the move
		m_moves.add(new Move(disc, dir));

		// Graphically rotation
		rotateDisk(60.0f, disc, dir, bShow, view);

		// Logically rotation - adjust the stones and bones on the disks and
		// take incount
		// that there are two stone and one bone that overlapp
		if (disc == eDisc.UpperDisc) {
			if (dir == eDirection.Left) {
				for (int i = 0; i < 6; i++) {
					if (m_upperStones[i] >= 6) {
						m_stones[m_upperStones[i]].incOrient();
					}

					int idx = i + 5;
					if (idx > 5) {
						idx -= 6;
					}
					newD[idx] = m_upperBones[i];
					newD2[idx] = m_upperStones[i];
				}
			} else {
				for (int i = 0; i < 6; i++) {
					if (m_upperStones[i] >= 6) {
						m_stones[m_upperStones[i]].decOrient();
					}

					int idx = i - 5;
					if (idx < 0) {
						idx += 6;
					}
					newD[idx] = m_upperBones[i];
					newD2[idx] = m_upperStones[i];
				}
			}

			for (int i = 0; i < 6; i++) {
				m_upperBones[i] = newD[i];
				m_upperStones[i] = newD2[i];
			}

			m_lowerBones[0] = m_upperBones[5];
			m_lowerStones[0] = m_upperStones[4];
			m_lowerStones[1] = m_upperStones[5];

		} else {
			if (dir == eDirection.Right) {
				for (int i = 0; i < 6; i++) {
					if (m_lowerStones[i] < 6) {
						m_stones[m_lowerStones[i]].incOrient();
					}

					int idx = i + 5;
					if (idx > 5) {
						idx -= 6;
					}
					newD[idx] = m_lowerBones[i];
					newD2[idx] = m_lowerStones[i];
				}
			} else {

				for (int i = 0; i < 6; i++) {
					if (m_lowerStones[i] < 6) {
						m_stones[m_lowerStones[i]].decOrient();
					}

					int idx = i - 5;
					if (idx < 0) {
						idx += 6;
					}
					newD[idx] = m_lowerBones[i];
					newD2[idx] = m_lowerStones[i];
				}
			}

			for (int i = 0; i < 6; i++) {
				m_lowerBones[i] = newD[i];
				m_lowerStones[i] = newD2[i];
			}

			m_upperBones[5] = m_lowerBones[0];
			m_upperStones[4] = m_lowerStones[0];
			m_upperStones[5] = m_lowerStones[1];

		}

		// Create the bitmaps newly and show them
		if (bShow) {
			createUpperDisk();
			createLowerDisk();
			view.repaint();
		}
	}

	/**
	 * Handle the MouseMove-Event when dragging a disk. Dragging will be
	 * translated into a rotation of the disc. The two points define a vector
	 * which defines the rotation direction
	 * 
	 * @param x1
	 *            starting x-coordinate
	 * @param y1
	 *            starting y-coordinate
	 * @param x2
	 *            end x-coordinate
	 * @param y2
	 *            end y-coordinate
	 * @param view
	 *            to refresh the screen
	 * 
	 * @return false if the vector length is less than 10 pixel, true otherwise
	 */
	public boolean turnDisk(float x1, float y1, float x2, float y2, BoardView view) {
		eDisc disc = eDisc.UpperDisc;
		eDirection dir = eDirection.Left;
		float cy;

		// Determin the disk - just look at the hor. middle of the board
		if (y1 < getMiddleY()) {
			disc = eDisc.UpperDisc;
			cy = m_upperCenter.y;
		} else {
			disc = eDisc.LowerDisc;
			cy = m_lowerCenter.y;
		}

		// Move the coordinates so that the y-axle is in the middle of the board
		x1 -= getMiddleX();
		x2 -= getMiddleX();

		// Because 0/0 is upper left corner, we have to inverse the y-coordinate
		y1 = -(y1 - cy);
		y2 = -(y2 - cy);

		// If the drag is too short (length of the turning vector) - do nothing
		// Get the turning vector
		float vx = x2 - x1;
		float vy = y2 - y1;
		if (Math.sqrt(vx * vx + vy * vy) < 10) {
			return false;
		}

		// Calc vector product to get the orientation
		double orient = x1 * y2 - y1 * x2;
		if (orient > 0) {
			dir = eDirection.Left;
		} else {
			dir = eDirection.Right;
		}

		// Do the rotations
		rotate(disc, dir, true, view);

		return true;
	}

	/**
	 * Flash the fields of the board with random colors to show that the puzzle
	 * has been solved
	 */
	public void colorStones(BoardView view) {
		Random r = new Random();

		// Do the rotation of all bones and stones
		for (int s = 0; s < 10; s++) {
			for (int i = 0; i < 62; i++) {
				Block.getBlocks()[i].Col = r.nextInt(7);
			}

			createBackground();
			createUpperDisk();
			createLowerDisk();
			view.repaint();

			try {
				Thread.sleep(1);
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * Redo all the moves in the given string
	 * 
	 * @param rots
	 *            a string with the moves
	 * @param view
	 *            a reference to the view for refreshing the board
	 */
	public void setBoard(String rots, BoardView view) {
		for (int i = 0; i < rots.length(); i += 2) {
			eDisc disc = eDisc.UpperDisc;
			eDirection dir = eDirection.Right;
			if (rots.charAt(i) == '1') {
				disc = eDisc.LowerDisc;
			}
			if (rots.charAt(i + 1) == '1') {
				dir = eDirection.Left;
			}

			// Rotate the disc but don't show it
			rotate(disc, dir, false, view);
		}

		// Create all the bitmaps newly
		createBackground();
		createUpperDisk();
		createLowerDisk();
	}

	/**
	 * Returns all the moves as a string of pairs of 0/1 for each move The first
	 * char defines the disc and the second one the direction.
	 * 
	 * @return a string with all moves
	 */
	public String getMoves() {

		// Write the moves
		String sRow = "";
		for (Move m : m_moves) {
			if (m.m_disc == eDisc.UpperDisc) {
				sRow += "0";
			} else {
				sRow += "1";
			}
			if (m.m_dir == eDirection.Right) {
				sRow += "0";
			} else {
				sRow += "1";
			}
		}
		return sRow;
	}

}
