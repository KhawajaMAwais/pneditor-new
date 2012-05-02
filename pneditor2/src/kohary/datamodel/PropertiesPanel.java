/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DataType;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class PropertiesPanel extends JPanel {
   

    public PropertiesPanel() {
        super(new BorderLayout());
        setUpTabbedPane();

    }
    private JTabbedPane tabbedPane = new JTabbedPane();

    private void setUpTabbedPane() {
        tabbedPane.addTab("Data type",  createDataTypeBox());
        tabbedPane.addTab("Data type",  createDataTypeBox());
        add(tabbedPane);
    }

    private JComboBox dataTypeBox;
    private JPanel createDataTypeBox() {
         
     Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
        
        
        JPanel pane = new JPanel();
        JLabel label = new JLabel("Set datatype:");
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        
        
         dataTypeBox = new JComboBox(DataType.values());
        dataTypeBox.setSelectedItem(attribute.getInput().getDataType());
        box.add(dataTypeBox);
        
        pane.add(box);
        return pane;
         

    }
}
