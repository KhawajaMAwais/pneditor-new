/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.attribute.Attribute;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Godric
 */
public class ShowAttributeLine extends JPanel {

    private Attribute attribute;
    private JLabel label, type, right;
    private JTextField textField;
    private JTextArea textArea;
    private JRadioButton radioButton1,radioButton2;
    private JCheckBox checkbox;

    public ShowAttributeLine(Attribute attribute) {
        this.attribute = attribute;
        setLayout(new GridLayout(1, 3));
        setMaximumSize(new Dimension(275, 20));



        label = new JLabel();
        label.setToolTipText("Label atributu");
        label.setText(attribute.getLabel());

        type = new JLabel();
        type.setToolTipText("Datovy typ atributu");
        type.setText(attribute.getType());


        right = new JLabel();
        right.setToolTipText("Prava");

        add(label);
        add(type);
        if (attribute.getInterpreter().equals("TextField")) {
            
            textField = new JTextField();
            textField.setSize(80, 20);
            add(textField);     

        }

        if (attribute.getInterpreter().equals("TextArea")) {
            setMaximumSize(new Dimension(275, 50));
            textArea = new JTextArea();
            textArea.setSize(100, 100);
            add(textArea);
        }
        
        if(attribute.getInterpreter().equals("RadioButtons")) {
            radioButton1 = new JRadioButton();
            radioButton2 = new JRadioButton();
            radioButton1.setSelected(true);
            ButtonGroup group = new ButtonGroup();
            group.add(radioButton1);
            group.add(radioButton2);
            add(radioButton1);
            add(radioButton2);            
        }

        if(attribute.getInterpreter().equals("CheckBox")) {
            checkbox = new JCheckBox();
            add(checkbox);
            
        }








        //add(dd);

        // line.createBlackLineBorder();

        setVisible(true);

    }


}
