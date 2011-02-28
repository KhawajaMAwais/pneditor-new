package net.matmas.pnapi;

import net.matmas.pnapi.editor.Movable;
import net.matmas.pnapi.editor.Resizable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import net.matmas.pnapi.editor.Resize;
import net.matmas.util.GraphicsTools;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class Subnet extends Element implements Movable, Resizable {

	public Subnet getParentSubnet() {
		Subnet parentSubnet = null;
		for (Subnet subnet : getPetriNet().getSubnets()) {
			if (subnet != this) {
				if (subnet.getBounds().contains(this.getBounds())) {
					if (parentSubnet == null) {
						parentSubnet = subnet;
					}
					else {
						if (parentSubnet.getBounds().contains(subnet.getBounds())) {
							parentSubnet = subnet;
						}
					}
				}
			}
		}
		return parentSubnet;
	}

	// -------------------------------------------------------------------------

	public boolean isTopLevel() {
		return getParentSubnet() == null;
	}
	
	private Set<Subnet> getTopLevelSubnets() {
		Set<Subnet> topLevelSubnets = new HashSet<Subnet>();
		for (Subnet subnet : getPetriNet().getSubnets()) {
			if (subnet.isTopLevel()) {
				topLevelSubnets.add(subnet);
			}
		}
		return topLevelSubnets;
	}
	
	// -------------------------------------------------------------------------

	public Set<Subnet> getChildSubnets() {
		Set<Subnet> childSubnets = new HashSet<Subnet>();
		for (Subnet subnet : getPetriNet().getSubnets()) {
			if (subnet.getParentSubnet() == this) {
				childSubnets.add(subnet);
			}
		}
		return childSubnets;
	}

	public Set<Subnet> getChildSubnetsRecursively() {
		Set<Subnet> descendantSubnets = new HashSet<Subnet>();
		for (Subnet subnet : getPetriNet().getSubnets()) {
			if (subnet != this) {
				if (this.getBounds().contains(subnet.getBounds())) {
					descendantSubnets.add(subnet);
				}
			}
		}
		return descendantSubnets;
	}

	public Set<Subnet> getSiblingSubnets() {
		Set<Subnet> siblingSubnets = new HashSet<Subnet>();
		if (getParentSubnet() != null) {
			for (Subnet parentChildSubnet : getParentSubnet().getChildSubnets()) {
				if (parentChildSubnet != this) {
					siblingSubnets.add(parentChildSubnet);
				}
			}
		}
		else {
			for (Subnet topLevelSubnet : getTopLevelSubnets()) {
				if (topLevelSubnet != this) {
					siblingSubnets.add(topLevelSubnet);
				}
			}
		}
		return siblingSubnets;
	}

	// -------------------------------------------------------------------------

	public Set<Node> getNodes() {
		Set<Node> nodes = new HashSet<Node>();
		for (Node node : getPetriNet().getNodes()) {
			if (this.getBounds().contains(node.getCenter().getPoint()) && !belongsToDescendantSubnet(node)) {
				nodes.add(node);
			}
		}
		return nodes;
	}

	private boolean belongsToDescendantSubnet(Node node) {
		boolean belongsToDescendantSubnet = false;
		for (Subnet childSubnet : getChildSubnets()) {
			if (childSubnet.getBounds().contains(node.getCenter().getPoint())) {
				belongsToDescendantSubnet = true;
			}
		}
		return belongsToDescendantSubnet;
	}

	private Set<Node> getParentSubnetNodes() {
		if (getParentSubnet() != null) {
			return getParentSubnet().getNodes();
		}
		else {
			Set<Node> parentSubnetNodes = new HashSet<Node>();
			for (Node node : getPetriNet().getNodes()) {
				boolean nodeIsTopLevel = true;
				for (Subnet subnet : getPetriNet().getSubnets()) {
					if (subnet.getNodes().contains(node)) {
						nodeIsTopLevel = false;
						break;
					}
				}
				if (nodeIsTopLevel) {
					parentSubnetNodes.add(node);
				}
			}
			return parentSubnetNodes;
		}
	}

	public Set<Node> getNodesRecursively() {
		Set<Node> nodes = new HashSet<Node>();
		for (Node node : getPetriNet().getNodes()) {
			if (this.getBounds().contains(node.getCenter().getPoint())) {
				nodes.add(node);
			}
		}
		return nodes;
	}

	// -------------------------------------------------------------------------

	public void draw(Graphics g, DrawingOptions drawingOptions) {
		if (isCollapsed()) {
			g.setColor(Color.lightGray);
			g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		}

		Color color = drawingOptions.getElementColors().get(this);
		if (color == null) {
			color = Color.black;
		}
		g.setColor(color);
		((Graphics2D)g).setStroke(new BasicStroke(2f));
		g.drawRect(getLeft(), getTop(), getWidth(), getHeight());
		((Graphics2D)g).setStroke(new BasicStroke(1f));

//		if (getPetriNet() != null) {
//			for (Subnet subnet : getChildSubnets()) {
//				g.fillRect(subnet.getLeft(), subnet.getTop(), subnet.getWidth(), subnet.getHeight());
//			}
//
//			for (Node node : getPetriNet().getNodes()) {
////				node.getLabel().setText("");
//			}
//			for (Node node : this.getNodesRecursively()) {
//				if (belongsToDescendantSubnet(node)) {
//					node.getLabel().setText("my node");
//				}
//			}
//		}
	}

	@Override
	public Subnet getClone() {
		Subnet clone = (Subnet)super.getClone();
		return clone;
	}

	public static final int nearTolerance = 5;

	@Override
	public boolean containsPoint(Point point) {
		if (collapsed) {
			return super.containsPoint(point);
		}
		final Point topLeft = getTopLeft();
		final Point bottomRight = getBottomRight();
		final Point topRight = getTopRight();
		final Point bottomLeft = getBottomLeft();
		return GraphicsTools.isPointNearSegment(topLeft, topRight, point, nearTolerance) ||
			   GraphicsTools.isPointNearSegment(bottomLeft, bottomRight, point, nearTolerance) ||
			   GraphicsTools.isPointNearSegment(topLeft, bottomLeft, point, nearTolerance) ||
			   GraphicsTools.isPointNearSegment(topRight, bottomRight, point, nearTolerance);
	}

	public boolean canBeMovedFrom(Point point) {
		if (collapsed) {
			return containsPoint(point);
		}
		final Point topLeft = getTopLeft();
		final Point bottomRight = getBottomRight();
		final Point topRight = getTopRight();
		final Point bottomLeft = getBottomLeft();
		return containsPoint(point) && !GraphicsTools.isPointNearPoint(topLeft, point, nearTolerance)
									&& !GraphicsTools.isPointNearPoint(bottomRight, point, nearTolerance)
									&& !GraphicsTools.isPointNearPoint(topRight, point, nearTolerance)
									&& !GraphicsTools.isPointNearPoint(bottomLeft, point, nearTolerance);
	}
	
	public Resize getResizeFrom(Point point) {
		if (collapsed) {
			return null;
		}
		final Point topLeft = getTopLeft();
		final Point bottomRight = getBottomRight();
		final Point topRight = getTopRight();
		final Point bottomLeft = getBottomLeft();
		if (GraphicsTools.isPointNearPoint(topLeft, point, nearTolerance)) {
			return Resize.northWest;
		}
		if (GraphicsTools.isPointNearPoint(bottomRight, point, nearTolerance)) {
			return Resize.southEast;
		}
		if (GraphicsTools.isPointNearPoint(topRight, point, nearTolerance)) {
			return Resize.northEast;
		}
		if (GraphicsTools.isPointNearPoint(bottomLeft, point, nearTolerance)) {
			return Resize.southWest;
		}
		return null;
	}

	public void moveByWithContents(Point point) {
		Set<Node> nodes = getNodesRecursively();
		for (Subnet childSubnet : getChildSubnets()) {
			childSubnet.moveBy(point);
		}
		this.moveBy(point);
		for (Node node : nodes) {
			node.moveBy(point);
		}
	}

	// -------------------------------------------------------------------------

	private final static int collapsedWidth = 30;
	private final static int collapsedHeight = 30;

	boolean collapsed;

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		if (this.collapsed != collapsed) {
			this.collapsed = collapsed;
			if (collapsed) {
				collapse();
			}
			else {
				expand();
			}
		}
	}

	private Point expandedSize;
	private Map<Node, Point> nodeRelativePositions = new Hashtable<Node, Point>();
	private Map<Subnet, Point> childSubnetRelativePositions = new Hashtable<Subnet, Point>();
	private Map<Subnet, Point> childSubnetSizes = new Hashtable<Subnet, Point>();

	private void collapse() {
		childSubnetRelativePositions.clear();
		for (Subnet childSubnet : getChildSubnetsRecursively()) {
			childSubnetRelativePositions.put(childSubnet, childSubnet.getCenter().getTranslated(this.getCenter().getNegative()));
			childSubnetSizes.put(childSubnet, childSubnet.getSize());
			childSubnet.setCenter(this.getCenter());
			childSubnet.setSize(0, 0);
		}

		nodeRelativePositions.clear();
		for (Node node : getNodesRecursively()) {
			nodeRelativePositions.put(node, node.getCenter().getTranslated(this.getCenter().getNegative()));
			node.setCenter(this.getCenter());
		}
		expandedSize = getSize();
		setSize(collapsedWidth, collapsedHeight);
		attractNodes(true);
	}

	private void expand() {
		attractNodes(false);
		setSize(expandedSize);
		for (Node node : getNodesRecursively()) {
			node.setCenter(nodeRelativePositions.get(node).getTranslated(this.getCenter()));
		}

		for (Subnet childSubnet : getChildSubnetsRecursively()) {
			childSubnet.setCenter(childSubnetRelativePositions.get(childSubnet).getTranslated(this.getCenter()));
			childSubnet.setSize(childSubnetSizes.get(childSubnet));
		}
	}

	private void attractNodes(boolean attract) {
		Set<Node> subnetNodes = this.getNodesRecursively();
		for (Node node : getParentSubnetNodes()) {

			boolean nodeIsBehindAnotherSubnet = false;
			for (Subnet siblingSubnet : getSiblingSubnets()) {
				if (siblingSubnet.getBounds().intersectsLine(new Line2D.Double(node.getCenter().getPoint(), this.getCenter().getPoint()))) {
					nodeIsBehindAnotherSubnet = true;
				}
			}

			if (!subnetNodes.contains(node) && !nodeIsBehindAnotherSubnet) {
				double expandedWidth = expandedSize.getX();
				double expandedHeight = expandedSize.getY();

				double gainedWidth = (expandedWidth - collapsedWidth) / 2;
				double gainedHeight = (expandedHeight - collapsedHeight) / 2;
				int subnetX = this.getCenter().getX();
				int subnetY = this.getCenter().getY();
				int nodeX = node.getCenter().getX();
				int nodeY = node.getCenter().getY();

				double a = Math.abs(subnetX - nodeX);
				double b = Math.abs(subnetY - nodeY);
				double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

				double a2;
				double b2;
				if (a == 0) {
					a2 = 0;
					b2 = expandedHeight / 2;
				}
				else if (b == 0) {
					a2 = expandedWidth / 2;
					b2 = 0;
				}
				else if (a / b > expandedWidth / expandedHeight) {
					a2 = expandedWidth / 2;
					b2 = b / a * a2;
				}
				else {
					b2 = expandedHeight / 2;
					a2 = a / b * b2;
				}
				double c2 = Math.sqrt(Math.pow(a2, 2) + Math.pow(b2, 2));

				double a3;
				double b3;
				if (a == 0) {
					a3 = 0;
					b3 = (double)collapsedHeight / 2;
				}
				else if (b == 0) {
					a3 = (double)collapsedWidth / 2;
					b3 = 0;
				}
				else if (a / b > (double)collapsedWidth / collapsedHeight) {
					a3 = (double)collapsedWidth / 2;
					b3 = b / a * a3;
				}
				else {
					b3 = (double)collapsedHeight / 2;
					a3 = a / b * b3;
				}
				double c3 = Math.sqrt(Math.pow(a3, 2) + Math.pow(b3, 2));

				double collapseDepth = c2 - c3;
				double nodeDistanceFromCollapsedBorder = c - c3;
				double nodeDistanceFromExpandedBorder = c - c2;

				double attractionToSubnet = 0;

				// attract
				double nearnessToExpandedBorder = 2 * collapseDepth - nodeDistanceFromExpandedBorder;

				// repel
				double nearnessToCollapsedBorder = 3 * collapseDepth - nodeDistanceFromCollapsedBorder;

				if (attract && nearnessToExpandedBorder > 0 || !attract && nearnessToCollapsedBorder > 0) {
					if (attract) {
						attractionToSubnet = nearnessToExpandedBorder / 2;
					}
					else {
						attractionToSubnet = - nearnessToCollapsedBorder / 3;
					}
					double c4 = c - attractionToSubnet; // new node distance to subnet
					double a4;
					double b4;
					if (a == 0) {
						a4 = 0;
						b4 = c4;
					}
					else if (b == 0) {
						a4 = c4;
						b4 = 0;
					}
					else {
						a4 = Math.sqrt(Math.pow(c4, 2) / (Math.pow(b, 2) / Math.pow(a, 2) + 1));
						b4 = b / a * a4;
					}
					if (nodeX < subnetX) {
						nodeX = subnetX - (int)Math.round(a4);
					}
					else {
						nodeX = subnetX + (int)Math.round(a4);
					}
					if (nodeY < subnetY) {
						nodeY = subnetY - (int)Math.round(b4);
					}
					else {
						nodeY = subnetY + (int)Math.round(b4);
					}
					node.setCenter(new Point(nodeX, nodeY));
				}
			}
		}
	}

}
