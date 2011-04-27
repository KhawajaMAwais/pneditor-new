/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.xml;

import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class ExportSepare {
   // private XmlDocument xmlDocument = new XmlDocument();
   // private List<DataModel> elements = new ArrayList<DataModel>();
    private XmlDataModelDocument xmlDataDoc = new XmlDataModelDocument();
    private DataModels dataModels;
    private File file,direct;


     public ExportSepare(DataModels dataModels,File direct) throws JAXBException, FileNotFoundException, IOException {
       // this.elements=elements;
        this.dataModels=dataModels;
        this.direct=direct;

        convertClassToXml();
       // Save();

    }
    public void Save() throws JAXBException, FileNotFoundException, IOException {
        
        JAXBContext ctx = JAXBContext.newInstance(XmlDataModelDocument.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty("jaxb.formatted.output", true);
        FileOutputStream fos = new FileOutputStream(file);
        m.marshal(xmlDataDoc, fos);
        fos.close();


    }

    private void convertClassToXml() {
        for (DataModel dataModel : dataModels) {
            this.xmlDataDoc.dataModel = getXmlDataModel(dataModel);
            file = new File(direct.getAbsolutePath()+'/'+dataModel.name + ".xml");
            try {
                //xmlDocument.datamodels.add(getXmlDataModel(dataModel));
                
                Save();
            } catch (JAXBException ex) {
                Logger.getLogger(ExportSepare.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExportSepare.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ExportSepare.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private XmlDataModel getXmlDataModel(DataModel dataModel) {

        XmlDataModel xmlDataModel = new XmlDataModel();
        //////////////////////////////////////////////
        xmlDataModel.name=dataModel.getName();
        xmlDataModel.id=dataModel.getID();

        for(RoleDefinitionProperty role :dataModel.position.keySet()) {
            for(Transition transition:dataModel.position.get(role)) {
                XmlPlace place = new XmlPlace();
                place.xrole=role.toString();
                place.xtransition=transition.toString();
                xmlDataModel.positions.add(place);

            }

        }

        for(Attribute attribute :dataModel.getAttributes()) {

            XmlAttribute xmlAttribute = new XmlAttribute();

            xmlAttribute.xlabel=attribute.getLabel();
            xmlAttribute.xtype=attribute.getType();
            xmlAttribute.xinterpret=attribute.getInterpreter();

            for(String transition : attribute.getRights().keySet()) {
                XmlRight xmlRight = new XmlRight();
                xmlRight.xtransition=transition;
                xmlRight.xright=attribute.getRights().get(transition);
                xmlAttribute.rights.add(xmlRight);
            }
            xmlDataModel.attributes.add(xmlAttribute);

        }

        return xmlDataModel;

    }

}
