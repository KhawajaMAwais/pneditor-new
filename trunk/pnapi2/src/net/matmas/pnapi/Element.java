package net.matmas.pnapi;

import net.matmas.util.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import net.matmas.pnapi.editor.Resize;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pnapi.properties.Properties;

/**
 *
 * @author matmas
 */
public abstract class Element implements WithProperties, Cloneable {

	private PetriNet petriNet;

	public PetriNet getPetriNet() {
		return petriNet;
	}

	public void setPetriNet(PetriNet petriNet) {
		this.petriNet = petriNet;
	}
	
	// -------------------------------------------------------------------------

	private Point start = new Point();

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	// -------------------------------------------------------------------------

	private Point end = new Point();

	public Point getEnd() {
		return end;
	}
	
	public void setEnd(Point end) {
		this.end = end;
	}

	// -------------------------------------------------------------------------

	public int getLeft() {
		return Math.min(getStart().getX(), getEnd().getX());
	}

	public void setLeft(int left) {
		if (getStart().getX() < getEnd().getX()) {
			setStart(new Point(left, getStart().getY()));
		}
		else {
			setEnd(new Point(left, getEnd().getY()));
		}
	}

	public int getTop() {
		return Math.min(getStart().getY(), getEnd().getY());
	}

	public void setTop(int top) {
		if (getStart().getY() < getEnd().getY()) {
			setStart(new Point(getStart().getX(), top));
		}
		else {
			setEnd(new Point(getEnd().getX(), top));
		}
	}

	public int getRight() {
		return Math.max(getStart().getX(), getEnd().getX());
	}

	public void setRight(int right) {
		if (getStart().getX() > getEnd().getX()) {
			setStart(new Point(right, getStart().getY()));
		}
		else {
			setEnd(new Point(right, getEnd().getY()));
		}
	}

	public int getBottom() {
		return Math.max(getStart().getY(), getEnd().getY());
	}

	public void setBottom(int bottom) {
		if (getStart().getY() > getEnd().getY()) {
			setStart(new Point(getStart().getX(), bottom));
		}
		else {
			setEnd(new Point(getEnd().getX(), bottom));
		}
	}

	// -------------------------------------------------------------------------

	public Point getTopLeft() {
		return new Point(getLeft(), getTop());
	}

	public void setTopLeft(Point point) {
		setTop(point.getY());
		setLeft(point.getX());
	}

	public Point getTopRight() {
		return new Point(getRight(), getTop());
	}

	public void setTopRight(Point point) {
		setTop(point.getY());
		setRight(point.getX());
	}

	public Point getBottomLeft() {
		return new Point(getLeft(), getBottom());
	}

	public void setBottomLeft(Point point) {
		setBottom(point.getY());
		setLeft(point.getX());
	}

	public Point getBottomRight() {
		return new Point(getRight(), getBottom());
	}

	public void setBottomRight(Point point) {
		setBottom(point.getY());
		setRight(point.getX());
	}

	// -------------------------------------------------------------------------

	public Point getResizePoint(Resize resize) {
		if (resize == Resize.northWest) {
			return getTopLeft();
		}
		if (resize == Resize.northEast) {
			return getTopRight();
		}
		if (resize == Resize.southWest) {
			return getBottomLeft();
		}
		if (resize == Resize.southEast) {
			return getBottomRight();
		}
		return null;
	}

	public void setResizePoint(Resize resize, Point point) {
		if (resize == Resize.northWest) {
			this.setTopLeft(point);
		}
		if (resize == Resize.northEast) {
			this.setTopRight(point);
		}
		if (resize == Resize.southWest) {
			this.setBottomLeft(point);
		}
		if (resize == Resize.southEast) {
			this.setBottomRight(point);
		}
	}

	// -------------------------------------------------------------------------
	
	public Point getCenter() {
		return new Point(
				getStart().getX() + (getEnd().getX() - getStart().getX()) / 2,
				getStart().getY() + (getEnd().getY() - getStart().getY()) / 2
				);
	}

	public void setCenter(Point center) {
		moveBy(center.getX() - getCenter().getX(), center.getY() - getCenter().getY());
	}

	// -------------------------------------------------------------------------

	public int getWidth() {
		return Math.abs(getEnd().getX() - getStart().getX());
	}

	public int getHeight() {
		return Math.abs(getEnd().getY() - getStart().getY());
	}

	public Point getSize() {
		return new Point(getWidth(), getHeight());
	}

	public void setSize(Point point) {
		setSize(point.getX(), point.getY());
	}

	public void setSize(int width, int height) {
		Point prevCenter = getCenter();
		setEnd(new Point(getStart().getX() + width, getStart().getY() + height));
		setCenter(prevCenter);
	}

	// -------------------------------------------------------------------------

	public boolean containsPoint(Point point) {
		int l = Math.min(getStart().getX(), getEnd().getX());
		int t = Math.min(getStart().getY(), getEnd().getY());
		int w = Math.abs(getWidth());
		int h = Math.abs(getHeight());
		if (point.getX() >= l && point.getX() < l + w && point.getY() >= t && point.getY() < t + h)
			return true;
		else
			return false;
	}

	public Rectangle getBounds() {
		int x = Math.min(getStart().getX(), getEnd().getX());
		int y = Math.min(getStart().getY(), getEnd().getY());
		int width = getWidth();
		int height = getHeight();
		Rectangle bounds = new Rectangle(x, y, width, height);
		return bounds;
	}

	public void moveBy(int dx, int dy) {
		start = new Point(getStart().getX() + dx, getStart().getY() + dy);
		end = new Point(getEnd().getX() + dx, getEnd().getY() + dy);
	}

	public void moveBy(Point point) {
		moveBy(point.getX(), point.getY());
	}

	// -------------------------------------------------------------------------

	abstract public void draw(Graphics g, DrawingOptions drawingOptions);

	// -------------------------------------------------------------------------

	public Element getClone() {
		try {
			Element element = (Element)this.clone();
			element.start = this.start;
			element.end = this.end;
			element.petriNet = this.petriNet;
			return element;
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException();
		}
	}

	// -------------------------------------------------------------------------

	private Properties properties = new Properties();

	public Properties getProperties() {
		return properties;
	}

	// -------------------------------------------------------------------------

}
