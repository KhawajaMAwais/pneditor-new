package net.matmas.pnapi.xml;

import java.util.ArrayList;
import java.util.List;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.properties.Property;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 *
 * @author matmas
 */
public class XmlNode {

	@Attribute
	public String id;

	@Element
	public int x;

	@Element
	public int y;

	@Element(required=false)
	public String label;

	@Element
	public int labelX;

	@Element
	public int labelY;

	@ElementList
	public ArrayList<Property> properties = new ArrayList<Property>();

	public XmlNode() {
	}

	public XmlNode(Node node) {
		this.id = node.getId();
		this.x = node.getCenter().getX();
		this.y = node.getCenter().getY();
		this.label = node.getLabel().getText();
		this.labelX = node.getLabel().getCenter().getX();
		this.labelY = node.getLabel().getCenter().getY();
		for (Property property : node.getProperties()) {
			this.properties.add(property);
		}
	}
	
}
