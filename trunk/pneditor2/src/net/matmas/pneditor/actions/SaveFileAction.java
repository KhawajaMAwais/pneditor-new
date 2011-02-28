package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.filechooser.FileType;
import net.matmas.pneditor.filechooser.FileTypeException;
import net.matmas.pneditor.filechooser.PetriNetFileType;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SaveFileAction extends Action {

	public SaveFileAction() {
		String name = "Save";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Save16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
	}

	public void actionPerformed(ActionEvent e) {
		List<FileType> fileTypes = new ArrayList<FileType>();
		fileTypes.add(new PetriNetFileType());

		File file = PNEditor.getInstance().getCurrentFile();
		if (file != null) {
			try {
				FileType fileType = FileType.getAcceptingFileType(file, fileTypes);
				fileType.save(PNEditor.getInstance().getDocument(), file);
				PNEditor.getInstance().getUndoManager().setDocumentModified(false);
			} catch (FileTypeException ex) {
				JOptionPane.showMessageDialog(PNEditor.getInstance().getMainFrame(), ex.getMessage());
			}
		}
		else {
			new SaveFileAsAction().actionPerformed(e);
		}
		
	}
	
}
