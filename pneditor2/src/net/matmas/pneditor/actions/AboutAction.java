package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class AboutAction extends Action {

	public AboutAction() {
		String name = "About...";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/About16.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showOptionDialog(
				PNEditor.getInstance().getMainFrame(),
				PNEditor.getAppLongName() + "\n\n" +
				"Copyright (c) 2008-2009 by Martin Riesz. All rights reserved.\n" +
				"For more information, visit http://pneditor.matmas.net/\n\n" +
				"Warning: This computer program is protected by copyright law\n" +
				"and international treaties. Unauthorized reproduction or distribution\n" +
				"may result in severe civil and criminal penalties, and will be\n" +
				"prosecuted to the maximum extend possible under the law.",
				"About",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new String[] {"OK"},
				"OK");
	}

}
