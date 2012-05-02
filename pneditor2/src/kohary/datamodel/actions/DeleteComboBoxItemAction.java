/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.properties.ComboBoxPanel;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class DeleteComboBoxItemAction extends Action {

    ComboBoxItem item;
    ComboBoxPanel comboboxPanel;

    public DeleteComboBoxItemAction( ComboBoxPanel comboboxPanel, ComboBoxItem item) {
        this.item = item;
        this.comboboxPanel = comboboxPanel;
        String name = "Delete";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("delete16.gif"));
        putValue(SHORT_DESCRIPTION, "");

    }

    public void actionPerformed(ActionEvent e) {
        comboboxPanel.getComboBox().getItems().remove(item);
        comboboxPanel.repaint();
        comboboxPanel.revalidate();
    }
}
