package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Graphics;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Element;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
class VisualSelection extends Element {
	
	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		g.setColor(Color.black);
		GraphicsTools.setDashedStroke(g);
		g.drawRect(Math.min(getStart().getX(), getEnd().getX()), Math.min(getStart().getY(), getEnd().getY()),getWidth(), getHeight());
		GraphicsTools.setDefaultStroke(g);
	}
}
