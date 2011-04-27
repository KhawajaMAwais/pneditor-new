/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author Godric
 */
public class DataModelSelectPanel extends JPanel {

    private DataModels dataModels;
    private ListEditorModel listModel;
    private ShowModelPanel showModelPanel;
    private JSplitPane splitPane;
    private PetriNet petriNet;
    
    public DataModelSelectPanel(DataModels dataModels,PetriNet petriNet) {
        this.dataModels = dataModels;
        this.petriNet=petriNet;
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        listModel = new ListEditorModel(dataModels, this);
        showModelPanel = new ShowModelPanel(listModel,petriNet);
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        splitPane.setDividerSize(8);
        splitPane.setOneTouchExpandable(true);
        //  Temp temp = new Temp();
        splitPane.setLeftComponent(listModel);
        splitPane.setRightComponent(showModelPanel);
       // add(listModel, BorderLayout.WEST);
        //add(temp,BorderLayout.EAST);
       // add(showModelPanel, BorderLayout.EAST);
        add(splitPane);
    }

    public ListEditorModel getListModel() {
        return listModel;
    }

    public ShowModelPanel getShowModelPanel() {
        return showModelPanel;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }
    
}
