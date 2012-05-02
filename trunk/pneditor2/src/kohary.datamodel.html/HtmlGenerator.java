/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class HtmlGenerator {

    private String mainDirectory;

    public HtmlGenerator(String mainDirectory) {

        this.mainDirectory = mainDirectory;
        List<DataModel> datamodels = DatamodelCreator.getInstance().getDocument().getDataModels().getElements();
        for (DataModel dataModel : datamodels) {
            File subDirectory = new File(mainDirectory +"/"+ dataModel.getName());
            subDirectory.mkdir();
            File directoryImage = new File(mainDirectory +"/images");
            directoryImage.mkdir();
            HtmlPage htmlPage = new HtmlPage(dataModel, subDirectory.getAbsolutePath(),directoryImage.getAbsolutePath());
        }        
    }
    
    
}
