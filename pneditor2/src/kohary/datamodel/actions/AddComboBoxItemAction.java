/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.properties.PropertiesDialog;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class AddComboBoxItemAction extends Action{
    
    
    PropertiesDialog pd;
    DComboBox comboBox;

    public AddComboBoxItemAction( DComboBox comboBox, PropertiesDialog pd) {
     this.pd = pd;
        this.comboBox = comboBox;
        String name = "Add Item";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
        putValue(SHORT_DESCRIPTION, "Add Item");

    }
    
    

    public void actionPerformed(ActionEvent e) {
       comboBox.getItems().add(new ComboBoxItem(pd.getItemField().getText(), pd.getValueField().getText()));
    }
    
}
