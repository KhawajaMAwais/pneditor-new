/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import kohary.datamodel.creator.util.ListModel;
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
public class AttributeEditor extends JPanel {

    private JScrollPane scrollPane;
    private ListModel<Attribute> attributes;
    private AttributeBox atb;
    private AttributePanel atPanel;
    private AttributeCreatorFrame atCrFrame;

 

    public AttributeEditor(AttributeCreatorFrame atCrFrame) {
        this.atCrFrame=atCrFrame;
       // this.attributes=attributes;
        setLayout(new BorderLayout());       

        
        atPanel = new AttributePanel(this);
        
        atb = new AttributeBox(atPanel,atCrFrame);
        scrollPane = new JScrollPane(atPanel);
        

        add(scrollPane, BorderLayout.CENTER);
        add(atb, BorderLayout.SOUTH);

    }

    public AttributeCreatorFrame getMfdModel() {
        return atCrFrame;
    }

    public AttributePanel getAtPanel() {
        return atPanel;
    }

    public void setAtPanel(AttributePanel atp) {
        this.atPanel = atp;
    }





}
