package net.matmas.pnapi.editor;

import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public interface Resizable {

	public Resize getResizeFrom(Point point);

	public Point getTopLeft();
	public void setTopLeft(Point point);

	public Point getTopRight();
	public void setTopRight(Point point);

	public Point getBottomLeft();
	public void setBottomLeft(Point point);

	public Point getBottomRight();
	public void setBottomRight(Point point);

	public Point getResizePoint(Resize resize);
	public void setResizePoint(Resize resize, Point point);
}
