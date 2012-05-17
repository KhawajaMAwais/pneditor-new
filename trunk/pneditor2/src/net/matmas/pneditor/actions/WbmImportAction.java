/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package net.matmas.pneditor.actions;

import ArisWbmImporter.WbmImporter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;
import net.matmas.pnsynthesizer.layout.graphviz.GraphvizLayout;
import net.matmas.util.GraphicsTools;
import org.openide.util.Exceptions;

/**
 *
 * @author JONNY
 */
public class WbmImportAction extends Action {

	public WbmImportAction() {
		String name = "Import Wbm xml file";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/wbm_import.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
          try {  
        JFileChooser chooser = new JFileChooser();
        File file = null;
        FileFilter filter = new FileNameExtensionFilter("XML WBM file", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
               PNEditor.getInstance().getDocument().getPetriNet().clear();
               PNEditor.getInstance().getMainFrame().refreshActions();
               file =  chooser.getSelectedFile();
               new WbmImporter(file);
            
               try {
                     PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                        new GraphvizLayout(petriNet).layout();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
               
               PNEditor.getInstance().getMainFrame().refreshActions();
            
               
        }
 
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }  
               
	}


}