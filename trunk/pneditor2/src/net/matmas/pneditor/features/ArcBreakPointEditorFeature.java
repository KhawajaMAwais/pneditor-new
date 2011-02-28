package net.matmas.pneditor.features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.commands.SetArcBreakPointCommand;
import net.matmas.util.GraphicsTools;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class ArcBreakPointEditorFeature extends Feature implements MouseListener, MouseMotionListener {
	
	public ArcBreakPointEditorFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		visualHandle.setSize(Arc.nearTolerance*2, Arc.nearTolerance*2);
	}
	
	private Arc arc;
	private int activeBreakPointIndex;
	private boolean started = false;
	private VisualHandle visualHandle = new VisualHandle();
	private List<Element> foregroundVisualElements = new ArrayList<Element>();
	
	private Point startingMouseLocation;
	private List<Point> oldBreakPoints;
	
	public void mousePressed(MouseEvent e) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);
		int mouseButton = e.getButton();
		
		if (mouseButton == MouseEvent.BUTTON1 &&
			clickedElement != null &&
			(
				PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
				PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
				PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
				PNEditor.getInstance().getToolSelector().isSelectedTool_Arc() ||
				PNEditor.getInstance().getToolSelector().isSelectedTool_Token() ||
				PNEditor.getInstance().getToolSelector().isSelectedTool_Fire()
			) &&
			clickedElement instanceof Arc
		) {
			arc = (Arc)clickedElement;
			canvas.getSelection().replaceWith(arc);
			oldBreakPoints = new LinkedList<Point>(arc.getBreakPoints());
			startingMouseLocation = clickedPoint;
			activeBreakPointIndex = arc.addOrGetBreakPoint(startingMouseLocation);
			started = true;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (started) {
			Point clickedPoint = getClickedPoint(e);
			arc.setBreakPoint(activeBreakPointIndex, clickedPoint);
			canvas.repaint();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (started) {
			arc.cleanupUnecessaryBreakPoints();
			
			boolean change = false;
			if (oldBreakPoints.size() != arc.getBreakPoints().size()) {
				change = true;
			}
			else {
				for (int i = 0; i < arc.getBreakPoints().size(); i++) {
					if ( !arc.getBreakPoints().get(i).equals(oldBreakPoints.get(i))) {
						change = true;
						break;
					}
				}
			}
			if (change) {
				arc.setBreakPoints(oldBreakPoints);
				Point targetLocation = getClickedPoint(e);
				PNEditor.getInstance().getUndoManager().executeCommand(new SetArcBreakPointCommand(arc, startingMouseLocation, targetLocation));
			}
			started = false;
		}
	}

	@Override
	public void setElementColors(MouseEvent e, Map<Element, Color> elementColors) {
		Point clickedPoint = getClickedPoint(e);
		Element clickedElement = getClickedElement(clickedPoint);

		if (PNEditor.getInstance().getToolSelector().isSelectedTool_Select() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Place() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Transition() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Arc() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Token() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Fire() ||
			PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet()
		) {
			boolean drawHandle = false;
			if (clickedElement instanceof Arc) {
				Arc anArc = (Arc)clickedElement;
				for (Point breakPoint : anArc.getBreakPoints()) {
					if (GraphicsTools.isPointNearPoint(breakPoint, clickedPoint, Arc.nearTolerance)) {
						if (!foregroundVisualElements.contains(visualHandle)) {
							foregroundVisualElements.add(visualHandle);
						}
						visualHandle.setCenter(breakPoint);
						canvas.repaint();
						drawHandle = true;
						break;
					}
				}
			}
			if (!drawHandle) {
				foregroundVisualElements.remove(visualHandle);
				canvas.repaint();
			}
			
			if (clickedElement instanceof Arc) {
				elementColors.put(clickedElement, Color.blue);
			}
		}
	}
	
	public void drawForeground(Graphics g) {
		for (Element element : foregroundVisualElements) {
			element.draw(g, null);
		}
	}
	
	public void drawBackground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
