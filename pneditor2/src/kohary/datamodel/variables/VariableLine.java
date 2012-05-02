/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import kohary.datamodel.actions.CancelVariableAction;
import kohary.datamodel.actions.EditVariableAction;
import net.matmas.pnapi.Transition;

/**
 *
 * @author Godric
 */
public class VariableLine extends JPanel {

    private JTextField name, type;
    private JButton cancel,edit; 
    private Dvariable variable;
    private Dimension maxSize;
    private VariablePanel atbPanel;      
    private int index, i;  

    public VariableLine(Dvariable attribute, VariablePanel atbPanel) {
        this.atbPanel = atbPanel;
        this.variable = attribute;
       Color color = new Color(221,236,244);
       setBackground(color);
      
     
           

        maxSize = new Dimension(550,23);
       
        setMaximumSize(maxSize);
        
        GridLayout gridLayOut = new GridLayout(1, 5);
        setLayout(gridLayOut);

        name = new JTextField(10);
        name.setText(attribute.getLabel());
        name.setEditable(false);

        type = new JTextField(10);
        type.setText(attribute.getType());
        type.setEditable(false);

  

        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelVariableAction(this, atbPanel));

        edit = new JButton("Edit");
        edit.addActionListener(new EditVariableAction(this,attribute));

       

        //addRightsButton.setMaximumSize(new Dimension(30, 30));

        add(name);
        add(type);

        add(edit);      
        add(cancel);


        setVisible(true);

    }


   

    public JTextField getLabel() {
        return name;
    }

    public void setLabel(JTextField label) {
        this.name = label;
    }

    public JTextField getType() {
        return type;
    }

    public void setType(JTextField type) {
        this.type = type;
    }

    public Dvariable getAttribute() {
        return variable;
    }

    public void setAttribute(Dvariable attribute) {
        this.variable = attribute;
    }


  
 
}
