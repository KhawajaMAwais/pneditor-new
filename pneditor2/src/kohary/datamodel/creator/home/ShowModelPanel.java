/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.util.DataModel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author Godric
 */
public class ShowModelPanel extends JPanel {

    private DataModel dataModel;
    private ShowAttributePanel showAtPanel;
    private ShowAttributeHeaderPanel header;
    private ListEditorModel listEditor;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;
    private ShowPositionPanel place;
    private PetriNet petriNet;

    public ShowModelPanel(ListEditorModel listEditor,PetriNet petriNet) {
        this.listEditor = listEditor;
        this.petriNet=petriNet;
        
        setLayout(new BorderLayout());

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
        splitPane.setDividerSize(8);
        splitPane.setDividerLocation(250);
        
        
        header = new ShowAttributeHeaderPanel();
        showAtPanel = new ShowAttributePanel((DataModel) listEditor.getList().getSelectedValue());
        scrollPane = new JScrollPane(showAtPanel);
        place = new ShowPositionPanel(listEditor,petriNet);


        add(header,BorderLayout.NORTH);
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(place);
        
        add(splitPane, BorderLayout.CENTER);
        setVisible(true);

    }

    public ShowPositionPanel getPlace() {
        return place;
    }

    public ShowAttributePanel getShowAtPanel() {
        return showAtPanel;
    }

    public void setShowAtPanel(ShowAttributePanel showAtPanel) {
        this.showAtPanel = showAtPanel;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
}
