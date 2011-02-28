package net.matmas.pnapi;

import net.matmas.pnapi.editor.Movable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import net.matmas.util.CachedGraphics2D;
import net.matmas.util.GraphicsTools;
import net.matmas.util.GraphicsTools.HorizontalAlignment;
import net.matmas.util.GraphicsTools.VerticalAlignment;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class Label extends Element implements Movable {

	private Node node;

	public Label(Node node) {
		this.node = node;
		this.setCenter(new Point(0, node.getHeight() / 2 + 8));
	}

	public void setNode(Node node) {
		this.node = node;
	}

	private String text = null;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		CachedGraphics2D cachedGraphics = new CachedGraphics2D();
		this.draw(cachedGraphics, new DrawingOptions());
		Rectangle bounds = cachedGraphics.getIntegerBounds();
		setSize(bounds.width, bounds.height);
	}
	
	@Override
	public void draw(Graphics g, DrawingOptions drawingOptions) {
		Color labelColor = drawingOptions.getElementColors().get(this);
		Color nodeColor = drawingOptions.getElementColors().get(node);
		Color color = Color.black;
		if (labelColor != null) {
			color = labelColor;
		}
		else if (nodeColor != null) {
			color = nodeColor;
		}
		g.setColor(color);
		if (text != null && !text.equals("")) {
			GraphicsTools.drawString(g, text, getCenter().getTranslated(node.getCenter()), HorizontalAlignment.center, VerticalAlignment.center);
		}
	}

	@Override
	public boolean containsPoint(Point point) {
		return super.containsPoint(point.getTranslated(node.getCenter().getNegative()));
	}

	public boolean canBeMovedFrom(Point point) {
		return containsPoint(point);
	}

	@Override
	public Label getClone() {
		Label label = (Label)super.getClone();
		label.text = this.text;
		label.node = this.node;
		return label;
	}

}
