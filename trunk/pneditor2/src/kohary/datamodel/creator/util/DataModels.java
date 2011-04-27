/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.util;

import kohary.datamodel.creator.home.ListEditorModel;
import kohary.datamodel.creator.attribute.AttributeCreatorFrame;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.ListModel;
import kohary.datamodel.creator.util.ListModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class DataModels extends ListModel<DataModel> {

    int index = 1;
    String str = null;

    @Override
    public void addNew(ListEditorModel list) {


        str = JOptionPane.showInputDialog(null, "Enter Datamodel name : ",
                "Datamodel name", 1);

        if (str != null) {
            DataModel dataModel = new DataModel();

            dataModel.setID(index);
            if (str.equals("")) {
                dataModel.setID(index);
                dataModel.setName("DataModel" + index++);

            } else {
                dataModel.setName(str);
                dataModel.setID(index);
                index++;
            }
           
            AttributeCreatorFrame atbCrFrame = new AttributeCreatorFrame(dataModel, list);
            atbCrFrame.setTitle(dataModel.getName());
            elements.add(dataModel);
            fireIntervalAdded(this, elements.indexOf(dataModel), elements.indexOf(dataModel));
            list.editableOff();
        }
        list.editableOn();


    }

    public void addSpecial(DataModel dataModel) {

        fireIntervalAdded(this, elements.indexOf(dataModel), elements.indexOf(dataModel));
        index++;
    }

    @Override
    public void addNew() {
    }
}
