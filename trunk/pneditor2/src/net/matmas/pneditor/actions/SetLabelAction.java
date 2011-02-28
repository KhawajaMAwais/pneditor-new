package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Node;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.commands.CompositeCommand;
import net.matmas.pneditor.commands.SetLabelCommand;
import net.matmas.util.CollectionTools;
import net.matmas.pneditor.commands.Command;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SetLabelAction extends Action {
	
	public SetLabelAction() {
		String name = "Set label";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/label.gif"));
		putValue(SHORT_DESCRIPTION, name);
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		MainFrame mainFrame = PNEditor.getInstance().getMainFrame();
		Set<Node> selectedNodes = selection.getNodes();

		if (!selectedNodes.isEmpty()) {

			String label = "";
			if (selection.getNodes().size() == 1) {
				Node node = CollectionTools.getFirstElement(selection.getNodes());
				label = node.getLabel().getText();
			}
			
			String newLabel = JOptionPane.showInputDialog(mainFrame, "New label:", label);
			if (newLabel != null) {

				List<Command> commands = new ArrayList<Command>();
				for (Node node : selectedNodes) {
					if (!newLabel.equals(node.getLabel().getText())) {
						commands.add(new SetLabelCommand(node, newLabel));
					}
				}

				if (!commands.isEmpty()) {
					PNEditor.getInstance().getUndoManager().executeCommand(new CompositeCommand(commands));
				}
			}

		}
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return !selection.getNodes().isEmpty();
	}
}
