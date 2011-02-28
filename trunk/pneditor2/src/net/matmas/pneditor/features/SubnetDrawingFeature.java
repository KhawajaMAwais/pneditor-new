package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Subnet;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.commands.AddSubnetCommand;
import net.matmas.util.CollectionTools;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class SubnetDrawingFeature extends Feature implements MouseListener, MouseMotionListener {

	public SubnetDrawingFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}

	private Subnet subnet;
	private boolean started;

	public void mousePressed(MouseEvent e) {
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);

		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (clickedElement == null) {
					subnet = new Subnet();
					subnet.setStart(clickedPoint);
					subnet.setEnd(clickedPoint);
					started = true;
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				PNEditor.getInstance().getToolSelector().selectTool_Select();
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		
		if (started) {
			subnet.setEnd(clickedPoint);
			canvas.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (started) {
			started = false;
			PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
			if (subnet.getWidth() > 10 && subnet.getHeight() > 10) {
				PNEditor.getInstance().getUndoManager().executeCommand(new AddSubnetCommand(subnet, petriNet));
				canvas.getSelection().replaceWith(CollectionTools.getLastElement(PNEditor.getInstance().getDocument().getPetriNet().getSubnets()));
			}
			canvas.repaint();
		}
	}

	public void drawBackground(Graphics g) {
		if (started) {
			subnet.draw(g, new DrawingOptions());
		}
	}
	
	public void drawForeground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
}
