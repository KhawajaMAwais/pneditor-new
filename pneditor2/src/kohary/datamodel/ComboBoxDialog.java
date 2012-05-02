/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.properties.Table;


/**
 *
 * @author Godric
 */
public class ComboBoxDialog extends JDialog implements ActionListener {

    private Table table;
    private DComboBox comboBox;
    private JTextField itemName,value;   
    private JLabel itemLabel,valueLabel;
    private JButton addItem = new JButton("Add Item");

    public ComboBoxDialog(JFrame parent,DComboBox comboBox) {    
        this.comboBox = comboBox;
        setTitle("Combobox editor");
        table = new Table(comboBox);
        itemLabel = new JLabel("Item:");
        valueLabel = new JLabel("Value:");
        
        itemName = new JTextField();
        value = new JTextField();
        
        this.getContentPane().setLayout(new GridBagLayout());
        this.setSize(600, 500);
        this.setLocationRelativeTo(getParent());
        
        	GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.add(new JScrollPane(table), c);
                c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(itemLabel, c);
                c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(itemName, c);
                c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(valueLabel, c);
                c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(value, c);
                this.setVisible(true);
    }
    
    

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
