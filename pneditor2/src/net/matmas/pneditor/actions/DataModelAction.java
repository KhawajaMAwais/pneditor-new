/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class DataModelAction extends Action {

	public DataModelAction() {
		String name = "Data model";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/datamodel.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {

	}


}
