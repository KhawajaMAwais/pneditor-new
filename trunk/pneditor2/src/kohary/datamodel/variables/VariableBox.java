/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import kohary.datamodel.actions.AddVariableAction;
import kohary.datamodel.actions.ConfirmVariableAction;

/**
 *
 * @author Godric
 */
public class VariableBox extends JPanel {

    private JTextField label;
    private JComboBox types;

    private JButton addButton, confirmButton;
    public VariablePanel atbPanel;
    private AddVariableAction atbAction;
    private JPanel attributeBox;
    private VariableCreatorFrame atCrFrame;
    private final String[] chooses = {"Integer", "String", "Double", "Date", "Boolean"};

    public VariableBox(VariablePanel atbPanel, VariableCreatorFrame atCrFrame) {
        this.atCrFrame = atCrFrame;
        this.atbPanel = atbPanel;

        GridLayout gridLayOut = new GridLayout(1, 3);
        gridLayOut.setHgap(10);
        attributeBox = new JPanel(gridLayOut);
        setLayout(gridLayOut);



        label = new JTextField(10);
        label.setPreferredSize(null);


        types = new JComboBox(chooses);
        types.setSelectedIndex(0);

      
        
        addButton = new JButton("ADD");

        confirmButton = new JButton("Confirm");

        addButton.addActionListener(new AddVariableAction(atbPanel, this));
        confirmButton.addActionListener(new ConfirmVariableAction(atbPanel, this));

        attributeBox.add(label);
        attributeBox.add(types);
       
        attributeBox.add(addButton);
        attributeBox.add(confirmButton);

        add(attributeBox);
        //setBackground(Color.red);
        setVisible(true);
    }

    public VariableCreatorFrame getAtCrFrame() {
        return atCrFrame;
    }

    public String getLabel() {
        return label.getText();
    }

    public String getTypes() {
        return (String) types.getSelectedItem();
    }



}
