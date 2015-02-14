/**
 * Figure.java
 *  
 * @author Hodel Michael
 */
package ch.adiuvaris.enigma;

import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * A clas that holds two or three blocks to build a figure-part of the board -
 * called stone (3 blocks) or bone (2 blocks)
 */
public class Figure {

	/**
	 * The blocks that build up the stone or bone
	 */
	private ArrayList<Block> m_blocks;

	/**
	 * The current orientation of that figure. 0 means in the original
	 * orientation. 1 = 60 deg 2 = 120 deg
	 */
	private int m_orient;

	/**
	 * Constructor
	 */
	public Figure() {
		m_blocks = new ArrayList<Block>();
		m_orient = 0;
	}

	/**
	 * Increment the rotations
	 */
	public void incOrient() {
		m_orient++;
		m_orient %= 3;
	}

	/**
	 * Decrement rotation
	 */
	public void decOrient() {
		m_orient--;
		if (m_orient < 0) {
			m_orient += 3;
		}
	}

	/**
	 * Add a part to the figure
	 * 
	 * @param nr
	 *            is the number of the block in the global block array
	 */
	public void addBlock(int nr) {
		m_blocks.add(Block.getBlocks()[nr]);
	}

	/**
	 * Paint the figure in the canvas
	 * 
	 * @param canvas
	 *            to paint to
	 */
	public void onPaint(Canvas canvas) {
		for (Block block : m_blocks) {
			block.onPaint(canvas);
		}
	}

	/**
	 * Transform the graphics path of all blockparts of the figure
	 * 
	 * @param mat
	 *            the transformation matrix
	 */
	public void transform(Matrix mat) {
		for (Block block : m_blocks) {
			block.GP.transform(mat);
		}
	}

	/**
	 * Returns the colorstring of the blockparts. The orientation will take
	 * effect.
	 * 
	 * @return the color string for this figure
	 */
	public String getColorString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m_blocks.size(); i++) {
			// Add the current orientation to the block position number
			// and make sure, the the index is in the range of the
			// number of blocks in the figure.
			int idx = (i + m_orient) % 3;
			sb.append(Integer.toString(m_blocks.get(idx).Col));
		}

		return sb.toString();
	}

}
