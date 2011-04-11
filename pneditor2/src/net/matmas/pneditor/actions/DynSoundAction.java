/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.filechooser.FileType;
import net.matmas.pneditor.filechooser.FileTypeException;
import net.matmas.pneditor.filechooser.PetriNetFileType;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class DynSoundAction extends Action {

	public DynSoundAction() {
		String name = "Dynamic sounddness";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/IsSoundButton.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {

            String filename = "network-dynSound.pflow";
            FileType chosenFileType = new PetriNetFileType();
            File file = new File(filename);
            try {
                    chosenFileType.save(PNEditor.getInstance().getDocument(), file);
            } catch (FileTypeException ex) {
                    JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), ex.getMessage());
                    return;
            }

            String ret = new String();
            String win_cmd = "dynSound " + filename;
            String unix_cmd = "./dynSound " + filename;
            String cmd = unix_cmd;
            if (System.getProperty("os.name").indexOf("Windows") >= 0)
                cmd = win_cmd;
            Process binaryProc;
            try{
                binaryProc = Runtime.getRuntime().exec(cmd);
                binaryProc.waitFor();
                InputStream is;
                is = binaryProc.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                int data = reader.read();
                while(data != -1){
                    char theChar = (char) data;
                    ret += theChar;
                    data = reader.read();
                }
                reader.close();

            }catch (/*IOException*/Exception ee) {
                JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), "Unable tu run or finish binary subroutine!");
                return;
            }
            //Print result:
            JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), ret);
            //finish

	}


}