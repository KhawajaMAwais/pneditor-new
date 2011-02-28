package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class SelectionDrawingFeature extends Feature implements MouseListener, MouseMotionListener {
	
	public SelectionDrawingFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}
	
	private boolean selecting = false;
	private VisualSelection visualSelection = new VisualSelection();
	private Set<Element> newSelection = new HashSet<Element>();
	
	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		int mouseButton = e.getButton();

		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select()) {
			if (mouseButton == MouseEvent.BUTTON1) {
				if (clickedElement == null) {
					selecting = true;
					visualSelection.setStart(clickedPoint);
					visualSelection.setEnd(clickedPoint);
					canvas.repaint();
					if (e.isShiftDown()) {
						newSelection.addAll(canvas.getSelection().getElements());
					}
					else {
						canvas.getSelection().clear();
						newSelection.clear();
					}
				}
                                if(clickedElement instanceof Place)
                                            {
                                            Place place = (Place) clickedElement;
                                             if(place.getIsORSplit()){
                                                 canvas.getSelection().add(place.getORJoin());
                                                 canvas.repaint();
                                                }
                                            }
                                if(clickedElement instanceof Place)
                                            {
                                            Place place = (Place) clickedElement;
                                             if(place.getIsIterationStart()){
                                                 canvas.getSelection().add(place.getIterationEnd());
                                                 canvas.repaint();
                                                }
                                            }
                                if(clickedElement instanceof Transition)
                                            {
                                            Transition tran = (Transition) clickedElement;
                                             if(tran.getIsANDSplit()){
                                                 canvas.getSelection().add(tran.getANDJoin());
                                                 canvas.repaint();
                                                }
                                            }
			}
			if (mouseButton == MouseEvent.BUTTON3) {
				if (clickedElement == null) {
					canvas.getSelection().clear();
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (selecting) {
			visualSelection.setEnd(getClickedPoint(e));

			canvas.getSelection().clear();
			canvas.getSelection().addAll(newSelection);
			for (Element element : PNEditor.getInstance().getDocument().getPetriNet()) {
				if (visualSelection.getBounds().contains(element.getBounds())) {
					canvas.getSelection().add(element);
				}
			}
			canvas.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (selecting) {
			selecting = false;
			canvas.repaint();
		}
	}

	public void drawForeground(Graphics g) {
		if (selecting) {
			visualSelection.draw(g, null);
		}
	}

	public void drawBackground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
}