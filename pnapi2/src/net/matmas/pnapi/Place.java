package net.matmas.pnapi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.properties.BooleanProperty;
import net.matmas.pnapi.properties.Properties;
import net.matmas.util.GraphicsTools;
import net.matmas.util.GraphicsTools.HorizontalAlignment;
import net.matmas.util.GraphicsTools.VerticalAlignment;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class Place extends Node implements Cloneable {

	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		Color color = drawingOptions.getElementColors().get(this);
		if (color == null) {
			color = Color.black;
		}
		drawPlaceBackground(g);
		drawPlaceBorder(g, color);
		getLabel().draw(g, drawingOptions);
		if (drawingOptions.getMarking() != null) {
			drawTokens(g, drawingOptions.getMarking(), color);
		}
	}

	private void drawTokens(Graphics g, Marking marking, Color color) {
		g.setColor(color);
		Point center = getCenter();
		int x = center.getX();
		int y = center.getY();
		int tokenSpacing = getWidth() / 5;
		if (marking.getTokens(this) == 1) {
			drawTokenAsDot(g, x, y);
		}
		else if (marking.getTokens(this) == 2) {
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
		}
		else if (marking.getTokens(this) == 3) {
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x, y);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
		}
		else if (marking.getTokens(this) == 4) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) == 5) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x, y);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) == 6) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) == 7) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x, y);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) == 8) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x, y - tokenSpacing);
			drawTokenAsDot(g, x, y + tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) == 9) {
			drawTokenAsDot(g, x - tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x - tokenSpacing, y);
			drawTokenAsDot(g, x - tokenSpacing, y + tokenSpacing);
			drawTokenAsDot(g, x, y - tokenSpacing);
			drawTokenAsDot(g, x, y);
			drawTokenAsDot(g, x, y + tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y - tokenSpacing);
			drawTokenAsDot(g, x + tokenSpacing, y);
			drawTokenAsDot(g, x + tokenSpacing, y + tokenSpacing);
		}
		else if (marking.getTokens(this) > 9) {
			GraphicsTools.drawString(g, Integer.toString(marking.getTokens(this)), center, HorizontalAlignment.center, VerticalAlignment.center);
		}
	}

	private void drawTokenAsDot(Graphics g, int x, int y) {
		final int tokenSize = getWidth() / 6;
		g.fillOval(x - tokenSize / 2, y - tokenSize / 2, tokenSize, tokenSize);
	}

	private void drawPlaceBackground(Graphics g) {
		final Color lightYellow = new Color(255, 246, 213);
		g.setColor(lightYellow);
		g.fillOval(getStart().getX(), getStart().getY(), getWidth(), getHeight());
	}

	private void drawPlaceBorder(Graphics g, Color color) {
		g.setColor(color);
		g.drawOval(getStart().getX(), getStart().getY(), getWidth()-1, getHeight()-1);
	}

	@Override
	public boolean containsPoint(Point point) {
		// Check whether (x,y) is inside this oval, using the
		// mathematical equation of an ellipse.
		double rx = getWidth() / 2.0;   // horizontal radius of ellipse
		double ry = getHeight() / 2.0;  // vertical radius of ellipse
		double cx = getStart().getX() + rx;   // x-coord of center of ellipse
		double cy = getStart().getY() + ry;    // y-coord of center of ellipse
		if ( (ry*(point.getX()-cx))*(ry*(point.getX()-cx)) + (rx*(point.getY()-cy))*(rx*(point.getY()-cy)) <= rx*rx*ry*ry )
			return true;
		else
			return false;
	}

        // -----------------------------------------------Workflow anal...

	private Boolean IsIterationStart=false;

	public Boolean getIsIterationStart() {
		return IsIterationStart;
	}

	public void setIsIterationStart(Boolean IsIterationStart) {
		this.IsIterationStart = IsIterationStart;
	}

        // -----------------------------------------------Workflow anal...

	private Place IterationEnd;

	public Place getIterationEnd() {
		return IterationEnd;
	}

	public void setIterationEnd(Place IterationEnd) {
		this.IterationEnd = IterationEnd;
	}

        // -----------------------------------------------Workflow anal...

	private Place ORJoin;

	public Place getORJoin() {
		return ORJoin;
	}

	public void setORJoin(Place ORJoin) {
		this.ORJoin = ORJoin;
	}

        // -----------------------------------------------Workflow anal...

	private Boolean IsORSplit=false;

	public Boolean getIsORSplit() {
		return IsORSplit;
	}

	public void setIsORSplit(Boolean IsORSplit) {
		this.IsORSplit = IsORSplit;
	}

        // -----------------------------------------------Workflow anal...

	private Boolean IsStartPlace=false;

	public Boolean getIsStartPlace() {
		return IsStartPlace;
	}

	public void setIsStartPlace(Boolean IsStartPlace) {
		this.IsStartPlace = IsStartPlace;
	}

	// -----------------------------------------------Workflow anal...

	private Boolean IsEndPlace=false;

	public Boolean getIsEndPlace() {
		return IsEndPlace;
	}

	public void setIsEndPlace(Boolean IsEndPlace) {
		this.IsEndPlace = IsEndPlace;
	}

        // -----------------------------------------------Workflow anal...
	
	private double ArrivalRate;

	public double getArrivalRate() {
		return ArrivalRate;
	}

	public void setArrivalRate(double ArrivalRate) {
		this.ArrivalRate = ArrivalRate;
	}

        // -----------------------------------------------Workflow anal...

	private double Zmena;

	public double getZmena() {
		return Zmena;
	}

	public void setZmena(double Zmena) {
		this.Zmena = Zmena;
	}

        // -----------------------------------------------Workflow anal...

	private String ORJoinId;

	public String getORJoinId() {
		return ORJoinId;
	}

	public void setORJoinId(String ORJoinId) {
		this.ORJoinId = ORJoinId;
	}

        // -----------------------------------------------Workflow anal...

	private String IterJoinId;

	public String getIterJoinId() {
		return IterJoinId;
	}

	public void setIterJoinId(String IterJoinId) {
		this.IterJoinId = IterJoinId;
        }
        // -------------------------------------------------------------------------

	@Override
	public Place getClone() {
		Place place = (Place)super.getClone();
		return place;
	}

	public boolean canBeMovedFrom(Point point) {
		return containsPoint(point);
	}

        public boolean isStatic(){
           return this.getProperties().containsPropertyWithId("static");
        }
        
        public Set<Transition> getConnectedTransitionNodes() {
		Set<Transition> connectedTransitionNodes = new HashSet<Transition>();
		for (Arc arcEdge : getConnectedArcEdges()) {
			connectedTransitionNodes.add(arcEdge.getTransition());
		}
		return connectedTransitionNodes;
	}
        
        public Set<Arc> getConnectedArcEdges() {
		Set<Arc> connectedArcEdges = new HashSet<Arc>();
		for (Arc arcEdge : getPetriNet().getArcs()) {
			if (arcEdge.getSource() == this || arcEdge.getDestination() == this) {
				connectedArcEdges.add(arcEdge);
			}
		}
		return connectedArcEdges;
	}

}
