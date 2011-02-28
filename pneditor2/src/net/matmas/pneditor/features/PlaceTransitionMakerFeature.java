package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.commands.AddPlaceCommand;
import net.matmas.pneditor.commands.AddTransitionCommand;
import net.matmas.util.CollectionTools;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class PlaceTransitionMakerFeature extends Feature implements MouseListener {

	public PlaceTransitionMakerFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Place()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (getClickedElement(clickedPoint) == null) {
					PNEditor.getInstance().getUndoManager().executeCommand(new AddPlaceCommand(clickedPoint, PNEditor.getInstance().getDocument().getPetriNet()));
					canvas.getSelection().replaceWith(CollectionTools.getLastElement(PNEditor.getInstance().getDocument().getPetriNet().getPlaces()));
					canvas.refresh(e);
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				PNEditor.getInstance().getToolSelector().selectTool_Select();
			}
		}
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Transition()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (getClickedElement(clickedPoint) == null) {
					PNEditor.getInstance().getUndoManager().executeCommand(new AddTransitionCommand(clickedPoint, PNEditor.getInstance().getDocument().getPetriNet()));
					canvas.getSelection().replaceWith(CollectionTools.getLastElement(PNEditor.getInstance().getDocument().getPetriNet().getTransitions()));
					canvas.refresh(e);
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				PNEditor.getInstance().getToolSelector().selectTool_Select();
			}
		}
	}
	
	public void drawForeground(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
