package net.matmas.pneditor.features;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class ScrollingFeature extends Feature implements MouseListener, MouseMotionListener, AdjustmentListener {

	public ScrollingFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.getDrawingBoard().getHorizontalScrollBar().addAdjustmentListener(this);
		canvas.getDrawingBoard().getVerticalScrollBar().addAdjustmentListener(this);
	}
	
	private int prevDragX;
	private int prevDragY;
	private boolean scrolling;

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON2 ||
			e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {
			prevDragX = e.getX();
			prevDragY = e.getY();
			scrolling = true;
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (scrolling) {
			doTheScrolling(e.getX(), e.getY());
			prevDragX = e.getX();
			prevDragY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (scrolling) {
			doTheScrolling(e.getX(), e.getY());
			scrolling = false;
		}
	}

	private void doTheScrolling(int mouseX, int mouseY) {
		Point viewTranslation = canvas.getViewTranslation();
		canvas.setViewTranslation(viewTranslation.getTranslated(mouseX - prevDragX, mouseY - prevDragY));
		canvas.repaint();
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getVerticalScrollBar().setValue(canvas.getY() - canvas.getViewTranslation().getY());
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getHorizontalScrollBar().setValue(canvas.getX() - canvas.getViewTranslation().getX());
	}

	@Override
	public Cursor getCursor(MouseEvent e) {
		if (scrolling) {
			return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		}
		return null;
	}

	public void drawForeground(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (!scrolling) {
			Point viewTranslation = canvas.getViewTranslation();
			if (e.getSource() == canvas.getDrawingBoard().getHorizontalScrollBar()) {
				viewTranslation = new Point(- e.getValue(), viewTranslation.getY());
			}
			if (e.getSource() == canvas.getDrawingBoard().getVerticalScrollBar()) {
				viewTranslation = new Point(viewTranslation.getX(), - e.getValue());
			}
			canvas.setViewTranslation(viewTranslation);
			canvas.repaint();
		}
	}
}
