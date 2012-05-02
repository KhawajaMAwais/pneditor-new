/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;

import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Godric
 */
public class VariableEditor extends JPanel {

    private JScrollPane scrollPane;
    private VariableBox atb;
    private VariablePanel atPanel;
    private VariableCreatorFrame atCrFrame;

 

    public VariableEditor(VariableCreatorFrame atCrFrame) {
        this.atCrFrame=atCrFrame;
       // this.attributes=attributes;
        setLayout(new BorderLayout());       

        
        atPanel = new VariablePanel(this);
        
        atb = new VariableBox(atPanel,atCrFrame);
        scrollPane = new JScrollPane(atPanel);
        

        add(scrollPane, BorderLayout.CENTER);
        add(atb, BorderLayout.SOUTH);

    }

    public VariableCreatorFrame getMfdModel() {
        return atCrFrame;
    }

    public VariablePanel getAtPanel() {
        return atPanel;
    }

    public void setAtPanel(VariablePanel atp) {
        this.atPanel = atp;
    }





}
