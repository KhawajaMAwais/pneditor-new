/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import kohary.datamodel.creator.actions.AddRightsAction;
import kohary.datamodel.creator.actions.CancelAttributeAction;
import kohary.datamodel.creator.actions.EditAttributeAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.actions.Action;

/**
 *
 * @author Godric
 */
public class AttributeLine extends JPanel {

    private JTextField label, type,interpreter;
    private JButton cancel,edit;
    private JToggleButton addRightsButton;
    private Attribute attribute;
    private Dimension maxSize;
    private AttributePanel atbPanel;
    private List<AttributeRightsLine> rightsLines;
    protected List<Transition> avaibleTransition;
    protected int maxRightLines;
    private int index, i;
    private Action addRightAction;

    public AttributeLine(Attribute attribute, AttributePanel atbPanel) {
        this.atbPanel = atbPanel;
        this.attribute = attribute;
       Color color = new Color(221,236,244);
       setBackground(color);
        rightsLines = new LinkedList<AttributeRightsLine>();
     
        maxRightLines=atbPanel.getAtbEditor().getMfdModel().getListModel().getDataModelSelectPanel().getPetriNet().getTransitions().size();
      
       addRightAction = new AddRightsAction(this, attribute, atbPanel);
      

        maxSize = new Dimension(550,23);
       
        setMaximumSize(maxSize);
        
        GridLayout gridLayOut = new GridLayout(1, 5);
        setLayout(gridLayOut);

        label = new JTextField(10);
        label.setText(attribute.getLabel());
        label.setEditable(false);

        type = new JTextField(10);
        type.setText(attribute.getType());
        type.setEditable(false);

        interpreter = new JTextField(10);
        interpreter.setText(attribute.getInterpreter());
        interpreter.setEditable(false);

        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelAttributeAction(this, atbPanel));

        edit = new JButton("Edit");
        edit.addActionListener(new EditAttributeAction(this,attribute));

        addRightsButton = new JToggleButton(addRightAction);

        //addRightsButton.setMaximumSize(new Dimension(30, 30));

        add(label);
        add(type);
        add(interpreter);
        add(edit);      
        add(cancel);
        add(addRightsButton);

        setVisible(true);

    }

   
    public int getMaxRightLines() {
        return maxRightLines;
    }

    public void setAvaibleTransitionBegin() {
        avaibleTransition.addAll(atbPanel.getAtbEditor().getMfdModel().getListModel().getDataModelSelectPanel().getPetriNet().getTransitions());
    }

    public List<Transition> getAvaibleTransition() {
        return avaibleTransition;
    }

    public List<AttributeRightsLine> getRightsLines() {
        return rightsLines;
    }

    public JTextField getLabel() {
        return label;
    }

    public void setLabel(JTextField label) {
        this.label = label;
    }

    public JTextField getType() {
        return type;
    }

    public void setType(JTextField type) {
        this.type = type;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public JTextField getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(JTextField interpreter) {
        this.interpreter = interpreter;
    }

  
 
}
