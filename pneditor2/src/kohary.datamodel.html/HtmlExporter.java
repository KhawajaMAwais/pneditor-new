/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Godric
 */
public class HtmlExporter {

    private File directoryPath;

    public HtmlExporter(File directoryPath) {
        this.directoryPath = directoryPath;
        createHtmlsToDirectories(directoryPath);
    }




    private void createHtmlsToDirectories(File file) {
        File webDirectory = new File(file.getAbsolutePath()+"/web");
        webDirectory.mkdir();
        new HtmlGenerator(webDirectory.getAbsolutePath());
    }
}
