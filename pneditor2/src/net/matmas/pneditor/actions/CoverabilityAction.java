/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.functions.coverability.MainWindow;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class CoverabilityAction extends Action {

	public CoverabilityAction() {
		String name = "Coverability graph";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/coverability.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
            PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
           // if(petriNet.getPlaces().get(0).getIsStartPlace())
                new MainWindow();
	}


}

