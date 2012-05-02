/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;


import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import kohary.datamodel.actions.VariableCancelAction;
import kohary.datamodel.actions.VariableConfirmAction;

/**
 *
 * @author Godric
 */
public class EditButtonPanel extends JPanel {

    private JButton cancel, confirm;
    private Dvariable attribute;
    private EditVariableFrame parent;
    private GridLayout gridLayOut;
    
    public EditButtonPanel(EditVariableFrame parent,Dvariable attribute) {
        this.attribute = attribute;
        this.parent = parent;
        cancel = new JButton("Cancel");
        confirm = new JButton("Confirm");
        gridLayOut = new GridLayout(1,2);
        setLayout(gridLayOut);


        cancel.addActionListener(new VariableCancelAction(parent));
        cancel.setMnemonic(KeyEvent.VK_C);

        confirm.addActionListener(new VariableConfirmAction(parent,attribute));

        add(cancel);
        add(confirm);
        setVisible(true);


    }
}
