package net.matmas.pnapi.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="point")
public class XmlPoint {

	@Attribute
	public int x;

	@Attribute
	public int y;
	
}
