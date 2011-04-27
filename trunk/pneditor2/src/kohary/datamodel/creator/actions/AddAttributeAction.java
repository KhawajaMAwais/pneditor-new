/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.attribute.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JList;

/**
 *
 * @author Godric
 */
public class AddAttributeAction implements ActionListener{
    private Attribute attribute;
    private AttributePanel atbPanel;    
    private AttributeBox atbBox;
    private JList list;
    private Object[] data;
    public AddAttributeAction(AttributePanel atbPanel,AttributeBox atbBox)
    {
        this.atbBox=atbBox;
        this.atbPanel=atbPanel; 


    }
    public void actionPerformed(ActionEvent e) {

        attribute = new Attribute();
        attribute.setLabel(atbBox.getLabel());
        attribute.setType(atbBox.getTypes());
        attribute.setInterpreter(atbBox.getInterpreter());
        
        AttributeLine line = new AttributeLine(attribute,atbPanel);

        atbPanel.getAttributeLines().add(line);
        atbPanel.add(line);
        atbPanel.add(Box.createRigidArea(new Dimension(5,10)));
        atbPanel.getAttributes().add(attribute);
        atbPanel.revalidate();
        atbPanel.repaint();
      

    }

}
