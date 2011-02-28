package net.matmas.pneditor.filechooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.Icon;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.xml.DocumentImporter;
import net.matmas.pnapi.xml.XmlDocument;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class PetriNetFileType extends FileType {

	@Override
	public String getName() {
		return "PetriNet";
	}
	
	@Override
	public String getExtension() {
		return "pflow";
	}

	@Override
	public Icon getIcon() {
		final Icon icon = GraphicsTools.getIcon("pneditor/filechooser/pn.gif");
		return icon;
	}

	@Override
	public void save(Document document, File file) throws FileTypeException {
		try {
			new XmlDocument(document).writeToFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FileTypeException(ex.getMessage());
		}
	}

	@Override
	public Document load(File file) throws FileTypeException {
		Document document = null;
		try {
			document = new DocumentImporter().readFromFile(file);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FileTypeException(ex.getMessage());
		}
		return document;
	}
}
