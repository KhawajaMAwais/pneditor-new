package net.matmas.pnapi;

import net.matmas.util.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.matmas.util.GraphicsTools;
import net.matmas.util.GraphicsTools.HorizontalAlignment;
import net.matmas.util.GraphicsTools.VerticalAlignment;

/**
 *
 * @author matmas
 */
public class Arc extends Element implements Cloneable {

	public Arc(Place place, Transition transition, boolean placeToTransition) {
		this.place = place;
		this.transition = transition;
		this.placeToTransition = placeToTransition;
	}

	public Arc() {
	}

	// -------------------------------------------------------------------------

	private Place place;

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	// -----------------------------------------------Workflow anal...

	private Boolean IsIterationArc=false;

	public Boolean getIsIterationArc() {
		return IsIterationArc;
	}

	public void setIsIterationArc(Boolean IsIterationArc) {
		this.IsIterationArc = IsIterationArc;
	}


        // -------------------------------------------------------------------------

	private Transition transition;

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	// -------------------------------------------------------------------------

	private boolean placeToTransition;

	public boolean isPlaceToTransition() {
		return placeToTransition;
	}

	public void setPlaceToTransition(boolean placeToTransition) {
		this.placeToTransition = placeToTransition;
	}


        // -------------------------------------------------------------------------

	private boolean Zmena = false;

	public boolean getZmena() {
		return placeToTransition;
	}

	public void setZmena(boolean Zmena) {
		this.Zmena =Zmena;
	}

        // -------------------------------------------------------------------------

	private double Probability = 1;

	public double  getProbability() {
		return Probability;
	}

	public void setProbability(double Probability) {
		this.Probability = Probability;
	}

         // -------------------------------------------------------------------------

	private double ChangedProbability = 1;

	public double  getChangedProbability() {
		return Probability;
	}

	public void setChangedProbability(double ChangedProbability) {
		this.ChangedProbability = ChangedProbability;
	}

        // -------------------------------------------------------------------------

	private double ArcTime = 0;

	public double  getArcTime() {
		return ArcTime;
	}

	public void setArcTime(double ArcTime) {
		this.ArcTime = ArcTime;
	}


        // -------------------------------------------------------------------------

	private double ArcWaitTime = 0;

	public double  getArcWaitTime() {
		return ArcWaitTime;
	}

	public void setArcWaitTime(double ArcWaitTime) {
		this.ArcWaitTime = ArcWaitTime;
	}


	// -------------------------------------------------------------------------

	private int multiplicity = 1;

	public int getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}
	
	// -------------------------------------------------------------------------

	private List<Point> breakPoints = new ArrayList<Point>();

	public List<Point> getBreakPoints() {
		return Collections.unmodifiableList(breakPoints);
	}

	public void setBreakPoints(List<Point> breakPoints) {
		this.breakPoints = breakPoints;
	}
        
        public void removeBreakPoints() {
		breakPoints.clear();
	}

	// -------------------------------------------------------------------------

	public Node getSource() {
		return isPlaceToTransition() ? place : transition;
	}

	public Node getDestination() {
		return isPlaceToTransition() ? transition : place;
	}

	// -------------------------------------------------------------------------

	public void setSource(Node source) {
		if (source instanceof Place) {
			this.place = (Place)source;
			this.placeToTransition = true;
		}
		else if (source instanceof Transition) {
			this.transition = (Transition)source;
			this.placeToTransition = false;
		}
		else if (source == null) {
			if (placeToTransition) {
				place = null;
			}
			else {
				transition = null;
			}
		}
	}

	public void setDestination(Node destination) {
		if (destination instanceof Place) {
			this.place = (Place)destination;
			this.placeToTransition = false;
		}
		else if (destination instanceof Transition) {
			this.transition = (Transition)destination;
			this.placeToTransition = true;
		}
		else if (destination == null) {
			if (placeToTransition) {
				transition = null;
			}
			else {
				place = null;
			}
		}
	}

	// Overrides: --------------------------------------------------------------
	
	@Override
	public Point getStart() {
		return getSource() != null ? getSource().getCenter() : super.getStart();
	}

	@Override
	public Point getEnd() {
		return getDestination() != null ? getDestination().getCenter() : super.getEnd();
	}

	@Override
	public void moveBy(int dx, int dy) {
		super.moveBy(dx, dy);
		for (int i = 0; i < breakPoints.size(); i++) {
			breakPoints.set(i, breakPoints.get(i).getTranslated(dx, dy));
		}
	}

	public static final int nearTolerance = 5;

	@Override
	public boolean containsPoint(Point point) {
		Point previous = getStart();
		for (Point breakPoint : breakPoints) {
			if (GraphicsTools.isPointNearSegment(previous, breakPoint, point, nearTolerance)) {
				return true;
			}
			previous = breakPoint;
		}
		return GraphicsTools.isPointNearSegment(previous, getEnd(), point, nearTolerance);
	}

	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		Color color = drawingOptions.getElementColors().get(this);
		if (color == null) {
			color = Color.black;
		}
		g.setColor(color);
		drawSegmentedLine(g);
		Point arrowTip = computeArrowTipPoint();
		drawArrow(g, arrowTip);
  ///// work flow
                if (getProbability() < 1)
                {
                    drawProbabilityLabel(g, arrowTip, Probability);
                }
  ///// work flow
		if (multiplicity >= 2) {
			drawMultiplicityLabel(g, arrowTip, multiplicity);
		}
	}

	// Public methods: ---------------------------------------------------------

	public void cleanupUnecessaryBreakPoints() {
		Point previous = getStart();
		for (int i = 0; i < breakPoints.size(); i++) {
			Point current = breakPoints.get(i);
			Point next = i < (breakPoints.size() - 1) ? breakPoints.get(i + 1) : getEnd();
			final int tolerance = Math.round(0.1f * (float)previous.distance(next));
			if (GraphicsTools.isPointNearSegment(previous, next, current, tolerance)) {
				breakPoints.remove(i--);
			}
			else {
				previous = breakPoints.get(i);
			}
		}
	}

	public int addOrGetBreakPoint(Point newPoint) {
		for (int i = 0; i < breakPoints.size(); i++) {
			if (GraphicsTools.isPointNearPoint(newPoint, breakPoints.get(i), nearTolerance)) {
				return i;
			}
		}

		if (breakPoints.isEmpty()) {
			breakPoints.add(newPoint);
		}
		else {
			Point previous = getStart();
			for (int i = 0; i < breakPoints.size(); i++) {
				if (GraphicsTools.isPointNearSegment(previous, breakPoints.get(i), newPoint, nearTolerance)) {
					breakPoints.add(i, newPoint);
					return i;
				}
				previous = breakPoints.get(i);
			}
			if (GraphicsTools.isPointNearSegment(previous, getEnd(), newPoint, nearTolerance)) {
				breakPoints.add(newPoint);
			}
		}
		return breakPoints.size() - 1;
	}

	public void setBreakPoint(int i, Point breakPoint) {
		breakPoints.set(i, breakPoint);
	}

	public void addDistantBreakPointToEnd(Point newPoint) {
		breakPoints.add(newPoint);
	}

	public void addDistantBreakPointToBeginning(Point newPoint) {
		breakPoints.add(0, newPoint);
	}

	// Private methods: --------------------------------------------------------

	private void drawSegmentedLine(Graphics g) {
		Point previous = getStart();
		for (Point breakPoint : breakPoints) {
			g.drawLine(previous.getX(), previous.getY(), breakPoint.getX(), breakPoint.getY());
			previous = breakPoint;
		}
		g.drawLine(previous.getX(), previous.getY(), getEnd().getX(), getEnd().getY());
	}

	private void drawArrow(Graphics g, Point arrowTip) {
		Point lastBreakPoint = getLastBreakPoint();
		GraphicsTools.drawArrow(g, lastBreakPoint.getX(), lastBreakPoint.getY(), arrowTip.getX(), arrowTip.getY());
	}

	private void drawMultiplicityLabel(Graphics g, Point arrowTip, int multiplicity) {
		Point labelPoint = getLabelPoint(arrowTip);
		GraphicsTools.drawString(g, Integer.toString(multiplicity), labelPoint, HorizontalAlignment.center, VerticalAlignment.bottom);

	}
 // work flow
        private void drawProbabilityLabel(Graphics g, Point arrowTip, double probability) {
		Point labelPoint = getLabelPoint(arrowTip);
                String str = "("+Double.toString(probability)+")";
		GraphicsTools.drawString(g,str, labelPoint, HorizontalAlignment.center, VerticalAlignment.top);

	}
