package net.matmas.pnapi.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.transform.TransformerException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Subnet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.Property;
import net.matmas.util.Point;
import net.matmas.util.Xslt;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author matmas
 */
public class DocumentImporter {
	
	private XmlDocument xmlDocument;
	private IdToXmlObject idToXmlObject;
	
	public Document readFromFile(File file) throws Exception {
		Serializer serializer = new Persister();
		xmlDocument = serializer.read(XmlDocument.class, file);
		idToXmlObject = new IdToXmlObject(xmlDocument);
		return getDocument();
	}

	public Document readFromFileWithXslt(File file, InputStream xslt) throws TransformerException, IOException, Exception  {
		if (xslt == null) {
			return readFromFile(file);
		}
		File tempFile = File.createTempFile("pneditor-import", null);
		Xslt.transformXml(file, xslt, tempFile);
		Document document = readFromFile(file);
		tempFile.delete();
		return document;
	}
	
	private Document getDocument() {
		Document document = getNewDocument(xmlDocument);
		constructMarking(document.getPetriNet().getMarking(), xmlDocument);

		for (Property property : xmlDocument.properties) {
			document.getPetriNet().getProperties().add(property);
			property.setWithProperties(document.getPetriNet());
		}

		document.getPetriNet().getIdGenerator().fixFutureUniqueIds(document.getPetriNet());
		return document;
	}
	
	private Object getObjectFromId(String id) {
		return getObject(idToXmlObject.getXmlObject(id));
	}
		
	private Map<Object, Object> cachedObjects = new HashMap<Object, Object>();
	
	private Object getObject(Object xmlObject) {
		if (cachedObjects.containsKey(xmlObject)) {
			return cachedObjects.get(xmlObject);
		}
		Object object = null;
		if (xmlObject instanceof XmlArc) {
			object = getNewArc((XmlArc)xmlObject);
		}
		if (xmlObject instanceof XmlPlace) {
			object = getNewPlace((XmlPlace)xmlObject);
		}
		if (xmlObject instanceof XmlTransition) {
			object = getNewTransition((XmlTransition)xmlObject);
		}
		if (xmlObject instanceof XmlSubnet) {
			object = getNewSubnet((XmlSubnet)xmlObject);
		}
		
		if (object != null) {
			cachedObjects.put(xmlObject, object);
		}
		return object;
	}
	
	private Document getNewDocument(XmlDocument xmlDocument) {
		Document document = new Document();
		if (xmlDocument.arcs != null) {
		for (XmlArc xmlArc : xmlDocument.arcs) {
			document.getPetriNet().addArc((Arc)getObject(xmlArc));
		}
		}
		if (xmlDocument.places != null) {
			for (XmlPlace xmlPlace : xmlDocument.places) {
				document.getPetriNet().addPlace((Place)getObject(xmlPlace));
			}
		}
		if (xmlDocument.transitions != null) {
			for (XmlTransition xmlTransition : xmlDocument.transitions) {
				document.getPetriNet().addTransition((Transition)getObject(xmlTransition));
			}
		}
		if (xmlDocument.subnets != null) {
			for (XmlSubnet xmlSubSubnet : xmlDocument.subnets) {
				document.getPetriNet().addSubnet((Subnet)getObject(xmlSubSubnet));
			}
		}

                for(Place place : document.getPetriNet().getPlaces())
                    {
                        if(place.getIsORSplit())
                        {
                            for(Place placee : document.getPetriNet().getPlaces())
                            {
                                if(place.getORJoinId().equals(placee.getId()))
                                {
                                    place.setORJoin(placee);
                                }
                            }
                        }
                        if(place.getIsIterationStart())
                        {
                            for(Place placee : document.getPetriNet().getPlaces())
                            {
                                if(place.getIterJoinId().equals(placee.getId()))
                                {
                                    place.setIterationEnd(placee);
                                }
                            }
                        }
                    }
                for(Transition tran : document.getPetriNet().getTransitions())
                {
                    if(tran.getIsANDSplit())
                        {
                            for(Transition trann : document.getPetriNet().getTransitions())
                            {
                                if(tran.getANDJoinId().equals(trann.getId()))
                                {
                                    tran.setANDJoin(trann);
                                }
                            }
                        }
                }
                
		return document;
	}
	
