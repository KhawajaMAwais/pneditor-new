/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Godric
 */
@XmlRootElement(name="DataModel")
public class XmlDocument {
    @XmlElementWrapper(name="datamodels")
    @XmlElement(name="datamodel")
    public List<XmlDataModel> datamodels = new ArrayList<XmlDataModel>();


    

}
