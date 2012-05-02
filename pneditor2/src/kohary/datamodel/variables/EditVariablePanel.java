/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Godric
 */
public class EditVariablePanel extends JPanel{

    private GridLayout gridLayOut;
    private JLabel label, type, interpreterLabel;
    private JTextField labelField;
    private JComboBox types;
    
    private final String[] chooses = {"Integer","String","Double","Date","Boolean"};
    private VariableLine eatbLine;
   

    public EditVariablePanel(VariableLine eatbLine) {
        this.eatbLine=eatbLine;
  
        
        gridLayOut = new GridLayout(4, 2);
        setLayout(gridLayOut);

        label = new JLabel("Label:");
        type = new JLabel("Type:");
        interpreterLabel = new JLabel("DataForm:");

        labelField = new JTextField(10);
        types = new JComboBox(chooses);
        
        


        add(label);
        add(labelField);
        add(type);
        add(types);
        add(interpreterLabel);
       
        setVisible(true);

    }

    public JTextField getLabelField() {
        return labelField;
    }

    public void setLabelField(JTextField labelField) {
        this.labelField = labelField;
    }

    public JComboBox getTypes() {
        return types;
    }

    public void setTypes(JComboBox types) {
        this.types = types;
    }







}
