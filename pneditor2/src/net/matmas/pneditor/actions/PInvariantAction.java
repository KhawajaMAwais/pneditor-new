/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class PInvariantAction extends Action {

	public PInvariantAction() {
		String name = "P Invariants";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Pinv.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {

	}


}

