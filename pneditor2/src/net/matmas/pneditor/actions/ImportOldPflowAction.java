/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.BooleanProperty;
import net.matmas.pnapi.properties.Property;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.filechooser.EpsFileType;
import net.matmas.pneditor.filechooser.FileChooserDialog;
import net.matmas.util.GraphicsTools;
import net.matmas.util.Point;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



/**
 *
 * @author JONNY
 */
public class ImportOldPflowAction extends Action {

	public ImportOldPflowAction() {
		String name = "Import old Pflow";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/importOld.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
                PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                Marking currentMarking = PNEditor.getInstance().getDocument().getPetriNet().getMarking();

                FileChooserDialog chooser = new FileChooserDialog();
                FileFilter filefilter = new FileNameExtensionFilter("pflow", "pflow");
                chooser.setFileFilter(filefilter);
		chooser.setCurrentDirectory(PNEditor.getInstance().getCurrentDirectory());

                if (chooser.showOpenDialog(PNEditor.getInstance().getMainFrame()) == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();
                try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse (file);

            doc.getDocumentElement ().normalize ();
            
            NodeList listOfPlace = doc.getElementsByTagName("place");
            
            for(int s=0; s<listOfPlace.getLength() ; s++){

                    Place placeimp = new Place();
                    Node Place = listOfPlace.item(s);
                    if(Place.getNodeType() == Node.ELEMENT_NODE){

                    Element place = (Element)Place;

                    NodeList id = place.getElementsByTagName("id");
                    Element Id = (Element)id.item(0);
                    NodeList oldid = Id.getChildNodes();
                    placeimp.setId(oldid.item(0).getNodeValue().trim());
                       
                    NodeList x = place.getElementsByTagName("x");
                    Element lastNameElement = (Element)x.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    String strX = textLNList.item(0).getNodeValue().trim();
                    int X = Integer.parseInt(strX);


                    NodeList y = place.getElementsByTagName("y");
                    Element yy = (Element)y.item(0);
                    NodeList yyy = yy.getChildNodes();

                    String strY = yyy.item(0).getNodeValue().trim();
                    int Y = Integer.parseInt(strY);

                    placeimp.setCenter(new Point(X,Y));

                    NodeList label = place.getElementsByTagName("label");
                    Element ll = (Element)label.item(0);
                    if(ll.hasChildNodes()){
                    NodeList lll = ll.getChildNodes();
                    String lab = lll.item(0).getNodeValue().trim();
                    placeimp.getLabel().setText(lab);
                    }
                    else{
                        System.out.println("Nema label");
                    }

                    NodeList tokens = place.getElementsByTagName("tokens");
                    Element t = (Element)tokens.item(0);
                    NodeList tt = t.getChildNodes();
                    String strtok = tt.item(0).getNodeValue().trim();
                    int tok = Integer.parseInt(strtok);
                    currentMarking.setTokens(placeimp, tok);

                    NodeList isStatic = place.getElementsByTagName("isStatic");
                    Element i = (Element)isStatic.item(0);
                    NodeList ii = i.getChildNodes();
                    String staatic = ii.item(0).getNodeValue().trim();
                    if(staatic.equals("true")){
                        Property property = new BooleanProperty() {

                                @Override
                                public String getPropertyTypeName() {
                                   return "Boolean";
                                }
                                private JCheckBox checkBox = new JCheckBox();
                                @Override
                                public Component getEditor() {
                                    return checkBox;
                                }
                            };
                        property.setId("static");
                        property.setIdEditable(false);
                        placeimp.getProperties().add(property);
                    }
                     petriNet.addPlace(placeimp,oldid.item(0).getNodeValue().trim());
                }

               

            }

        NodeList listOfTransitions = doc.getElementsByTagName("transition");

            for(int s=0; s<listOfTransitions.getLength() ; s++){
                    Transition tranimp = new Transition();
                    Node Tran = listOfTransitions.item(s);
                    if(Tran.getNodeType() == Node.ELEMENT_NODE){

                    Element tran = (Element)Tran;

                    NodeList id = tran.getElementsByTagName("id");
                    Element Id = (Element)id.item(0);
                    NodeList oldid = Id.getChildNodes();
                    tranimp.setId(oldid.item(0).getNodeValue().trim());

                    NodeList x = tran.getElementsByTagName("x");
                    Element lastNameElement = (Element)x.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    String strX = textLNList.item(0).getNodeValue().trim();
                    int X = Integer.parseInt(strX);

                    NodeList y = tran.getElementsByTagName("y");
                    Element yy = (Element)y.item(0);
                    NodeList yyy = yy.getChildNodes();
                    String strY = yyy.item(0).getNodeValue().trim();
                    int Y = Integer.parseInt(strY);

                    tranimp.setCenter(new Point(X,Y));

                    NodeList label = tran.getElementsByTagName("label");
                    Element ll = (Element)label.item(0);
                    if(ll.hasChildNodes()){
                    NodeList lll = ll.getChildNodes();
                    String lab = lll.item(0).getNodeValue().trim();
                    tranimp.getLabel().setText(lab);
                    }
                    else{
                        System.out.println("Nema label");
                    }
                    petriNet.addTransition(tranimp,oldid.item(0).getNodeValue().trim());
                }
                     

            }

        NodeList listOfArcs = doc.getElementsByTagName("arc");

            for(int s=0; s<listOfArcs.getLength() ; s++){
                    Place p = new  Place();
                    Transition t = new Transition();
                    Boolean placetotran = false;
                    Node Tran = listOfArcs.item(s);
                    if(Tran.getNodeType() == Node.ELEMENT_NODE){

                    Element tran = (Element)Tran;

                    NodeList id = tran.getElementsByTagName("sourceId");
                    Element Id = (Element)id.item(0);
                    NodeList oldid = Id.getChildNodes();
                    String strsour = oldid.item(0).getNodeValue().trim();

                    for(Place place:petriNet.getPlaces()){
                        if(place.getId().equals(strsour)){
                            
                            p = place;
                            placetotran = true;
                        }
                    }

                    for(Transition trann:petriNet.getTransitions()){
                        if(trann.getId().equals(strsour)){
                            t = trann;
                        }
                    }

                    NodeList x = tran.getElementsByTagName("destinationId");
                    Element lastNameElement = (Element)x.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    String strdes = textLNList.item(0).getNodeValue().trim();

                    for(Place place:petriNet.getPlaces()){
                        if(place.getId().equals(strdes)){
                            p = place;

                        }
                    }

                    for(Transition trann:petriNet.getTransitions()){
                        if(trann.getId().equals(strdes)){
                            t = trann;
                        }
                    }

                    Arc arcimp = new Arc(p, t, placetotran);

                    NodeList y = tran.getElementsByTagName("multiplicity");
                    Element yy = (Element)y.item(0);
                    NodeList yyy = yy.getChildNodes();
                    String strmulti = yyy.item(0).getNodeValue().trim();
                    int multi = Integer.parseInt(strmulti);

                    arcimp.setMultiplicity(multi);
                    petriNet.addArc(arcimp);
                }
                
            }

        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line "
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException ee) {
        Exception x = ee.getException ();
        }catch (Throwable t) {
        }

                }

        }
}