/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Godric
 */
public class EditAttributeFrame extends JFrame {

    private EditAtributePanel editPanel;
    private EditButtonPanel editButtonPanel;
    private BorderLayout layOut;
    private AttributeLine atbLine;
    private Attribute attribute;
  
    
    public EditAttributeFrame(AttributeLine atbLine,Attribute attribute) {
        this.attribute=attribute;
        this.atbLine= atbLine;

        setTitle("Edit Attribute");
        setSize(300, 150);
        layOut = new BorderLayout();
        setLayout(layOut);
        
        editPanel = new EditAtributePanel(atbLine);
        editButtonPanel = new EditButtonPanel(this, attribute);

       
        add(editPanel,BorderLayout.NORTH);
        add(editButtonPanel,BorderLayout.SOUTH);
       
        
        setVisible(true);


    }


    public AttributeLine getAtbLine() {
        return atbLine;
    }

    public void setAtbLine(AttributeLine atbLine) {
        this.atbLine = atbLine;
    }

    public EditAtributePanel getEditPanel() {
        return editPanel;
    }

    public void setEditPanel(EditAtributePanel editPanel) {
        this.editPanel = editPanel;
    }
}
