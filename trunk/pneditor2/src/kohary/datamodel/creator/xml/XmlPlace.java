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
public class XmlPlace {
    
    @XmlElement(name="role")
    public String xrole;
    
    @XmlElement(name="transition")
    public String xtransition;

}
