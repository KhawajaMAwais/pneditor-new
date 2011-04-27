/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import kohary.invariant.SMAlghoritm;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class PTInvariantAction extends Action {

	public PTInvariantAction() {
		String name = "P/T Invariants";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/pti.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
                SMAlghoritm smalghoritm = new SMAlghoritm(PNEditor.getInstance().getDocument().getPetriNet(), null);
	}


}

