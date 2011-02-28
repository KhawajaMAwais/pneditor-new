package net.matmas.pneditor.filechooser;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

/**
 *
 * @author matmas
 */
public class FileIconView extends FileView {
	
	@Override
	public Icon getIcon(File file) {
		if (file.isDirectory()) {
			return super.getIcon(file);
		}
		FileType fileType = FileType.getAcceptingFileType(file, FileType.getAllFileTypes());
		if (fileType != null) {
			return fileType.getIcon();
		}
		return super.getIcon(file);
//		if (file.isDirectory()) {
//			return super.getIcon(file);
//		}
//		if (cache.containsKey(file)) {
//			return cache.get(file);
//		}
//		try {
//			for (Element element : new DocumentImporter().readFromFile(file).petriNet.getRootSubnet().getElements()) {
//				if (element instanceof ReferencePlace) {
//					cache.put(file, pflowxIcon);
//					return pflowxIcon;
//				}
//			}
//			cache.put(file, pflowIcon);
//			return pflowIcon;
//		} catch (JAXBException ex) {
//		}
//		cache.put(file, super.getIcon(file));
//		return super.getIcon(file);
	}
	
}
