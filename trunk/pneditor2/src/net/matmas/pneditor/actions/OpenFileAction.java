package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Document;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.filechooser.FileChooserDialog;
import net.matmas.pneditor.filechooser.FileType;
import net.matmas.pneditor.filechooser.FileTypeException;
import net.matmas.pneditor.filechooser.PetriNetFileType;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class OpenFileAction extends Action {
	
	public OpenFileAction() {
		String name = "Open...";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Open16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);
	}

	public void actionPerformed(ActionEvent e) {
		List<FileType> fileTypes = new ArrayList<FileType>();
		fileTypes.add(new PetriNetFileType());


		if (!PNEditor.getInstance().getUndoManager().isDocumentModified() || JOptionPane.showOptionDialog(
				PNEditor.getInstance().getMainFrame(),
				"Any unsaved changes will be lost. Continue?",
				"Open file...",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				new String[] {"Open...", "Cancel"},
				"Cancel") == JOptionPane.YES_OPTION
		) {
			FileChooserDialog chooser = new FileChooserDialog();

			for (FileType fileType : fileTypes) {
				chooser.addChoosableFileFilter(fileType);
			}
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setCurrentDirectory(PNEditor.getInstance().getCurrentDirectory());

			if (chooser.showOpenDialog(PNEditor.getInstance().getMainFrame()) == JFileChooser.APPROVE_OPTION) {

				File file = chooser.getSelectedFile();
				FileType chosenFileType = (FileType)chooser.getFileFilter();

				try {
					Document document = chosenFileType.load(file);
					PNEditor.getInstance().setDocument(document);
					PNEditor.getInstance().setCurrentFile(file);
					PNEditor.getInstance().getUndoManager().setDocumentModified(false);
				} catch (FileTypeException ex) {
					JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), ex.getMessage());
				}

			}
			PNEditor.getInstance().setCurrentDirectory(chooser.getCurrentDirectory());
		}
	}
}