package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Subnet;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class PopupMenuFeature extends Feature implements MouseListener {

	public PopupMenuFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		MainFrame mainFrame = PNEditor.getInstance().getMainFrame();
		Selection selection = canvas.getSelection();

		int realX = e.getX();
		int realY = e.getY();
		
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (clickedElement != null &&
				!(PNEditor.getInstance().getToolSelector().isSelectedTool_Token() && clickedElement instanceof Place)
			) {
				if (clickedElement instanceof Place) {
					showPopup(mainFrame.placePopup, realX, realY);
					
					if (!selection.contains(clickedElement)) {
						selection.replaceWith(clickedElement);
					}
				}
				else if (clickedElement instanceof Transition) {
					showPopup(mainFrame.transitionPopup, realX, realY);

					if (!selection.contains(clickedElement)) {
						selection.replaceWith(clickedElement);
					}
				}
				else if (clickedElement instanceof Arc) {
					showPopup(mainFrame.arcPopup, realX, realY);

					if (!selection.contains(clickedElement)) {
						selection.replaceWith(clickedElement);
					}
				}
				else if (clickedElement instanceof Subnet) {
					showPopup(mainFrame.subnetPopup, realX, realY);

					if (!selection.contains(clickedElement)) {
						selection.replaceWith(clickedElement);
					}
				}
			}
			
			if (clickedElement == null &&
				PNEditor.getInstance().getToolSelector().isSelectedTool_Select()
			) {
				showPopup(mainFrame.canvasPopup, realX, realY);
			}
		}
	}
	
	private void showPopup(JPopupMenu popupMenu, int clickedX, int clickedY) {
		popupMenu.show(canvas, clickedX + 1, clickedY + 1);
	}
	
	public void drawForeground(Graphics g) {}
	public void drawBackground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
