/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.CanvasPanel;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.xml.Export;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SaveAction extends Action implements ActionListener{
    private CanvasPanel canvas;
    private JFileChooser fc;
    private File file;
    
    public SaveAction(CanvasPanel canvas) {
        this.canvas=canvas;
            String name = "Save";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/Save16.gif"));
		putValue(SHORT_DESCRIPTION, name);
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
        fc.setFileFilter(filter);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            int returnVal = fc.showSaveDialog(canvas);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
               
                System.out.print(fc.getCurrentDirectory().toString());
            }
            new Export(canvas.getDataModels(), file);


        } catch (JAXBException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        }


                  
    }

}
