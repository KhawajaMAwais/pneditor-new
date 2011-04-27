/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

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
public class EditAtributePanel extends JPanel implements ActionListener{

    private GridLayout gridLayOut;
    private JLabel label, type, interpreterLabel;
    private JTextField labelField;
    private JComboBox types;
    private DataInterpreterBox interpreter;
    
    
    private final String[] chooses = {"Integer","String","Double","Date","Boolean"};
    private AttributeLine eatbLine;
   

    public EditAtributePanel(AttributeLine eatbLine) {
        this.eatbLine=eatbLine;
  
        
        gridLayOut = new GridLayout(4, 2);
        setLayout(gridLayOut);

        label = new JLabel("Label:");
        type = new JLabel("Type:");
        interpreterLabel = new JLabel("DataForm:");

        labelField = new JTextField(10);
        types = new JComboBox(chooses);
        types.addActionListener(this);
        
        interpreter = new DataInterpreterBox();
        


        add(label);
        add(labelField);
        add(type);
        add(types);
        add(interpreterLabel);
        add(interpreter);
       
        setVisible(true);

    }

    public JTextField getLabelField() {
        return labelField;
    }

    public DataInterpreterBox getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(DataInterpreterBox interpreter) {
        this.interpreter = interpreter;
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

    public void actionPerformed(ActionEvent e) {
        getInterpreter().setInterpreter((String)getTypes().getSelectedItem());
    }






}
