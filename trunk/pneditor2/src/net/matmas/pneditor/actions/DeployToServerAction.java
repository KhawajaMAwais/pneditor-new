package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.functions.DeployToServerDialog;
import net.matmas.util.CollectionTools;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class DeployToServerAction extends Action {
	
	public DeployToServerAction() {
		String name = "Deploy to server";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/upload.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
        MainFrame mainFrame = PNEditor.getInstance().getMainFrame();
		Selection selection = mainFrame.getDrawingBoard().getCanvas().getSelection();
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();


		WithProperties withProperties = null;
		if (selection.isEmpty()) {
			withProperties = petriNet;
		}
		if (selection.size() == 1) {
			withProperties = CollectionTools.getFirstElement(selection);
		}
		new DeployToServerDialog(mainFrame, withProperties);
    }

}