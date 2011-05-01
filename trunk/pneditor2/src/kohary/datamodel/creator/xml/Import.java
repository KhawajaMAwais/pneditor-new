/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.xml;

import kohary.datamodel.creator.util.DataModels;
import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.home.CanvasPanel;
import kohary.datamodel.creator.home.ListEditorModel;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.ListModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class Import {

    private XmlDocument xmlDocument;
    // private DataModels dataModels = new DataModels();
    protected DataModels dataModels;
    private File file;
    private CanvasPanel canvas;
    protected PetriNet petriNet;
    protected RoleDefinitionProperty role;
    protected Transition transition;

    public Import(File file, DataModels dataModels, CanvasPanel canvas,PetriNet petriNet) throws FileNotFoundException, IOException {
        this.dataModels = dataModels;
        this.canvas = canvas;
        this.petriNet=petriNet;
        
        load(file);
        setupDatamodels();
        //dataModelss=dataModels;
    }

    public Import(){
        
    }

   

    public void load(File file) throws FileNotFoundException, IOException {
        try {
            JAXBContext ctx = JAXBContext.newInstance(XmlDocument.class);
            Unmarshaller u = ctx.createUnmarshaller();
            FileInputStream fileInputStream = new FileInputStream(file);
            xmlDocument = (XmlDocument) u.unmarshal(fileInputStream);
            fileInputStream.close();
        } catch (JAXBException ex) {
            Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public XmlDocument getXmlDocument(){
        return this.xmlDocument;
    }

    

    public DataModels setupDatamodels() {
        for (XmlDataModel xmlDatamodel : xmlDocument.datamodels) {
            DataModel dataModel = (DataModel) getDataModel(xmlDatamodel);
            dataModels.add(dataModel);
            canvas.getSelectPanel().getListModel().getDataModels().addSpecial(dataModel);
        }
        return dataModels;
    }

    private DataModel getDataModel(XmlDataModel xmlDataModel) {
        DataModel dataModel = new DataModel();
        dataModel.setID(xmlDataModel.id);
        dataModel.setName(xmlDataModel.name);
        dataModel.setAttributes((ListModel<Attribute>) getAttributes(xmlDataModel));
        dataModel.setPosition((Map<RoleDefinitionProperty, Set<Transition>>) getPosition(xmlDataModel));

        return dataModel;

    }

    private ListModel<Attribute> getAttributes(XmlDataModel xmlDataModel) {
        ListModel<Attribute> attributes = new ListModel<Attribute>() {

            @Override
            public void addNew(ListEditorModel list) {
            }

            @Override
            public void addNew() {
            }
        };
        for (XmlAttribute xmlAttribute : xmlDataModel.attributes) {
            Map<String, String> rights = new HashMap<String, String>();
            Attribute attribute = new Attribute();

            attribute.setInterpreter(xmlAttribute.xinterpret);
            attribute.setLabel(xmlAttribute.xlabel);
            attribute.setType(xmlAttribute.xtype);
            for (XmlRight xmlRight : xmlAttribute.rights) {
                rights.put(xmlRight.xtransition, xmlRight.xright);
            }
            attribute.setRights(rights);
            attributes.add(attribute);
        }

        return attributes;
    }

    private Map<RoleDefinitionProperty, Set<Transition>> getPosition(XmlDataModel xmlDataModel) {
        Map<RoleDefinitionProperty, Set<Transition>> position = new HashMap<RoleDefinitionProperty, Set<Transition>>();
        for (XmlPlace xmlPlace : xmlDataModel.positions) {
            Set<Transition> transitions = new HashSet<Transition>();
            role=null;
            transition=null;
            for(RoleDefinitionProperty roleTemp:petriNet.getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {
                if(roleTemp.toString().equals((String)xmlPlace.xrole)) {
                    role=roleTemp;
                }
            }
            for(Transition transitionTemp :petriNet.getTransitions()) {
                if(transitionTemp.toString().equals((String)xmlPlace.xtransition))
                    transition=transitionTemp;
            }
            if((role==null) || (transition==null)) {
                JOptionPane.showMessageDialog(null, "Nacitanie prebehlo nekorektne!!! Skontrolujte ci je v PNeditore nacitana prislusna siet.");

            }
            transitions.add(transition);
            int index = 0;

            for (RoleDefinitionProperty rolaTemp : position.keySet()) { // zistovanie ci sa rola uz v positions nachadza
            if (rolaTemp.toString().equals(role.toString())) {
            index = 1;

            position.get(rolaTemp).add(transition);

            }
            }
            if (index == 0) {

            position.put(role, transitions);

            }


            }
        
         return position;
    }
}
       

    


