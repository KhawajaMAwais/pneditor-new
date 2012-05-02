/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import java.io.File;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class AddImageCommand implements Command {

    private File path;
    private Point location;
    private DataModel dataModel;
    private DImage image;

    public AddImageCommand(File path, Point location, DataModel dataModel) {
        this.path = path;
        this.location = location;
        this.dataModel = dataModel;
    }

    public void execute() {
        image = new DImage(location, path);

        dataModel.getPage().getDesignElements().add(image);
    }

    public void undo() {
        new DeleteDesignCommand(dataModel, image).execute();
    }

    public void redo() {
        dataModel.getPage().getDesignElements().add(image);
    }

    public String actionName() {
        return "Add Image ";
    }
}
