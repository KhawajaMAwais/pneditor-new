/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Godric
 */
@XmlRootElement(name="Data_models_for_form")
public class XmlDataModelDocument {
    @XmlElement(name="datamodel")
    XmlDataModel dataModel = new XmlDataModel();

}
