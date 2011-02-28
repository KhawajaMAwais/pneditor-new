package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Subnet;
import net.matmas.pneditor.Canvas;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class SubnetCollapseExpandFeature extends Feature implements MouseListener {

	public SubnetCollapseExpandFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
			Point clickedPoint = getClickedPoint(e);
			Element clickedElement = getClickedElement(clickedPoint);
			if (clickedElement instanceof Subnet) {
				Subnet subnet = (Subnet)clickedElement;
				subnet.setCollapsed(!subnet.isCollapsed());
			}
		}
	}

	public void drawForeground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
