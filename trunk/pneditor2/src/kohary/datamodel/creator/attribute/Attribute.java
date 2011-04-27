/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import java.util.HashMap;
import java.util.Map;

/** DOROBIT PRAVA K PRECHODU
 *
 * @author Godric
 */
public class Attribute {

    private String label, type , interpreter;
    private Map<String,String> rights = new HashMap<String,String>();

    public Attribute() {
    }

    public Attribute(String label, String type, String interpreter) {
        this.label = label;
        this.type = type;
        this.interpreter=interpreter;
    }

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getRights() {
        return rights;
    }

    public void setRights(Map<String, String> rights) {
        this.rights = rights;
    }

    public void setType(String type) {
        this.type = type;
    }


}
