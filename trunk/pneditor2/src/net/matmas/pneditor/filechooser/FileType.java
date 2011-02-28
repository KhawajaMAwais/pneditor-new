package net.matmas.pneditor.filechooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.filechooser.FileFilter;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.PetriNet;
import net.matmas.util.StringTools;

/**
 *
 * @author matmas
 */
public abstract class FileType extends FileFilter {

	public static Set<FileType> getAllFileTypes() {
		Set<FileType> allFileTypes = new HashSet<FileType>();

		allFileTypes.add(new PngFileType());
		allFileTypes.add(new EpsFileType());
		allFileTypes.add(new PetriNetFileType());

		return allFileTypes;
	}

	public abstract String getExtension();
	public abstract String getName();
	public abstract void save(Document document, File file) throws FileTypeException;
	public abstract Document load(File file) throws FileTypeException;
	public abstract Icon getIcon();

	public BufferedImage getPreview(File file) {
		try {
			Document document = load(file);
			PetriNet petriNet = document.getPetriNet();
			BufferedImage image = petriNet.getPreview(petriNet.getMarking());
			return image;
		}
		catch (FileTypeException ex) {
		}
		return null;
	}

	public String getDescription() {
		return getName() + " (*." + getExtension() + ")";
	}
	
	public boolean accept(File file) {
		if (file.isDirectory()) { //Show also directories in the filters
			return true;
		}
		
		String extension = StringTools.getExtension(file);
		if (extension != null) {
			if (extension.equals(getExtension())) {
				return true;
			}
		}
		return false;
	}
	
	public static FileType getAcceptingFileType(File file, Collection<FileType> fileTypes) {
		for (FileType fileType : fileTypes) {
			if (fileType.accept(file)) {
				return fileType;
			}
		}
		return null;
	}
}
