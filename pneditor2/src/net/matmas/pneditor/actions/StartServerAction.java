/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class StartServerAction extends Action {

	public StartServerAction() {
		String name = "Start server";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/StartServer.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {


            String win_cmd = "cmd start /c \"cd tomcat/bin & startup.bat\"";
            String unix_cmd = "./tomcat/bin/startup.sh";
            String cmd = unix_cmd;
            if (System.getProperty("os.name").indexOf("Windows") >= 0)
                cmd = win_cmd;
            Process binaryProc;
            try{
                binaryProc = Runtime.getRuntime().exec(cmd);

            }catch (/*IOException*/Exception ee) {
                JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), "Unable to run server!");
                return;
            }


	}


}
