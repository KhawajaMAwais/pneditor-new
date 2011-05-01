/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import kohary.datamodel.creator.xml.Import;
import kohary.datamodel.creator.xml.XmlDocument;

/**
 *
 * @author Godric
 */
public class OpenXmlDatamodelAction implements ActionListener {

    private JFileChooser fc;
    private File file;
    private String stringXmlDocument;
    private JDialog dialog;
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    String output ="";

    public OpenXmlDatamodelAction(String stringXmlDocument, JDialog dialog) {
        this.stringXmlDocument = stringXmlDocument;
        this.dialog = dialog;
        fc = new JFileChooser();
    }

    public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showSaveDialog(dialog);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenXmlDatamodelAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        dis = new DataInputStream(bis);
        try {
       
           while (dis.available() != 0) {

                // this statement reads the line from the file and print it to
                // the console.
              //  System.out.println(dis.readLine());
                output+=dis.readLine();
            }
          
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }

    }
    public String getOutput(){
        return this.output;
    }
}
