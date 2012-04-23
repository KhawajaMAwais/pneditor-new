/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.matmas.pneditor.actions;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;
import xesloganalyzer.LogTable;

/**
 *
 * @author XY
 */
public class LogAction extends Action {

	public LogAction() {
		String name = "Log analyzer";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/log_1.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}
	
	public void actionPerformed(ActionEvent e) {
	  PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().logtable.show();
          
	}

}