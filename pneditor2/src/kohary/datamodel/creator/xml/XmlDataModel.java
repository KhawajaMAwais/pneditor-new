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
public class XmlDataModel {
    @XmlElement(name="name")
    public String name;

    @XmlElement(name="id")
    public int id;

    @XmlElementWrapper(name="positions")
    @XmlElement(name="position")
    List<XmlPlace> positions = new LinkedList<XmlPlace>();

    @XmlElementWrapper(name="attributes")
    @XmlElement(name="attribute")
    List<XmlAttribute> attributes = new LinkedList<XmlAttribute>();

}
