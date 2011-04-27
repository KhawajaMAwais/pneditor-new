/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.CanvasPanel;
import kohary.datamodel.creator.home.DataModelSelectPanel;
import kohary.datamodel.creator.xml.Export;
import kohary.datamodel.creator.xml.Import;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class LoadAction extends Action implements ActionListener {

    private CanvasPanel canvas;
    private JFileChooser fc;
    private File file;
    private PetriNet petriNet;
    
    public LoadAction(CanvasPanel canvas,PetriNet petriNet) {
        this.canvas = canvas;
        this.petriNet=petriNet;
        fc = new JFileChooser();
                    String name = "Load";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/import.gif"));
		putValue(SHORT_DESCRIPTION, name);

    }

    public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showSaveDialog(canvas);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        try {
            new Import(file, canvas.getDataModels(),canvas,petriNet);
           // canvas.setSelectPanel(new DataModelSelectPanel(canvas.getDataModels(), canvas.getPetriNet()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoadAction.class.getName()).log(Level.SEVERE, null, ex);
        }







    }
}
