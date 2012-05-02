/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.Datamodels;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class DataModelList extends JList implements MouseMotionListener, ListSelectionListener {

    public DataModelList() {
        super();
        addMouseMotionListener(this);
        
    }

    public DataModelList(Datamodels datamodels) {
        super();
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setSelectedIndex(0);
        addListSelectionListener(this);
        setModel(datamodels);
        addMouseMotionListener(this);
       
    }

    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {

        DataModelList list = (DataModelList) e.getSource();
        ListModel model = (ListModel) list.getModel();
        int index = list.locationToIndex(e.getPoint());
        if (index > -1) {
            list.setToolTipText(null);
            DataModel dataModel = (DataModel) model.getElementAt(index);
            list.setToolTipText(dataModel.toString());

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if(e != null)
        if(e.getValueIsAdjusting())
        DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel((DataModel) this.getSelectedValue());
    }
}
