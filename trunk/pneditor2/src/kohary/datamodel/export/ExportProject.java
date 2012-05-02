/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.Document;
import kohary.datamodel.actions.OpenDocumentAction;

/**
 *
 * @author Godric
 */
public class ExportProject {

    private File file;

    public ExportProject(File file) throws ClassNotFoundException {
        this.file = file;
        open();
        setFirstAsCurrentDatamodel();
        refreshDatamodelList();
        setProjectTitle();
    }

    private void open() throws ClassNotFoundException {
        ObjectInputStream oos = null;
        try {
            FileInputStream openFile = new FileInputStream(file);
            oos = new ObjectInputStream(openFile);
            //DataModel datamodel = DatamodelCreator.getInstance().getDocument().getDataModels().getElements().get(0);
            Document d = (Document) oos.readObject();
            DatamodelCreator.getInstance().setDocument(d);
            DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();


        } catch (IOException ex) {
            Logger.getLogger(OpenDocumentAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(OpenDocumentAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    private void setFirstAsCurrentDatamodel() {
        if (!DatamodelCreator.getInstance().getDocument().getDataModels().getElements().isEmpty()) {
            DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel(DatamodelCreator.getInstance().getDocument().getDataModels().getElements().get(0));
        }
    }

    private void setProjectTitle() {
        DatamodelCreator.getInstance().getMainFrame().setTitle(file.getName().substring(0, file.getName().lastIndexOf(".")));
    }
    private void refreshDatamodelList(){
        DatamodelCreator.getInstance().getMainFrame().getSelectDataModelPanel().getListPanel().refresh();
        DatamodelCreator.getInstance().getDocument().getDataModels().checkEmpty();
        
       
    }
}