	private void constructMarking(Marking marking, XmlDocument xmlDocument) {
		for (XmlPlace xmlPlace : xmlDocument.places) {
			marking.setTokens((Place)getObject(xmlPlace), xmlPlace.tokens);
		}
	}

	private Arc getNewArc(XmlArc xmlArc) {
		Node source = (Node)getObjectFromId(xmlArc.sourceId);
		Node destination = (Node)getObjectFromId(xmlArc.destinationId);
		Arc arc = new Arc();
		arc.setSource(source);
		arc.setDestination(destination);
		arc.setMultiplicity(xmlArc.multiplicity);
                arc.setIsIterationArc(xmlArc.IsIterationArc);
                arc.setProbability(xmlArc.Probability);
		if (xmlArc.breakPoints != null) {
			List<Point> breakPoints = new LinkedList<Point>();
			for (XmlPoint xmlPoint : xmlArc.breakPoints) {
				breakPoints.add(new Point(xmlPoint.x, xmlPoint.y));
			}
			arc.setBreakPoints(breakPoints);
		}

		for (Property property : xmlArc.properties) {
			arc.getProperties().add(property);
			property.setWithProperties(arc);
		}

		return arc;
	}
	
	private Place getNewPlace(XmlPlace xmlPlace) {
		Place place = new Place();

		place.setId(xmlPlace.id);
		place.getLabel().setText(xmlPlace.label);
		place.getLabel().setCenter(new Point(xmlPlace.labelX, xmlPlace.labelY));
		place.setCenter(new Point(xmlPlace.x, xmlPlace.y));
                place.setIsStartPlace(xmlPlace.IsStartPlace);
                place.setIsEndPlace(xmlPlace.IsEndPlace);
                place.setArrivalRate(xmlPlace.ArrivalRate);
                place.setIsORSplit(xmlPlace.IsORSplit);
                place.setIsIterationStart(xmlPlace.IsIterationStart);
                if(xmlPlace.IsORSplit)
                {
                    place.setORJoinId(xmlPlace.ORJoinId);
                }
                if(xmlPlace.IsIterationStart)
                {
                    place.setIterJoinId(xmlPlace.IterJoinId);
                }
		for (Property property : xmlPlace.properties) {
			place.getProperties().add(property);
			property.setWithProperties(place);
		}

		return place;
	}
	
	private Transition getNewTransition(XmlTransition xmlTransition) {
		Transition transition = new Transition();

		transition.setId(xmlTransition.id);
		transition.getLabel().setText(xmlTransition.label);
		transition.getLabel().setCenter(new Point(xmlTransition.labelX, xmlTransition.labelY));
		transition.setCenter(new Point(xmlTransition.x, xmlTransition.y));
                transition.setMM1(xmlTransition.MM1);
                transition.setMMc(xmlTransition.MMc);
                transition.setNumberOfServers(xmlTransition.NumberOfServers);
                transition.setServisRate(xmlTransition.ServisRate);
                transition.setIsANDSplit(xmlTransition.IsANDSplit);
                if(xmlTransition.IsANDSplit)
                {
                    transition.setANDJoinId(xmlTransition.ANDJoinId);
                }

		for (Property property : xmlTransition.properties) {
			transition.getProperties().add(property);
			property.setWithProperties(transition);
		}

		return transition;
	}

	private Subnet getNewSubnet(XmlSubnet xmlSubnet) {
		Subnet subnet = new Subnet();
		subnet.setCenter(new Point(xmlSubnet.x, xmlSubnet.y));
		subnet.setSize(xmlSubnet.width, xmlSubnet.height);
		return subnet;
	}
	
}
