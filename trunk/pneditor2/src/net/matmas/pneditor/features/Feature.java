package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Map;
import net.matmas.pnapi.Element;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public abstract class Feature {

	protected Canvas canvas;

	public Feature(Canvas canvas) {
		this.canvas = canvas;
	}

	public abstract void drawForeground(Graphics g);
	public abstract void drawMainLayer(Graphics g);
	public abstract void drawBackground(Graphics g);
	
	public Cursor getCursor(MouseEvent e) {
		return null;
	}

	public void setElementColors(MouseEvent e, Map<Element, Color> elementColors) {
	}
	
	protected Point getClickedPoint(MouseEvent e) {
		int x = (int)(Math.round(e.getX() - canvas.getViewTranslation().getX()) / canvas.getZoom());
		int y = (int)(Math.round(e.getY() - canvas.getViewTranslation().getY()) / canvas.getZoom());
		return new Point(x, y);
	}

	protected Element getClickedElement(Point clickedPoint) {
		return PNEditor.getInstance().getDocument().getPetriNet().getElementByLocation(clickedPoint);
	}
}
