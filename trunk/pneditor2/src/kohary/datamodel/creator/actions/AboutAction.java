/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.MainDataModelFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class AboutAction extends Action implements ActionListener{
    private MainDataModelFrame frame;
    	public AboutAction(MainDataModelFrame frame) {

		String name = "About...";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/about.png"));
		putValue(SHORT_DESCRIPTION, name);

            this.frame=frame;
        }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showOptionDialog(
				frame,
				"DataModel Creator " + "\n\n" +
				"Copyright (c) 2010 by Richard Kohary. All rights reserved.\n" +
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
