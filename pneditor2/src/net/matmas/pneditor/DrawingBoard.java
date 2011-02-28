package net.matmas.pneditor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author matmas
 */
public class DrawingBoard extends JPanel {
	
	private JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, 100);
	private JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, 100);
	private Canvas canvas = new Canvas(this);

	public DrawingBoard() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.add(canvas, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(verticalScrollBar, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		this.add(horizontalScrollBar, constraints);

		verticalScrollBar.setUnitIncrement(30);
		horizontalScrollBar.setUnitIncrement(30);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JScrollBar getVerticalScrollBar() {
		return verticalScrollBar;
	}

	public JScrollBar getHorizontalScrollBar() {
		return horizontalScrollBar;
	}
	
}
