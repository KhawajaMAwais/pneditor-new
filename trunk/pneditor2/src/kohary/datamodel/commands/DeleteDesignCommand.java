/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Design;
import kohary.datamodel.dapi.Text;

/**
 *
 * @author Godric
 */
public class DeleteDesignCommand implements Command{
      private DataModel dataModel;
      private Design designElement;

    public DeleteDesignCommand(DataModel dataModel, Design designElement) {
        this.dataModel = dataModel;
        this.designElement = designElement;
    }
              
              

    public void execute() {
       dataModel.getPage().getDesignElements().remove(designElement);
    }

    public void undo() {
        dataModel.getPage().getDesignElements().add(designElement);
    }

    public void redo() {
          dataModel.getPage().getDesignElements().remove(designElement);
    }

    public String actionName() {
         return "Delete design element";
    }
    
}
