package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import net.matmas.pneditor.commands.MoveElementsCommand;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Label;
import net.matmas.pnapi.editor.Movable;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.Subnet;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class DraggingFeature extends Feature implements MouseListener, MouseMotionListener {
	
	public DraggingFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}
	
	private Element draggedElement = null;
	private Point deltaPosition;
	private Point prevDrag;	// During dragging, this records the x and y coordinates of the
							//   previous position of the mouse.
	
	
	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		int mouseButton = e.getButton();
		boolean doubleclick = e.getClickCount() == 2;

		if (
			PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			if (mouseButton == MouseEvent.BUTTON1 && !doubleclick) {
				if (clickedElement instanceof Movable) {
					Movable movable = (Movable)clickedElement;
					if (movable.canBeMovedFrom(clickedPoint)) {
						if (!canvas.getSelection().contains(clickedElement)) {
							canvas.getSelection().replaceWith(clickedElement);
							if (clickedElement instanceof Subnet) {
								Subnet subnet = (Subnet)clickedElement;
								canvas.getSelection().addAll(subnet.getNodesRecursively());
								canvas.getSelection().addAll(subnet.getChildSubnetsRecursively());
							}
						}
						draggedElement = getClickedElement(clickedPoint);
						deltaPosition = new Point();
						prevDrag = clickedPoint;
					}
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (draggedElement != null) {
			Point clickedPoint = getClickedPoint(e);
			doTheMoving(clickedPoint);
			canvas.repaint();  // redraw canvas to show shape in new position
			deltaPosition = deltaPosition.getTranslated(clickedPoint.getX() - prevDrag.getX(), clickedPoint.getY() - prevDrag.getY());
			prevDrag = clickedPoint;
		}
	}

	public void mouseReleased(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		if (draggedElement != null) {
			doTheMoving(clickedPoint);
			deltaPosition = deltaPosition.getTranslated(clickedPoint.getX() - prevDrag.getX(), clickedPoint.getY() - prevDrag.getY());
			saveTheMoving();
			canvas.repaint();
			draggedElement = null;  // Dragging is finished.
		}
	}

	private void doTheMoving(Point mouse) {
		if (!canvas.getSelection().isEmpty()) {
			for (Element selectedElement : canvas.getSelection()) {
				selectedElement.moveBy(mouse.getX() - prevDrag.getX(), mouse.getY() - prevDrag.getY());
			}
		}
		else {
			draggedElement.moveBy(mouse.getX() - prevDrag.getX(), mouse.getY() - prevDrag.getY());
		}
	}
	
	private void saveTheMoving() {
		if (!deltaPosition.equals(new Point(0, 0))) {
			if (!canvas.getSelection().isEmpty()) {
				for (Element selectedElement : canvas.getSelection()) {
					selectedElement.moveBy(-deltaPosition.getX(), -deltaPosition.getY()); //move back to original positions
				}

				PNEditor.getInstance().getUndoManager().executeCommand(new MoveElementsCommand(canvas.getSelection().getElements(), deltaPosition));
			}
		}
	}

	public void mouseMoved(MouseEvent e) {}

	@Override
	public Cursor getCursor(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			if (clickedElement instanceof Movable) {
				Movable movable = (Movable)clickedElement;
				if (movable.canBeMovedFrom(clickedPoint)) {
					return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
				}
			}
		}
		return null;
	}

	@Override
	public void setElementColors(MouseEvent e, Map<Element, Color> elementColors) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);

		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			if (clickedElement instanceof Movable) {
				Movable movable = (Movable)clickedElement;
				if (movable.canBeMovedFrom(clickedPoint)) {
					elementColors.put(clickedElement, Color.blue);
				}
			}
		}
	}

	public void drawForeground(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void setHoverEffects(int x, int y) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseMoved(int x, int y) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
