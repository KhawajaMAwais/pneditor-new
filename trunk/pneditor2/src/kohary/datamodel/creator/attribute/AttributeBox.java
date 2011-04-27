/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import kohary.datamodel.creator.actions.AddAttributeAction;
import kohary.datamodel.creator.actions.ConfirmAttributeAction;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Godric
 */
public class AttributeBox extends JPanel implements ActionListener {

    private JTextField label;
    private JComboBox types;
    private DataInterpreterBox dataInterpreter;
    private JButton addButton, confirmButton;
    public AttributePanel atbPanel;
    private AddAttributeAction atbAction;
    private JPanel attributeBox;
    private AttributeCreatorFrame atCrFrame;
    private final String[] chooses = {"Integer", "String", "Double", "Date", "Boolean"};

    public AttributeBox(AttributePanel atbPanel, AttributeCreatorFrame atCrFrame) {
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

        dataInterpreter = new DataInterpreterBox((String)types.getSelectedItem());
        
        addButton = new JButton("ADD");

        confirmButton = new JButton("Confirm");

        types.addActionListener(this);
        addButton.addActionListener(new AddAttributeAction(atbPanel, this));
        confirmButton.addActionListener(new ConfirmAttributeAction(atbPanel, this));

        attributeBox.add(label);
        attributeBox.add(types);
        attributeBox.add(dataInterpreter);
        attributeBox.add(addButton);
        attributeBox.add(confirmButton);

        add(attributeBox);
        //setBackground(Color.red);
        setVisible(true);
    }

    public AttributeCreatorFrame getAtCrFrame() {
        return atCrFrame;
    }

    public String getLabel() {
        return label.getText();
    }

    public String getTypes() {
        return (String) types.getSelectedItem();
    }

    public String getInterpreter() {
        return (String) dataInterpreter.getSelectedItem();
    }

    public DataInterpreterBox getDataInterpreter() {
        return dataInterpreter;
    }

    public void actionPerformed(ActionEvent e) {
        getDataInterpreter().setInterpreter(getTypes());
    }
}
