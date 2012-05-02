/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SaveDocumentAction extends Action{
 private JFileChooser exportFileChooser = new JFileChooser();
 private int returnVal;
 private File file;

    public SaveDocumentAction() {
            String name = "Save project";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("Save16.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);
    }
  
    
    private void save(){
        try{
            FileOutputStream saveFile=new FileOutputStream(new File(file.getAbsolutePath()+".dml")); 
            ObjectOutputStream oos = new ObjectOutputStream(saveFile);
            oos.writeObject(DatamodelCreator.getInstance().getDocument());         // .writeObject(oos);
            oos.close();
        }catch(Exception ex){
            
        }
       
    }

    public void actionPerformed(ActionEvent e) {
                returnVal = exportFileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = exportFileChooser.getSelectedFile();
          save();
          DatamodelCreator.getInstance().getMainFrame().setTitle(file.getName());
         //   ziper.toZip();
        }
    }
    
}
