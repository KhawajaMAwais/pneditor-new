/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** DOROBIT PRAVA K PRECHODU
 *
 * @author Godric
 */
public class Dvariable implements Serializable{

    private String name, type ;   

    public Dvariable() {
    }

    public Dvariable(String label, String type) {
        this.name = label;
        this.type = type;
      
    }

    public String getLabel() {
        return name;
    }

    public void setLabel(String label) {
        this.name = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{#"+name+"}";
    }
    
    


}
