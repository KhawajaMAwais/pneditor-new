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
    
    //potrebne ako navratovy typ funkcie parseSubprocess
    private class ReturnValues {
        String start;
        String end;
    }
    
    private File file;
    private PetriNet petriNet;
    private ArrayList<String> placesList = new ArrayList<>();
    private ArrayList<String> transitionsList = new ArrayList<>();
    private ArrayList<arci> arcsList = new ArrayList<>();   
    private ArrayList<String> wbmPlaces = new ArrayList<String>() {{
        add("startNode"); add("endNode"); add("stopNode");
        add("decision"); add("merge");
    }};
    private ArrayList<String> wbmTransitions = new ArrayList<String>() {{
        add("timer"); add("observer"); add("notificationReceiver"); add("notificationBroadcaster");
        add("task"); add("humanTask"); add("businessRulesTask");
        add("fork"); add("join");
    }};
    private int counter = 1;//pre rozlisovanie rovnakych nazvov
    
    
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
            ReturnValues rv = new ReturnValues();//pre potreby subprocesu
            //ziskam vsetky elementy a rozdeli ich medzi miesta a prechody
            while (i<nodeList.getLength()) {
                Node node = nodeList.item(i);
                if (node.getNodeName() == null)
                    continue;
                String name = node.getNodeName().replace("wbim:", "");
                //kedze switch nepodporuje String, musim to spravit pomocou if, else if, else
//----MIESTO----
                if (wbmPlaces.contains(name)) {
                    placesList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                    Place place = new Place();
                    place.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                    petriNet.addPlace(place); 
                }
//----PRECHOD---
                else if (wbmTransitions.contains(name)) {
                    transitionsList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                    Transition transition = new Transition();
                    transition.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                    petriNet.addTransition(transition);
                }
//----HRANA-----
                else if (name.equals("connection")) {
                    createArc(nodeList.item(i).getChildNodes());
                }
//----PODPROCES-
                else if (name.equals("process")){
                    rv = parseSubprocess(getNodeByName(node.getChildNodes(), "wbim:flowContent").getChildNodes());
                    renameSubnetConnections(node, rv.start, rv.end);
                }
                i+=2;
            }

        }
    
    
    private void createArc (NodeList nodeList){
        String source = getNodeByName(nodeList, "wbim:source").getAttributes().getNamedItem("node").getNodeValue();//wbim:source;
        String target = getNodeByName(nodeList, "wbim:target").getAttributes().getNamedItem("node").getNodeValue();//wbim:target;

        // ak ide hrana z place do place, pridam medzi ne prechod
        if (placesList.contains(source) && placesList.contains(target)) {
            String trans = "t" + counter++;
            transitionsList.add(trans);
            Transition transition = new Transition();
            transition.getLabel().setText(trans);
            petriNet.addTransition(transition);
            arcsList.add(new arci(source, trans));
            arcsList.add(new arci(trans, target));

            for(Place place : petriNet.getPlaces()){
                //from source place to new transition
                if(place.getLabel().getText().equals(source)){
                    Arc arc = new Arc();
                    arc.setSource(place);
                    arc.setDestination(transition);
                    petriNet.addArc(arc);
                }
                //from new transition to destination place
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
                //from source transition to new place
                if(tran.getLabel().getText().equals(source)){
                    Arc arc = new Arc();
                    arc.setSource(tran);
                    arc.setDestination(placen);
                    petriNet.addArc(arc);
                }

                //from new place to destination transition
                if(tran.getLabel().getText().equals(target)){
                    Arc arc = new Arc();
                    arc.setSource(placen);
                    arc.setDestination(tran);
                    petriNet.addArc(arc);
                }
            }
        }
        //ak je prechod place->trans. alebo trans.->place
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
    
    
    private ReturnValues parseSubprocess (NodeList nodeList){
        ReturnValues ret = new ReturnValues();
        String start = getNodeByName(nodeList, "wbim:startNode").getAttributes().getNamedItem("name").getNodeValue();
        String end = getNodeByName(nodeList, "wbim:stopNode").getAttributes().getNamedItem("name").getNodeValue();
        
        int i=1;
        while (i<nodeList.getLength()) {
            Node node = nodeList.item(i);
            String name = node.getNodeName().replace("wbim:", "");
            //kedze switch nepodporuje String, musim to spravit pomocou if, else if, else
//-MIESTO----
            if (wbmPlaces.contains(name)) {
                if (!name.equals("startNode") && !name.equals("endNode") && !name.equals("stopNode")){
                    if (placesList.contains(node.getAttributes().getNamedItem("name").getNodeValue())){
                        renameElement(node);
                    }
                    placesList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                    Place place = new Place();
                    place.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                    petriNet.addPlace(place);
                }
            }
//-PRECHOD---
            else if (wbmTransitions.contains(name)) {
                if (transitionsList.contains(node.getAttributes().getNamedItem("name").getNodeValue())) {
                    renameElement(node);
                }
                transitionsList.add(node.getAttributes().getNamedItem("name").getNodeValue());
                Transition transition = new Transition();
                transition.getLabel().setText(node.getAttributes().getNamedItem("name").getNodeValue());
                petriNet.addTransition(transition);
            }
//-HRANA-----
            else if (name.equals("connection")) {
                //vynechanie prepojenia zo startNode
                if (getNodeByName(node.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").getNodeValue().equals(start)){
                    ret.start = getNodeByName(node.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").getNodeValue();
                }
                //vynechanie prepojenia do endNode
                else if (getNodeByName(node.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").getNodeValue().equals(end)){
                    ret.end = getNodeByName(node.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").getNodeValue();
                }
                else {
                    createArc(nodeList.item(i).getChildNodes());
                }
            }
//-PODPROCES-
            else if (name.equals("process")){
                ReturnValues rv = parseSubprocess(getNodeByName(node.getChildNodes(), "wbim:flowContent").getChildNodes());
                renameSubnetConnections(node, rv.start, rv.end);
            }
            i+=2;//kazdy druhy element je "#text"
        }
        return ret;
    }
    
    
    private Node getNodeByName (NodeList nodeList, String name){
        Node node = null;
        
        for (int i=0; i<nodeList.getLength(); i++){
            node = nodeList.item(i);
            if (node.getNodeName().equals(name))
                return node;
        }
        return node;
    }
    
    //funkcia zmeni hodnotu atributu name v node aj v connections
    private void renameElement (Node node){
        String oldName = node.getAttributes().getNamedItem("name").getNodeValue();
        String newName = oldName + counter++;
        node.getAttributes().getNamedItem("name").setNodeValue(newName);
        
        NodeList nl = node.getParentNode().getChildNodes();
        for (int i=0; i<nl.getLength(); i++){
            node = nl.item(i);
            if (node.getNodeName() != null && node.getNodeName().equals("wbim:connection")){
                if ( getNodeByName(node.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").getNodeValue().equals(oldName) ){
                    getNodeByName(node.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").setNodeValue(newName);
                }
                else if ( getNodeByName(node.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").getNodeValue().equals(oldName) ){
                    getNodeByName(node.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").setNodeValue(newName);
                }
            }
        }
    }
    
    private void renameSubnetConnections (Node node, String input, String output) {
        NodeList nl = node.getParentNode().getChildNodes();
        String name = node.getAttributes().getNamedItem("name").getNodeValue();
        for (int i=1; i<nl.getLength(); i++){
            Node n = nl.item(i);
            if (n.getNodeName() != null && n.getNodeName().equals("wbim:connection")){
                if ( getNodeByName(n.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").getNodeValue().equals(name) ){
                    getNodeByName(n.getChildNodes(), "wbim:source").getAttributes().getNamedItem("node").setNodeValue(output);
                }
                else if ( getNodeByName(n.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").getNodeValue().equals(name) ){
                    getNodeByName(n.getChildNodes(), "wbim:target").getAttributes().getNamedItem("node").setNodeValue(input);
                }
            }
        }
    }
}