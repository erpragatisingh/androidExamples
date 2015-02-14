/**
 * Block.java
 * 
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Matrix;

/**
 * The class for the blocks on the board. A block is the smallest part of the
 * board graphics.
 */
public class Block {

	/**
	 * Gets/Sets the graphics path of the block
	 */
	public Path GP;

	/**
	 * Gets/Sets the color number
	 */
	public int Col;

	/**
	 * Gets/Sets the pen number
	 */
	public int Edge;

	/**
	 * Constructor
	 */
	public Block() {
		GP = new Path();
		Col = -1;
		Edge = -1;
	}

	/**
	 * Paint the block to the given canvas
	 * 
	 * @param canvas
	 *            to paint to
	 */
	public void onPaint(Canvas canvas) {

		// Check the ranges for the color and the pen
		if (Col >= 0 && Col < m_colors.length) {
			canvas.drawPath(GP, m_colors[Col]);
		}
		if (Edge >= 0 && Edge < m_pens.length) {
			canvas.drawPath(GP, m_pens[Edge]);
		}
	}

	/**
	 * Returns true if the given point belongs to the path of the block This
	 * function will be used to detect if a click was on a painted block
	 * 
	 * @param p
	 *            the point to check
	 * 
	 * @return true it the point is in the block
	 */
	public boolean isPointInBlock(PointF p) {
		boolean ret = false;
		RectF bounds = new RectF();

		// Get the bounds of the path and check the point against it
		GP.computeBounds(bounds, true);
		if (p.x >= bounds.left && p.x <= bounds.right && p.y >= bounds.top && p.y <= bounds.bottom) {
			return true;
		}
		return ret;
	}

	/**
	 * All the defined blocks
	 */
	private static Block[] m_blocks = null;

	/**
	 * All the defined colors
	 */
	private static Paint[] m_colors = null;

	/**
	 * The defined pens
	 */
	private static Paint[] m_pens = null;

