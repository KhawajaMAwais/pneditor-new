/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.home;

import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
import javax.swing.JFrame;

/**
 *
 * @author Godric
 */
public interface InterfaceOfDataModels {
   
    public DataModels getDataModels();
    public void setDataModels(DataModels dataModels);
    public DataModel getSelectedDataModel();

}
