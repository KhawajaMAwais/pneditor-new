/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.home;

import kohary.datamodel.creator.util.DataModels;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author Godric
 */
public class CanvasPanel extends JPanel {
    private BoxLayout layOut;
    private DataModelPositionPanel positionPanel;
   // private ListEditorModel editorModel;
    private DataModelSelectPanel selectPanel;
    private PetriNet petriNet;
    private DataModels dataModels;
    

    public CanvasPanel(PetriNet petriNet) {
        this.petriNet=petriNet;
        dataModels = new DataModels();

        layOut = new BoxLayout(this, BoxLayout.Y_AXIS);
        setBackground(Color.red);
        setLayout(layOut);
       //setPreferredSize(new Dimension(50,50));
        
       // editorModel = new ListEditorModel(dataModels);
        selectPanel = new DataModelSelectPanel(dataModels,petriNet);
        positionPanel = new DataModelPositionPanel(selectPanel.getListModel(),petriNet);
       
        
        add(positionPanel);
        add(selectPanel);
        revalidate();
        setVisible(true);
        
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public DataModels getDataModels() {
        return dataModels;
    }

    public DataModelSelectPanel getSelectPanel() {
        return selectPanel;
    }

    public void setSelectPanel(DataModelSelectPanel selectPanel) {
        this.selectPanel = selectPanel;
    }

    public DataModelPositionPanel getPlacePanel() {
        return positionPanel;
    }


}
