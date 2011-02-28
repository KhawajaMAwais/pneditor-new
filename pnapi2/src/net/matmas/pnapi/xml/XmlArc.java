package net.matmas.pnapi.xml;

import java.util.ArrayList;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.properties.Property;
import net.matmas.util.Point;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="arc")
public class XmlArc {

	@Element
	public String sourceId;

	@Element
	public String destinationId;

        @Element
        public Boolean IsIterationArc;

        @Element
        public double Probability;

	@Element
	public int multiplicity;

	@ElementList
	public ArrayList<XmlPoint> breakPoints = new ArrayList<XmlPoint>();

	@ElementList
	public ArrayList<Property> properties = new ArrayList<Property>();

	public XmlArc() {
	}

	public XmlArc(Arc arc) {
		multiplicity = arc.getMultiplicity();
		sourceId = arc.getSource().getId();
		destinationId = arc.getDestination().getId();
                IsIterationArc = arc.getIsIterationArc();
                Probability = arc.getProbability();
		for (Point point : arc.getBreakPoints()) {
			XmlPoint xmlPoint = new XmlPoint();
			xmlPoint.x = point.getX();
			xmlPoint.y = point.getY();
			this.breakPoints.add(xmlPoint);
		}
		for (Property property : arc.getProperties()) {
			this.properties.add(property);
		}
	}

}
