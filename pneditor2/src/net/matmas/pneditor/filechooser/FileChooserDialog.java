package net.matmas.pneditor.filechooser;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author matmas
 */
public class FileChooserDialog extends JFileChooser {

	private Sidebar sidebar = new Sidebar(this);

	public FileChooserDialog() {
		setFileView(new FileIconView());
		setAccessory(sidebar);
	}
	
	@Override
	public File getSelectedFile() {
		File file = super.getSelectedFile();
		if (file == null) {
			return null;
		}
		else if (file.exists() && getFileFilter().getDescription().equals("All Files")) {
			return file;
		}
		else if (getFileFilter().accept(file)) {
			return file;
		}
		else {
			return new File(file.getAbsolutePath() + "." + ((FileType)getFileFilter()).getExtension());
		}
	}

	@Override
	public void addChoosableFileFilter(FileFilter filter) {
		super.addChoosableFileFilter(filter);
		if (getChoosableFileFilters().length > 1) { // first filter is always "All files"
			setFileFilter(getChoosableFileFilters()[1]);
		}
	}

	public Sidebar getSidebar() {
		return sidebar;
	}
}
