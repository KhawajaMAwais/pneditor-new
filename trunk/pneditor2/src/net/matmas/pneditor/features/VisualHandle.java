package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Graphics;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Element;

/**
 *
 * @author matmas
 */
class VisualHandle extends Element {
	
	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		g.setColor(Color.blue);
		g.drawRect(Math.min(getStart().getX(), getEnd().getX()), Math.min(getStart().getY(), getEnd().getY()), getWidth(), getHeight());
	}
}
