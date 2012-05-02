/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.variables.Dvariable;
import kohary.datamodel.variables.VariableCreatorFrame;

/**
 *
 * @author Godric
 */
public class SelectVariableToInputAction extends Action{

    public SelectVariableToInputAction() {
                     String name = "Select variable";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);    
    }
    
    

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
        List<Dvariable> avaibleVariables = DatamodelCreator.getInstance().getDocument().getVariables();
       
       if(avaibleVariables.isEmpty()){
             new VariableCreatorFrame();
       }else{
       
      Dvariable s = (Dvariable)JOptionPane.showInputDialog(
                    mainFrame,
                    "Variable in petriflow:\n",
                    "Variable",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    avaibleVariables.toArray(),
                    attribute.getInput().getVariable());
      
      attribute.getInput().setVariable(s);
      DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
       }
    }
    
}
