/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.io.File;
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
            String unix_cmd = "./startup.sh";
            String cmd = unix_cmd;
            if (System.getProperty("os.name").indexOf("Windows") >= 0)
                cmd = win_cmd;
            Process binaryProc;
            try{
                if (System.getProperty("os.name").indexOf("Windows") >= 0)
                    binaryProc = Runtime.getRuntime().exec(cmd);
                else
                    binaryProc = Runtime.getRuntime().exec(cmd, null, new File(this.getJarFolder() + "./tomcat/bin"));

            }catch (/*IOException*/Exception ee) {
                System.out.println(ee.getMessage());
                JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), "Unable to run server!");
                return;
            }

            JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), "Server started!");

	}

    private String getJarFolder() {
        String name = this.getClass().getName().replace('.', '/');
        String s = this.getClass().getResource("/" + name + ".class").toString();
        s = s.replace('/', File.separatorChar);
        s = s.substring(0, s.indexOf(".jar")+4);
        s = s.substring(s.lastIndexOf(':')-1);
        s = s.substring(s.indexOf(':')+1);
        return s.substring(0, s.lastIndexOf(File.separatorChar)+1);
    }


}
