/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.commands.AddImageCommand;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.util.ImageCopier;

/**
 *
 * @author Godric
 */
public class AddDImageAction extends Action{
    
    private JFileChooser importImageChooser = new JFileChooser();

    public AddDImageAction() {
       
                String name = "Add image";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);    
    }
    
    

    public void actionPerformed(ActionEvent e) {
        addFileChooser();
    }
    
    private void add(File file){
        
        String relativeOwnPath = ImageCopier.copyImage(file);
        DatamodelCreator.getInstance().getUndoManager().executeCommand(new AddImageCommand(file, new Point(0,0), DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel()));
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
    
    private void addFileChooser(){
    
        FileFilter filter = new FileFilter() {

            public boolean accept(File pathname) {
                if( pathname.getAbsolutePath().endsWith(".jpg"))
                    return true;
                else if( pathname.getAbsolutePath().endsWith(".jpeg"))
                    return true;
                else if( pathname.getAbsolutePath().endsWith(".gif"))
                    return true;
                else if( pathname.getAbsolutePath().endsWith(".png"))
                    return true;
                else return false;
            }

            @Override
            public String getDescription() {
                return " Only .JPG, .GIF, .PNG picture are allowed";
            }
            
         
        };
        
        importImageChooser.setFileFilter(filter);
        int returnVal = importImageChooser.showSaveDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = importImageChooser.getSelectedFile();            
            add(file);
            
        } else {
            
        }
    }
}
    

