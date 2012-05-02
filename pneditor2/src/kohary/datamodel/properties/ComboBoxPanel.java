/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import kohary.datamodel.actions.DeleteComboBoxItemAction;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.dapi.DComboBox;

/**
 *
 * @author Godric
 */
public class ComboBoxPanel extends JPanel {

    private DComboBox comboBox;

    public ComboBoxPanel(DComboBox comboBox) {
        this.comboBox = comboBox;
        setLayout(new GridBagLayout());
        setUpPanel();
        

    }

    private void setUpPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.gridy = 0;
        c.gridx = 0;
         add(new JLabel("Item"),c);
         c.gridx = 1;
            add(new JLabel("Value"),c);
            c.gridx = 2;
            add(new JLabel(),c);
            
        int rowNumber=1;
        
        for(ComboBoxItem item: comboBox.getItems()){
            c.gridx = 0;
            add(new JLabel(item.getItem()),c);
              c.gridx = 1;
            add(new JLabel(item.getValue()),c);
               c.gridx = 2;
            add(new JToggleButton(new DeleteComboBoxItemAction(this, item)),c);
            c.gridy=rowNumber;
        }
    }

    public DComboBox getComboBox() {
        return comboBox;
    }
    
    
}
