/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.CanvasPanel;
import kohary.datamodel.creator.xml.Export;
import kohary.datamodel.creator.xml.ExportSepare;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SaveSepareAction extends Action implements ActionListener{
    private CanvasPanel canvas;
    private JFileChooser fc;
    private File file;

    public SaveSepareAction(CanvasPanel canvas) {
        this.canvas = canvas;
         String name = "Save Separe";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/Save16.gif"));
		putValue(SHORT_DESCRIPTION, name);
        fc = new JFileChooser();
    }

    
    
    public void actionPerformed(ActionEvent e) {
               try {
            int returnVal = fc.showSaveDialog(canvas);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getCurrentDirectory();
                System.out.print(fc.getCurrentDirectory().toString());
            }
            new ExportSepare(canvas.getDataModels(), file);


        } catch (JAXBException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
