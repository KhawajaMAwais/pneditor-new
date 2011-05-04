/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.xml;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author Godric
 */
public class XmlAttribute {

    @XmlElement(name="label")
    public String xlabel;

    @XmlElement(name="type")
    public String xtype;

    @XmlElement(name="data-interpret")
    public String xinterpret;
    
    @XmlElementWrapper(name="rights")
    @XmlElement(name="right")
    public List<XmlRight> rights = new LinkedList<XmlRight>();
    /*@XmlElement(name="right")
    public String xright;*/
    

}
