/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Resolution;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.util.ListModel;

/**
 *
 * @author Godric
 */
public class Datamodels extends ListModel<DataModel> implements Serializable{

    int index = 1;
    String str = null;

    @Override
    public void addNew() {

        str = JOptionPane.showInputDialog(null, "Enter Datamodel name : ",
                "Datamodel name", 1);



        if (str != null) {

            if (containSameName()) {
                JOptionPane.showMessageDialog(null, "This name is already used");
            } else {
                Resolution resolution = (Resolution) JOptionPane.showInputDialog(
                        null,
                        "Select resolution for page of datamodel " + str,
                        "Resolution",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        DatamodelCreator.getInstance().getDefaultSettingsManager().getResolutions().getResolutions().toArray(),
                        1);

                DataModel dataModel = new DataModel();


                if (str.equals("")) {
                    dataModel.setId(index);
                    dataModel.setName("DataModel" + index);

                } else {
                    dataModel.setName(str);
                    dataModel.setId(index);

                }
                index++;

                dataModel.getPage().setResolution(resolution);
                elements.add(dataModel);
                fireIntervalAdded(this, elements.indexOf(dataModel), elements.indexOf(dataModel));
                DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel(dataModel);

            }
        }
        checkEmpty();
    }

    private boolean containSameName() {
        for (DataModel dataModel : elements) {
            if (dataModel.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void removeSelected() {
        this.deleteComponent(DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel());
        if (!elements.isEmpty()) {
            DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel(elements.get(elements.size() - 1));
        }
        if (elements.isEmpty()) {
            DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel(new DataModel());
            DatamodelCreator.getInstance().getSelectionManager().setSelect_selection();
        }
        checkEmpty();

    }

    public void checkEmpty() {
        MainFrame mainframe = DatamodelCreator.getInstance().getMainFrame();
        if (elements.isEmpty()) {
            mainframe.disableToolBar();

        } else {
            mainframe.enableToolBar();
        }

    }
    //---------------------------------------------------------------
    //public DataModel getActiveDataModel(){
    // }
}
