/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import kohary.datamodel.DatamodelCreator;

import kohary.datamodel.Datamodels;
import kohary.datamodel.Document;
import kohary.datamodel.export.ExportProject;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class OpenDocumentAction extends Action{
     private JFileChooser exportFileChooser = new JFileChooser();
 private int returnVal;
 private File file;


    public OpenDocumentAction() {
            String name = "Open project";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("Open16.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);
    }


    public void actionPerformed(ActionEvent e) {
                   returnVal = exportFileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = exportFileChooser.getSelectedFile();
            try {
                ExportProject exportProject = new ExportProject(file);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OpenDocumentAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
