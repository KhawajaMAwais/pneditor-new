/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import kohary.datamodel.managers.DataModelSelectManager;
import kohary.datamodel.managers.DefaultSettingsManager;
import kohary.datamodel.managers.SelectionManager;
import kohary.datamodel.managers.UndoManager;
import kohary.datamodel.variables.VariableCreatorFrame;

/**
 *
 * @author Godric
 */
public class DatamodelCreator {

    public DatamodelCreator() {
        Preferences preferences = Preferences.userNodeForPackage(this.getClass());
        setCurrentDirectory(new File(preferences.get("current directory", System.getProperty("user.home"))));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                Preferences preferences = Preferences.userNodeForPackage(this.getClass());

                try {
                    preferences.flush();
                } catch (BackingStoreException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
    }
    // Singleton ---------------------------------------------------------------
    private static DatamodelCreator instance = new DatamodelCreator();

    public static DatamodelCreator getInstance() {
        return instance;
    }
    
        private DataModelSelectManager dataModelSelectManager = new DataModelSelectManager();

    public DataModelSelectManager getDataModelSelectManager() {
        return dataModelSelectManager;
    }
    
        // -------------------------------------------------------------------------
    	protected File currentDirectory;

	public File getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

    // -------------------------------------------------------------------------
    private  Document document = new Document();

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    //-------------------------------------------------------------------------
    private DefaultSettingsManager defaultSettingsManager = new DefaultSettingsManager();

    public DefaultSettingsManager getDefaultSettingsManager() {
        return defaultSettingsManager;
    }
    
    
    
    //--------------------------------------------------------------------------
    private SelectionManager selectionManager = new SelectionManager();

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }
    
    //--------------------------------------------------------------------------
    
    private UndoManager undoManager = new UndoManager();

    public UndoManager getUndoManager() {
        return undoManager;
    }
    
    //--------------------------------------------------------------------------
//    private VariableCreatorFrame variableFrame = new VariableCreatorFrame();

    private MainFrame mainFrame = new MainFrame();

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    
    
    
}