	/**
	 * The colors of the blocks for the different game levels
	 */
	private static int[][] m_games = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
					1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
					1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 2, 2, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					1, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1, 0, 2, 1, 2, 2, 0, 1 },
			{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2,
					2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 2, 3 },
			{ 1, 1, 1, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 2, 2,
					2, 2, 2, 2, 2, 2, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 0, 4, 1, 3, 4, 2, 4 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 1, 1, 1, 1, 1, 1, 1, 1, 6, 6, 2, 2, 2, 2, 2, 2, 2, 2, 6, 6, 6, 6, 3, 3, 3, 3, 3, 3, 3,
					3, 6, 6, 4, 4, 4, 4, 4, 4, 4, 4, 6, 6, 6, 6, 0, 6, 1, 6, 6, 4, 6, 3 },
			{ 2, 0, 5, 2, 0, 3, 0, 2, 3, 0, 2, 0, 3, 2, 0, 5, 0, 2, 5, 0, 1, 0, 5, 1, 0, 5, 0, 1, 5, 0, 1, 5, 6, 5, 1, 1, 6, 3, 6,
					1, 1, 3, 6, 3, 1, 1, 6, 5, 6, 1, 1, 5, 0, 1, 2, 3, 2, 5, 5, 6, 3, 6 },
			{ 2, 0, 5, 2, 0, 3, 0, 2, 3, 0, 4, 0, 3, 4, 0, 5, 0, 4, 5, 0, 1, 0, 5, 1, 0, 5, 0, 1, 5, 0, 1, 5, 4, 5, 1, 1, 4, 3, 4,
					1, 1, 3, 2, 3, 1, 1, 2, 5, 2, 1, 1, 5, 0, 1, 2, 3, 4, 5, 5, 2, 3, 4 },
			{ 2, 0, 5, 2, 0, 3, 0, 2, 3, 0, 4, 0, 3, 4, 0, 6, 0, 4, 6, 0, 1, 0, 6, 1, 0, 5, 0, 1, 5, 0, 1, 5, 4, 5, 1, 1, 4, 3, 4,
					1, 1, 3, 2, 3, 1, 1, 2, 6, 2, 1, 1, 6, 0, 1, 2, 3, 4, 5, 6, 2, 3, 4 } };

	/**
	 * The default colors for the board
	 */
	private static final String C_Colors = "#006400,#8B7500,#CD6600,#008080,#551A8B,#802A2A,#00008B,#FFFFFF,#000000";

	/**
	 * Returns the global static blocks array
	 */
	public static Block[] getBlocks() {
		return m_blocks;
	}

	/**
	 * Returns the static array of solid brushes
	 */
	public static Paint[] getColors() {
		return m_colors;
	}

	/**
	 * Gets the array of brushes based on a list of colors
	 */
	public static void createBrushes() {
		String br = C_Colors;
		String[] brs = br.split(",");

		m_colors = new Paint[brs.length];
		for (int i = 0; i < brs.length; i++) {
			m_colors[i] = new Paint();
			m_colors[i].setAntiAlias(true);
			m_colors[i].setStyle(Style.FILL);
			m_colors[i].setColor(Color.parseColor(brs[i]));
		}
	}

	/**
	 * Init all blocks and colors etc. for a given game level Blocks 0..51 are
	 * the blocks for the game-board Blocks 52/53 are the centers of the two
	 * disks Blocks 54..61 build the frame of the board Blocks 62..69 are the
	 * buttons and arrows to turn the disks
	 * 
	 * @param level
	 *            the starting level to color the blocks
	 */
	public static void Init(int level) {

		// Create all the static blocks or reset them
		if (m_blocks == null) {
			m_blocks = new Block[70];
			for (int i = 0; i < 70; i++) {
				m_blocks[i] = new Block();
			}
		} else {
			// Reset the blocks if they already exist
			for (int i = 0; i < 70; i++) {
				m_blocks[i].GP.reset();
			}
		}

		// Init the colors from the settings
		if (m_colors == null) {
			createBrushes();
		}

		// Create the pens
		if (m_pens == null) {
			m_pens = new Paint[2];
			m_pens[0] = new Paint();
			m_pens[0].setAntiAlias(true);
			m_pens[0].setStyle(Style.STROKE);
			m_pens[0].setColor(Color.WHITE);
			m_pens[0].setStrokeWidth(1.0f);

			m_pens[1] = new Paint();
			m_pens[1].setAntiAlias(true);
			m_pens[1].setStyle(Style.STROKE);
			m_pens[1].setColor(Color.YELLOW);
			m_pens[1].setStrokeWidth(1.0f);
		}

		// Create the first sub-part of the first stone
		m_blocks[0].GP.addArc(new RectF(6.60254F, 20, 166.60254F, 180), 180, 21.31781F);
		m_blocks[0].GP.arcTo(new RectF(-80, 70, 80, 230), 278.68219F, 21.31781F);
		m_blocks[0].GP.lineTo(28.86751F, 100);
		m_blocks[0].GP.close();

		Matrix mat120 = new Matrix();
		mat120.setRotate(120.0F, 28.86751F, 100);

		// The second sub-part of the first stone (rotate the first by 120
		// degrees)
		m_blocks[1].GP.addPath(m_blocks[0].GP);
		m_blocks[1].GP.transform(mat120);

		// The third sub-part of the first stone (rotate the second part by 120
		// degrees)
		m_blocks[2].GP.addPath(m_blocks[1].GP);
		m_blocks[2].GP.transform(mat120);

		// The first sub-part of the first bone
		m_blocks[3].GP.addArc(new RectF(6.60254F, -80, 166.60254F, 80), 120, 21.31781F);
		m_blocks[3].GP.arcTo(new RectF(6.60254F, 20, 166.60254F, 180), 218.68218F, -17.36437F);
		m_blocks[3].GP.arcTo(new RectF(-80, 70, 80, 230), 278.68219F, 21.31781F);
		m_blocks[3].GP.lineTo(46.60254F, 69.28203F);

		Matrix mat180 = new Matrix();
		mat180.setRotate(180.0F, 43.30127F, 75F);

		// The second sub-part of the first stone (rotate the first part by 180
		// degrees)
		m_blocks[4].GP.addPath(m_blocks[3].GP);
		m_blocks[4].GP.transform(mat180);

		// Create all the stones and bones of the upper disk based on the first
		// one
		// by rotating them six times by 60 degrees.
		Matrix mat60 = new Matrix();
		mat60.setRotate(60.0F, 86.60254F, 100);
		for (int i = 5; i < 30; i++) {
			m_blocks[i].GP.addPath(m_blocks[i - 5].GP);
			m_blocks[i].GP.transform(mat60);
		}

		Matrix matMin60 = new Matrix();
		matMin60.setRotate(-60.0F, 86.60254F, 200);

		// Create all the stones of the intersection
		for (int i = 30; i < 35; i++) {
			m_blocks[i].GP.addPath(m_blocks[i - 7].GP);
			m_blocks[i].GP.transform(matMin60);
		}

		// Create all the stones of the lower disk
		for (int i = 35; i < 52; i++) {
			m_blocks[i].GP.addPath(m_blocks[i - 5].GP);
			m_blocks[i].GP.transform(matMin60);
		}

		// Crate the center of the upper disk
		m_blocks[52].GP.addCircle(86.60254F, 100, 20, Path.Direction.CW);

		// Create the center of the lower disk (by translating the upper one)
		Matrix mat100 = new Matrix();
		mat100.setTranslate(0, 100);
		m_blocks[53].GP.addPath(m_blocks[52].GP);
		m_blocks[53].GP.transform(mat100);

		// Create the border by createing the necessary polygons
		m_blocks[54].GP.addArc(new RectF(6.60254F, 20, 166.60254F, 180), 180, 60);
		m_blocks[54].GP.lineTo(36.60254F, 13.39746f);
		m_blocks[54].GP.lineTo(-13.39746f, 100);
		m_blocks[54].GP.lineTo(6.60254F, 100);

		m_blocks[55].GP.addPath(m_blocks[54].GP);
		m_blocks[55].GP.transform(mat60);

		m_blocks[56].GP.addPath(m_blocks[55].GP);
		m_blocks[56].GP.transform(mat60);

		m_blocks[57].GP.moveTo(-13.39746F, 100);
		m_blocks[57].GP.lineTo(6.60254F, 100);
		m_blocks[57].GP.arcTo(new RectF(6.60254F, 20, 166.60254F, 180), 180, -38.68218F);
		m_blocks[57].GP.arcTo(new RectF(6.60254F, 120, 166.60254F, 280), 218.68218F, -38.68218F);
		m_blocks[57].GP.lineTo(-13.39746F, 200);
		m_blocks[57].GP.lineTo(14.60254F, 150);
		m_blocks[57].GP.lineTo(-13.39746F, 100);
		m_blocks[57].GP.close();

		mat180 = new Matrix();
		mat180.setRotate(180.0F, 86.60254F, 150F);

		m_blocks[58].GP.addPath(m_blocks[57].GP);
		m_blocks[58].GP.transform(mat180);

		m_blocks[59].GP.addPath(m_blocks[54].GP);
		m_blocks[59].GP.transform(mat180);

		m_blocks[60].GP.addPath(m_blocks[55].GP);
		m_blocks[60].GP.transform(mat180);

		m_blocks[61].GP.addPath(m_blocks[56].GP);
		m_blocks[61].GP.transform(mat180);

		// Create the clickable button-circle
		m_blocks[62].GP.addArc(new RectF(-27, 120, 3, 150), 0, 360);
		m_blocks[63].GP.addArc(new RectF(170, 120, 200, 150), 0, 360);
		m_blocks[64].GP.addArc(new RectF(-27, 150, 3, 180), 0, 360);
		m_blocks[65].GP.addArc(new RectF(170, 150, 200, 180), 0, 360);

		// Create the arrow in the first button
		m_blocks[66].GP.moveTo(-23, 124);
		m_blocks[66].GP.lineTo(-16, 124);
		m_blocks[66].GP.lineTo(-16, 125);
		m_blocks[66].GP.lineTo(-21, 125);
		m_blocks[66].GP.lineTo(-13, 133);
		m_blocks[66].GP.lineTo(-14, 134);
		m_blocks[66].GP.lineTo(-22, 126);
		m_blocks[66].GP.lineTo(-22, 131);
		m_blocks[66].GP.lineTo(-23, 131);
		m_blocks[66].GP.lineTo(-23, 124);

		// Copy the arrow to the other buttons
		Matrix matTrans = new Matrix();
		matTrans.setTranslate(0, 41F);
		m_blocks[67].GP.addPath(m_blocks[66].GP);
		m_blocks[67].GP.transform(matTrans);

		Matrix matRot = new Matrix();
		RectF bo = new RectF();
		m_blocks[67].GP.computeBounds(bo, true);
		matRot.setRotate(270.0F, bo.centerX(), bo.centerY());
		m_blocks[67].GP.transform(matRot);

		m_blocks[69].GP.addPath(m_blocks[66].GP);
		m_blocks[69].GP.transform(mat180);

		m_blocks[68].GP.addPath(m_blocks[67].GP);
		m_blocks[68].GP.transform(mat180);

		// Move everything a little bit to the right, so that the border on
		// the left side has positive coordinates
		Matrix m = new Matrix();
		m.setTranslate(30, 0);
		for (int i = 0; i < 70; i++) {
			m_blocks[i].GP.transform(m);
		}

		// No border for the centers of the disks
		m_blocks[52].Edge = -1;
		m_blocks[53].Edge = -1;

		// Game init with the given level
		// and default borders
		for (int i = 0; i < 62; i++) {
			m_blocks[i].Edge = 0;
			m_blocks[i].Col = m_games[level][i];
		}

		// Buttons and arrows
		for (int i = 62; i < 66; i++) {
			// Button form
			m_blocks[i].Col = 8;
			m_blocks[i].Edge = -1;

			// Text on buttons
			m_blocks[i + 4].Col = 8;
			m_blocks[i + 4].Edge = 0;
		}
	}

	/**
	 * Transforms all the blocks with the given matrix
	 * 
	 * @param m
	 *            the transformation matrix
	 */
	public static void transformAll(Matrix m) {
		for (int i = 0; i < 70; i++) {
			m_blocks[i].GP.transform(m);
		}
	}

	/**
	 * Returns the color-string for the original-setting of the blocks
	 * 
	 * @return the colorString for the original board
	 */
	public static String getColorString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 52; i++) {
			sb.append(Integer.toString(m_blocks[i].Col));
		}

		return sb.toString();
	}

}
