/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Godric
 */
public class XmlRight {

    @XmlElement(name="transition")
    public String xtransition;

    @XmlElement(name="local_right")
    public String xright;
}
