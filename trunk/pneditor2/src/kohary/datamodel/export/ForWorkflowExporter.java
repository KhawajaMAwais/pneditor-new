/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.export;

import java.io.File;
import javax.swing.JFileChooser;
import kohary.datamodel.HtmlExporter;

/**
 *
 * @author Godric
 */
public class ForWorkflowExporter {

    private HtmlExporter htmlExporter;
    private XmlExporter xmlExporter;
    private JFileChooser exportFileChooser = new JFileChooser();
    private int returnVal;

    public ForWorkflowExporter() throws Exception {

        returnVal = exportFileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = exportFileChooser.getSelectedFile();
            createMainDirectory(file);
            htmlExporter=new HtmlExporter(file);
            xmlExporter = new XmlExporter(file);
            
            Ziper ziper = new Ziper(file);
            
         //   ziper.toZip();
        }

    }
        private void createMainDirectory(File file) {
        boolean success = (new File(file.getAbsolutePath())).mkdir();
    }
}
