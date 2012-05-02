/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.actions;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JList;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.variables.VariableBox;
import kohary.datamodel.variables.VariableLine;
import kohary.datamodel.variables.VariablePanel;
import kohary.datamodel.variables.Dvariable;

/**
 *
 * @author Godric
 */
public class AddVariableAction extends Action{
    private Dvariable attribute;
    private VariablePanel atbPanel;    
    private VariableBox atbBox;
    private JList list;
    private Object[] data;
    public AddVariableAction(VariablePanel atbPanel,VariableBox atbBox)
    {
        this.atbBox=atbBox;
        this.atbPanel=atbPanel; 
              String name = "Add image";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);  


    }
    public void actionPerformed(ActionEvent e) {

        attribute = new Dvariable();
        attribute.setLabel(atbBox.getLabel());
        attribute.setType(atbBox.getTypes());
     
        
        VariableLine line = new VariableLine(attribute,atbPanel);

        atbPanel.getAttributeLines().add(line);
        atbPanel.add(line);
        atbPanel.add(Box.createRigidArea(new Dimension(5,10)));
        atbPanel.getVariables().add(attribute);
        atbPanel.revalidate();
        atbPanel.repaint();
      

    }

}
