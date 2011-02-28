package net.matmas.pnapi.xml;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matmas
 */
public class IdToXmlObject {
	
	private XmlDocument xmlDocument;
	
	public IdToXmlObject(XmlDocument xmlDocument) {
		this.xmlDocument = xmlDocument;
	}
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public Object getXmlObject(String id) {
		if (id.equals(null)) {
			return null;
		}
		if (id.equals("")) {
			return null;
		}
		if (map.containsKey(id)) {
			return map.get(id);
		}
		Object xmlObject = getXmlObjectFromXmlDocument(id, xmlDocument);
		if (xmlObject != null) {
			map.put(id, xmlObject);
		}
		return xmlObject;
	}
	
	private Object getXmlObjectFromXmlDocument(String id, XmlDocument xmlDocument) {
		for (XmlPlace xmlPlace : xmlDocument.places) {
			if (xmlPlace.id.equals(id)) {
				return xmlPlace;
			}
		}
		for (XmlTransition xmlTransition : xmlDocument.transitions) {
			if (xmlTransition.id.equals(id)) {
				return xmlTransition;
			}
		}
		return null;
	}
	
}
