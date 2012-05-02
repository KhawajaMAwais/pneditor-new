/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import kohary.datamodel.dapi.DTextPane;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Design;

/**
 *
 * @author Godric
 */
public class AddTextPaneCommand implements Command{
    
     
    private Point location;
    private DataModel dataModel;
    private DTextPane textPane;
    private String text;

    public AddTextPaneCommand(Point location, DataModel dataModel,String text) {
        this.location = location;
        this.dataModel = dataModel;
        this.text = text;
      
    }
            
    
   

    public void execute() {
         textPane = new DTextPane(location,text);              
        dataModel.getPage().getDesignElements().add(textPane);

    }

    public void undo() {
        new DeleteDesignCommand(dataModel,(Design) textPane).execute();
    }

    public void redo() {
        dataModel.getPage().getDesignElements().add(textPane);
    }

    public String actionName() {
       return "Add TextPane";
    }
    
}
