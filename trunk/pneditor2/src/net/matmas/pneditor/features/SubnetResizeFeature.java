package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.editor.Resizable;
import net.matmas.pnapi.editor.Resize;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.commands.ResizeElementCommand;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class SubnetResizeFeature extends Feature implements MouseListener, MouseMotionListener {

	public SubnetResizeFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}

	@Override
	public Cursor getCursor(MouseEvent e) {
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Arc() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			Point clickedPoint = getClickedPoint(e);
			Element clickedElement = getClickedElement(clickedPoint);
			if (clickedElement instanceof Resizable) {
				Resizable resizableElement = (Resizable)clickedElement;
				if (resizableElement.getResizeFrom(clickedPoint) == Resize.northWest) {
					return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				}
				else if (resizableElement.getResizeFrom(clickedPoint) == Resize.northEast) {
					return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				}
				else if (resizableElement.getResizeFrom(clickedPoint) == Resize.southWest) {
					return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				}
				else if (resizableElement.getResizeFrom(clickedPoint) == Resize.southEast) {
					return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				}
			}
		}
		return null;
	}

	@Override
	public void setElementColors(MouseEvent e, Map<Element, Color> elementColors) {
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Arc() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			Point clickedPoint = getClickedPoint(e);
			Element clickedElement = getClickedElement(clickedPoint);
			if (clickedElement instanceof Resizable) {
				Resizable resizableElement = (Resizable)clickedElement;
				if (resizableElement.getResizeFrom(clickedPoint) != null) {
					elementColors.put(clickedElement, Color.blue);
				}
			}
		}
	}

	private Resizable resizable;
	private Resize resize;
	private Point oldResizePoint;

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point clickedPoint = getClickedPoint(e);
			Element clickedElement = getClickedElement(clickedPoint);
			if (clickedElement instanceof Resizable) {
				this.resizable = (Resizable)clickedElement;
				this.resize = resizable.getResizeFrom(clickedPoint);
				this.oldResizePoint = resizable.getResizePoint(resize);
				if (resize != null) {
					canvas.getSelection().replaceWith(clickedElement);
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		if (resize != null) {
			resizable.setResizePoint(resize, clickedPoint);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		if (resize != null) {
			resizable.setResizePoint(resize, oldResizePoint);
			PNEditor.getInstance().getUndoManager().executeCommand(new ResizeElementCommand(resizable, resize, clickedPoint));
			resize = null;
		}
	}

	public void drawForeground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

}
