/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ArisWbmImporter;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
/**
 *
 * @author XY
 */
public class ArisImporter {
    
    public class arci {
    String start;
    String end;
        public arci(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
    
    class element {
    String name;
    String ID;
    ArrayList<String> toIdList = new ArrayList<String>();
    String type;
}
    private File file;
    private PetriNet petriNet;
    private ArrayList<element> list = new ArrayList<element>(); 
    private ArrayList<String> placesList = new ArrayList<String>();
    private ArrayList<String> transitionsList = new ArrayList<String>();
    private ArrayList<arci> arcsList = new ArrayList<arci>();

    public ArisImporter(File file) {
        this.file = file;
        petriNet = PNEditor.getInstance().getDocument().getPetriNet();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Exceptions.printStackTrace(ex);
        }
            Document doc = null;
        try {
            doc = db.parse(this.file);
        } catch (SAXException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        NodeList nodeList = doc.getElementsByTagName("ObjDef");// vrati vsetky elementy s tagom ObjDef
        for (int i = 0; i < nodeList.getLength(); i++) {
            String str;
            element el = new element();
            Node node = nodeList.item(i);
            NamedNodeMap nnm = node.getAttributes(); // vrati vsetky atributy elementu

            if (nnm == null)
                    continue;

            str = nnm.getNamedItem("TypeNum").getNodeValue();
            el.type = str.replace("OT_", "");

            //ak sa nejedna o hlavne elementy, pokracujem na dalsi prvok
            if (!el.type.equals("EVT") && !el.type.equals("FUNC") && !el.type.equals("RULE"))
                continue;


            str = nnm.getNamedItem("ObjDef.ID").getNodeValue();
            el.ID = str.replace("ObjDef.", "");


            NodeList nl = node.getChildNodes();
            for (int j = 0; j < nl.getLength(); j++) {
                NamedNodeMap nnm2 = nl.item(j).getAttributes();
                if (nnm2 == null)
                    continue;

                // z childNodov vytiahnem ten, v ktorom ma atribut "AttrDef.Type" hodnotu "AT_NAME" => nazov elementu
                if (nnm2.getNamedItem("AttrDef.Type") != null && nnm2.getNamedItem("AttrDef.Type").getNodeValue().equals("AT_NAME")) {
                    // pozriem na jeho childa "AttrValue" a zoberiem text
                    el.name = nl.item(j).getChildNodes().item(1).getTextContent();
                    continue;
                }

                // z childNodov vytiahnem ten, ktory ma atribut "ToObjDef.IdRef"
                if (nnm2.getNamedItem("ToObjDef.IdRef") != null) {
                    // vytiehnem si hodnotu tohto atributu
                    str = nnm2.getNamedItem("ToObjDef.IdRef").getNodeValue();
                    el.toIdList.add(str.replace("ObjDef.", ""));
                    continue;
                }
            }
            list.add(el);
        }
        setArrays ();
    }
    
    private void setArrays () {
        int counter = 1; // rozlisovanie rovnakych nazvov
        
        //tu sa pohram so zoznamom, vytvorim miesta a prechody
        for (element e:list) {
            //zoznam eventov = miest
            if (e.type.equals("EVT")) {
                if (placesList.contains(e.name) || transitionsList.contains(e.name))
                    e.name = e.name + counter++;
                placesList.add(e.name);
                Place place = new Place();
                place.getLabel().setText(e.name);
                petriNet.addPlace(place); 
            }
            //zoznam funkcii = prechodov
            else if (e.type.equals("FUNC")) {
                if (placesList.contains(e.name) || transitionsList.contains(e.name))
                    e.name = e.name + counter++;
                transitionsList.add(e.name);
                Transition transition = new Transition();
                transition.getLabel().setText(e.name);
                petriNet.addTransition(transition);
            }
            //operatory
            else if (e.type.equals("RULE")) {
                //OR, XOR
                if (e.name.equals("OR operátor") || e.name.equals("XOR operátor")) {
                    if (e.toIdList.size() == 1) {//ak pokracuje len do jedneho, potom je to JOIN
                        e.name = "OR join " + counter++;
                    }
                    else { // ak ide o join
                        e.name = "OR split " + counter++;
                    }
                    placesList.add(e.name);
                    Place place = new Place();
                    place.getLabel().setText(e.name);
                    petriNet.addPlace(place);
                }
                //AND
                else if (e.name.equals("AND operátor")) {
                    if (e.toIdList.size() == 1) {//ak pokracuje len do jedneho, potom je to JOIN
                        e.name = "AND join " + counter++;
                    }
                    else { // ak ide o join
                        e.name = "AND split " + counter++;
                    }
                    transitionsList.add(e.name);
                    Transition transition = new Transition();
                    transition.getLabel().setText(e.name);
                    petriNet.addTransition(transition);
                }
            }
        }
        
        //po vytvoreni miest a prechodov pridam hrany a doplnim chybajuce
        for (element e:list){
            // zoznam hran
            for (String str:e.toIdList) {
                for (element el:list) {
                    if (str.equals(el.ID)) {
                        //ak ide hrana z place do place, pridam transition medzi ne
                        if (placesList.contains(e.name) && placesList.contains(el.name)) {
                            String trans = "t" + counter++;
                            transitionsList.add(trans);
                            
                            Transition transition = new Transition();
                            transition.getLabel().setText(trans);
                            petriNet.addTransition(transition);
                            
                            arcsList.add(new arci(e.name, trans));
                            arcsList.add(new arci(trans, el.name));
                            
                         for(Place place : petriNet.getPlaces()){
                           if(place.getLabel().getText().equals(e.name)){
                            Arc arc = new Arc();
                            arc.setSource(place);
                            arc.setDestination(transition);
                            petriNet.addArc(arc);
                            }
                       }
                        
                        
                        for(Place place : petriNet.getPlaces()){
                           if(place.getLabel().getText().equals(el.name)){
                            Arc arc = new Arc();
                            arc.setSource(transition);
                            arc.setDestination(place);
                            petriNet.addArc(arc);
                            }
                       }
                            
                        }
                        //ak ide hrana z transition do transition, pridem place medzi ne
                        else if (transitionsList.contains(e.name) && transitionsList.contains(el.name)) {
                            String place = "p" + counter++;
                            placesList.add(place);
                            Place placen = new Place();
                            placen.getLabel().setText(place);
                            petriNet.addPlace(placen);
                            arcsList.add(new arci(e.name, place));
                            arcsList.add(new arci(place, el.name));
                            
                         for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(e.name)){
                               Arc arc = new Arc();
                               arc.setSource(tran);
                               arc.setDestination(placen);
                               petriNet.addArc(arc);
                               }
                        }
                        
                        for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(el.name)){
                               Arc arc = new Arc();
                               arc.setSource(placen);
                               arc.setDestination(tran);
                               petriNet.addArc(arc);
                               }
                        }
                            
                        }
                        else {
                            arcsList.add(new arci(e.name, el.name));
                            Transition trann = new Transition();
                            Place placen = new Place();
                            boolean placetotran = false;
                            
                            for(Transition tran : petriNet.getTransitions()){
                            if(tran.getLabel().getText().equals(e.name)){
                                trann = tran;
                            }
                            if(tran.getLabel().getText().equals(el.name)){
                                trann = tran;
                            }
                        }
                        for(Place place : petriNet.getPlaces()){
                            if(place.getLabel().getText().equals(e.name)){
                                placen = place;
                                placetotran = true;
                            }
                            if(place.getLabel().getText().equals(el.name)){
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
                }
            }
        }        
    }
    
    
}
