/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import kohary.datamodel.creator.actions.EditConfirmAction;
import kohary.datamodel.creator.actions.EditCancelAction;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Godric
 */
public class EditButtonPanel extends JPanel {

    private JButton cancel, confirm;
    private Attribute attribute;
    private EditAttributeFrame parent;
    private GridLayout gridLayOut;
    
    public EditButtonPanel(EditAttributeFrame parent,Attribute attribute) {
        this.attribute = attribute;
        this.parent = parent;
        cancel = new JButton("Cancel");
        confirm = new JButton("Confirm");
        gridLayOut = new GridLayout(1,2);
        setLayout(gridLayOut);


        cancel.addActionListener(new EditCancelAction(parent));
        cancel.setMnemonic(KeyEvent.VK_C);

        confirm.addActionListener(new EditConfirmAction(parent,attribute));

        add(cancel);
        add(confirm);
        setVisible(true);


    }
}