/////
	private Point getLabelPoint(Point arrowTip) {
		Point lastBreakPoint = getLastBreakPoint();
		return new Point(
			lastBreakPoint.getX() + (arrowTip.getX() - lastBreakPoint.getX()) * 1 / 2,
			lastBreakPoint.getY() + (arrowTip.getY() - lastBreakPoint.getY()) * 1 / 2 - 3
			);
	}

	private Point getLastBreakPoint() {
		Point last = getStart();
		for (Point breakPoint : breakPoints) {
			last = breakPoint;
		}
		return last;
	}

	private Point computeArrowTipPoint() {
		Point arrowTip = getEnd();
		if (getDestination() == null) {
			return arrowTip;
		}
		else { //Thanks to http://www.cs.unc.edu/~mcmillan/comp136/Lecture6/Lines.html
			int x0 = getLastBreakPoint().getX();
			int y0 = getLastBreakPoint().getY();
			int x1 = getEnd().getX();
			int y1 = getEnd().getY();

			int dy = y1 - y0;
			int dx = x1 - x0;
			int stepx, stepy;

			if (dy < 0) { dy = -dy;  stepy = -1; } else { stepy = 1; }
			if (dx < 0) { dx = -dx;  stepx = -1; } else { stepx = 1; }
			dy <<= 1;
			dx <<= 1;

			if (dx > dy) {
				int fraction = dy - (dx >> 1);
				while (x0 != x1) {
					if (fraction >= 0) {
						y0 += stepy;
						fraction -= dx;
					}
					x0 += stepx;
					fraction += dy;
					if (getDestination().containsPoint(new Point(x0, y0))) {
						return arrowTip;
					}
					arrowTip = new Point(x0, y0);
				}
			} else {
				int fraction = dx - (dy >> 1);
				while (y0 != y1) {
					if (fraction >= 0) {
						x0 += stepx;
						fraction -= dy;
					}
					y0 += stepy;
					fraction += dx;
					if (getDestination().containsPoint(new Point(x0, y0))) {
						return arrowTip;
					}
					arrowTip = new Point(x0, y0);
				}
			}
		}
		return arrowTip;
	}

	// -------------------------------------------------------------------------

	@Override
	public Arc getClone() {
		Arc arc = (Arc)super.getClone();
		arc.place = this.place;
		arc.transition = this.transition;
		arc.placeToTransition = this.placeToTransition;
		arc.multiplicity = this.multiplicity;
		arc.breakPoints = new ArrayList<Point>(this.breakPoints);
		return arc;
	}

}
