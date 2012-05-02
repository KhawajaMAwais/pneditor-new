/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.variables.VariableLine;
import kohary.datamodel.variables.VariablePanel;


/**
 *
 * @author Godric
 */
public class CancelVariableAction extends Action{
    private VariablePanel atbPanel;
    private VariableLine atbLine;
    
    public CancelVariableAction(VariableLine atbLine,VariablePanel atbPanel) {
        this.atbLine=atbLine;
        this.atbPanel=atbPanel;
                 String name = "Add image";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("delete.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);    

    }

    public void actionPerformed(ActionEvent e) {
     
        int index = atbPanel.getComponentZOrder(atbLine);       
        atbPanel.remove(atbLine);
        if(index>=1)
        atbPanel.remove(index-1);
        atbPanel.getVariables().remove(atbLine.getAttribute());
        atbPanel.revalidate();
        atbPanel.repaint();
        
    }

}
