/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.properties;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import kohary.datamodel.actions.AddComboBoxItemAction;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class PropertiesDialog extends JDialog {

    Element element;
    DataModel dataModel;

    public PropertiesDialog(DataModel dataModel, Element element) {
        this.dataModel = dataModel;
        this.element = element;
        setLayout(new BorderLayout());
        setSize(600, 500);
        setUpTabs();
        setVisible(true);
        pack();

    }

    private void setUpTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();

        if (element instanceof DComboBox) {
            tabbedPane.addTab("Combobox Items", createComboBoxValuesPanel());
        }
        add(tabbedPane, BorderLayout.CENTER);
    }
    //---------------------------------------------
    JTextField itemField = new JTextField(15);
    JTextField valueField = new JTextField(15);

    public JTextField getItemField() {
        return itemField;
    }

    public JTextField getValueField() {
        return valueField;
    }

    //-------------------------------------------------
    private JPanel createComboBoxValuesPanel() {


        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());

        ComboBoxPanel comboBoxValuesPanel = new ComboBoxPanel((DComboBox) element);
        box.add(comboBoxValuesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.gridy = 0;
        c.gridx = 0;
        buttonPanel.add(new JLabel("Item:"), c);
        c.gridx = 1;
        buttonPanel.add(itemField, c);
        c.gridy = 1;
        c.gridx = 0;
        buttonPanel.add(new JLabel("Value:"), c);
        c.gridx = 1;
        buttonPanel.add(valueField, c);
        c.gridy = 2;
        buttonPanel.add(new JToggleButton(new AddComboBoxItemAction((DComboBox) element, this)));
        
        box.add(buttonPanel,BorderLayout.SOUTH);
        return box;

    }
}
