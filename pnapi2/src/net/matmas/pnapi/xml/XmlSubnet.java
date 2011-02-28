package net.matmas.pnapi.xml;

import net.matmas.pnapi.Subnet;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="subnet")
public class XmlSubnet {

	@Element
	public int x;

	@Element
	public int y;

	@Element
	public int width;

	@Element
	public int height;

	public XmlSubnet() {
	}

	public XmlSubnet(Subnet subnet) {
		this.x = subnet.getCenter().getX();
		this.y = subnet.getCenter().getY();
		this.width = subnet.getWidth();
		this.height = subnet.getHeight();
	}

}
