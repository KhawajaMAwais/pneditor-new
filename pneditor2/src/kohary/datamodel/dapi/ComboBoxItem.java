/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

/**
 *
 * @author Godric
 */
public class ComboBoxItem {
    
    String item;
    String value;

    public ComboBoxItem(String item, String value) {
        this.item = item;
        this.value = value;
    }
    public ComboBoxItem(String item) {
        this.item = item;
        this.value = item;
    }
    
    

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

   
    
    
}
