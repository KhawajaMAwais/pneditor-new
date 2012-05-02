/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Godric
 */
public class EditVariableFrame extends JFrame {

    private EditVariablePanel editPanel;
    private EditButtonPanel editButtonPanel;
    private BorderLayout layOut;
    private VariableLine atbLine;
    private Dvariable attribute;
  
    
    public EditVariableFrame(VariableLine atbLine,Dvariable attribute) {
        this.attribute=attribute;
        this.atbLine= atbLine;

        setTitle("Edit Attribute");
        setSize(300, 150);
        layOut = new BorderLayout();
        setLayout(layOut);
        
        editPanel = new EditVariablePanel(atbLine);
        editButtonPanel = new EditButtonPanel(this, attribute);

       
        add(editPanel,BorderLayout.NORTH);
        add(editButtonPanel,BorderLayout.SOUTH);
       
        
        setVisible(true);


    }


    public VariableLine getAtbLine() {
        return atbLine;
    }

    public void setAtbLine(VariableLine atbLine) {
        this.atbLine = atbLine;
    }

    public EditVariablePanel getEditPanel() {
        return editPanel;
    }

    public void setEditPanel(EditVariablePanel editPanel) {
        this.editPanel = editPanel;
    }
}
