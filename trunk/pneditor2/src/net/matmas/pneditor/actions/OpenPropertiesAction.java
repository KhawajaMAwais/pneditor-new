package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pneditor.properties.PropertiesDialog;
import net.matmas.util.CollectionTools;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class OpenPropertiesAction extends Action {

	public OpenPropertiesAction() {
		String name = "Properties...";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Edit16.gif"));
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
		new PropertiesDialog(mainFrame, withProperties);
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return selection.isEmpty() || selection.size() == 1;
	}
	
}
