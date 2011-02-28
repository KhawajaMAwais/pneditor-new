package net.matmas.pnapi.xml;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Subnet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.Property;
import net.matmas.util.Xslt;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author matmas
 */

@Root(name="document")
public class XmlDocument {

	@ElementList(inline=true, required=false)
	public List<XmlPlace> places = new ArrayList<XmlPlace>();

	@ElementList(inline=true, required=false)
	public List<XmlTransition> transitions = new ArrayList<XmlTransition>();

	@ElementList(inline=true, required=false)
	public List<XmlArc> arcs = new ArrayList<XmlArc>();

	@ElementList(inline=true, required=false)
	public List<XmlSubnet> subnets = new ArrayList<XmlSubnet>();

	@Element
	public int left;

	@Element
	public int top;

	@ElementList(type=Property.class)
	public ArrayList<Property> properties = new ArrayList<Property>();

	public XmlDocument() {
	}

	public XmlDocument(Document document) {
		PetriNet petriNet = document.getPetriNet();
		Marking marking = petriNet.getMarking();

		for (Place place : petriNet.getPlaces()) {
			this.places.add(new XmlPlace(place, marking));
		}
		for (Transition transition : petriNet.getTransitions()) {
			this.transitions.add(new XmlTransition(transition));
		}
		for (Arc arc : petriNet.getArcs()) {
			this.arcs.add(new XmlArc(arc));
		}
		for (Subnet subnet : petriNet.getSubnets()) {
			this.subnets.add(new XmlSubnet(subnet));
		}

		Rectangle bounds = petriNet.getBounds(new DrawingOptions());
		this.left = bounds.x;
		this.top = bounds.y;
		for (Property property : document.getPetriNet().getProperties()) {
			this.properties.add(property);
		}
	}

	public void writeToFile(File file) throws FileNotFoundException, Exception {
		Serializer serializer = new Persister();
		serializer.write(this, file);
//		XStream xStream = new XStream();
//		xStream.autodetectAnnotations(true);
//		xStream.toXML(this, new BufferedOutputStream(new FileOutputStream(file)));
	}

	public void writeToFileWithXslt(File file, InputStream xslt) throws FileNotFoundException, IOException, TransformerException, Exception {
		if (xslt == null) {
			writeToFile(file);
			return;
		}
		File tempFile = File.createTempFile("pneditor-export", null);
		writeToFile(tempFile);
		Xslt.transformXml(tempFile, xslt, file);
		tempFile.delete(); // delete temp file
	}
}
