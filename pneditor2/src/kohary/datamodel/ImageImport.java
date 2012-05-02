/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Godric
 */
public class ImageImport {
    private JFileChooser importImageChooser = new JFileChooser();
    

    public ImageImport() {
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
            
            
        } else {
            
        }
    }

    public JFileChooser getImportImageChooser() {
        return importImageChooser;
    }
    
    
    
}
