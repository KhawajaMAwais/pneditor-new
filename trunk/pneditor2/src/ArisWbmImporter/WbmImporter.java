/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ArisWbmImporter;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author XY
 */

public class WbmImporter {
    
    
    public class arci {
    String start;
    String end;
        public arci(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
    
    private File file;
    private PetriNet petriNet;
    private ArrayList<String> placesList = new ArrayList<String>();
    private ArrayList<String> transitionsList = new ArrayList<String>();
    private ArrayList<arci> arcsList = new ArrayList<arci>();   
    private ArrayList<String> wbmPlaces = new ArrayList<String>() {{
        add("startNode"); add("endNode"); add("stopNode");
        add("decision"); add("merge");
    }};
    private ArrayList<String> wbmTransitions = new ArrayList<String>() {{
        add("timer"); add("observer"); add("notificationReceiver"); add("notificationBroadcaster");
        add("task"); add("humanTask"); add("businessRulesTask");
        add("fork"); add("join");
    }};
    
    
    public WbmImporter (File file){
       
            petriNet = PNEditor.getInstance().getDocument().getPetriNet();
            this.file = file;
 
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Exceptions.printStackTrace(ex);
            }
            Document doc =null;
            try {
                doc = db.parse(this.file);
            } catch (SAXException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("wbim:flowContent").item(0).getChildNodes();
            int i = 1;
            int counter = 1; // -> rozlisenie popisu pre pridane elementy
            //ziskam vsetky elementy a rozdeli ich medzi miesta a prechody
            System.out.println(nodeList.getLength());
            
            
            while (i<nodeList.getLength()) {
                Node node = nodeList.item(i);
                String name = node.getNodeName().replace("wbim:", "");
                //kedze switch nepodporuje String, musim to spravit pomocou if, else if, else
                if (wbmPlaces.contains(name)) {
                  placesList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                  Place place = new Place();
                  place.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                  petriNet.addPlace(place); 
                }
                else if (wbmTransitions.contains(name)) {
                    transitionsList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                    Transition transition = new Transition();
                    transition.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                    petriNet.addTransition(transition);
                }
                //doplnim prechody a miesta ak nastane situacia place -> place (trans. -> trans.)
                else if (name.equals("connection")) {
                    NodeList nl = nodeList.item(i).getChildNodes(); // tu uz dostavam len "wbim:connection"
                    String source = nl.item(1).getAttributes().getNamedItem("node").getNodeValue();//wbim:source
                    String target = nl.item(3).getAttributes().getNamedItem("node").getNodeValue();//wbim:target
                    if (placesList.contains(source) && placesList.contains(target)) {
                        String trans = "t" + counter++;
                        transitionsList.add(trans);
                        Transition transition = new Transition();
                        transition.getLabel().setText(trans);
                        petriNet.addTransition(transition);
                        arcsList.add(new arci(source, trans));
                        arcsList.add(new arci(trans, target));
                        
                       for(Place place : petriNet.getPlaces()){
                           if(place.getLabel().getText().equals(source)){
                            Arc arc = new Arc();
                            arc.setSource(place);
                            arc.setDestination(transition);
                            petriNet.addArc(arc);
                            }
                       }
                        
                        
                        for(Place place : petriNet.getPlaces()){
                           if(place.getLabel().getText().equals(target)){
                            Arc arc = new Arc();
                            arc.setSource(transition);
                            arc.setDestination(place);
                            petriNet.addArc(arc);
                            }
                       }
                        
                        
                    }
                    //ak ide hrana z transition do transition, pridem place medzi ne
                    else if (transitionsList.contains(source) && transitionsList.contains(target)) {
                        String place = "p" + counter++;
                        placesList.add(place);
                        arcsList.add(new arci(source, place));
                        arcsList.add(new arci(place, target));
                        
                        Place placen = new Place();
                        placen.getLabel().setText(place);
                        petriNet.addPlace(placen);  
                        
                        
                        for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(source)){
                               Arc arc = new Arc();
                               arc.setSource(tran);
                               arc.setDestination(placen);
                               petriNet.addArc(arc);
                               }
                        }
                        
                        for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(target)){
                               Arc arc = new Arc();
                               arc.setSource(placen);
                               arc.setDestination(tran);
                               petriNet.addArc(arc);
                               }
                        }
                        
                    }
                    else {
                       Transition trann = new Transition();
                       Place placen = new Place();
                       boolean placetotran = false;
                        arcsList.add(new arci(source, target));
                        for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(source)){
                                trann = tran;
                            }
                            if(tran.getLabel().getText().equals(target)){
                                trann = tran;
                            }
                        }
                        for(Place place : petriNet.getPlaces()){
                            if(place.getLabel().getText().equals(source)){
                                placen = place;
                                placetotran = true;
                            }
                            if(place.getLabel().getText().equals(target)){
                                placen = place;
                            }
                        }
                        
                         Arc arc = new Arc();
                         if(placetotran){
                         arc.setSource(placen);
                         arc.setDestination(trann);
                         }
                         else{
                         arc.setSource(trann);
                         arc.setDestination(placen);    
                         }
                         petriNet.addArc(arc);
                        
                        
                    }
                }
                i+=2;
            }

        } 
      
    
    }
    

