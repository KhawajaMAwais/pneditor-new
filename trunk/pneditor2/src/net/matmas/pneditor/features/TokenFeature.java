package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import net.matmas.pnapi.Element;
import net.matmas.pneditor.commands.AddTokenCommand;
import net.matmas.pneditor.commands.RemoveTokenCommand;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Place;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class TokenFeature extends Feature implements MouseListener {
	
	public TokenFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		Marking marking = PNEditor.getInstance().getDocument().getPetriNet().getMarking();
		
		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Token()) {
			if (clickedElement instanceof Place) {
				Place place = (Place)clickedElement;

				if (e.getButton() == MouseEvent.BUTTON1) {
					PNEditor.getInstance().getUndoManager().executeCommand(new AddTokenCommand(place, marking));
				}
				else if (e.getButton() == MouseEvent.BUTTON3) {
					if (marking.getTokens(place) > 0) {
						PNEditor.getInstance().getUndoManager().executeCommand(new RemoveTokenCommand(place, marking));
					}
				}
			}

			if (clickedElement == null) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					PNEditor.getInstance().getToolSelector().selectTool_Select();
				}
			}
		}
	}

	@Override
	public void setElementColors(MouseEvent e, Map<Element, Color> elementColors) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);

		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Token()) {
			if (clickedElement instanceof Place) {
				elementColors.put(clickedElement, Color.blue);
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
