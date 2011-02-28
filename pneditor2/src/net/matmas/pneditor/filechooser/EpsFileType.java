package net.matmas.pneditor.filechooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Icon;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Element;
import net.matmas.util.EPSGraphics2D;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class EpsFileType extends FileType {

	@Override
	public String getExtension() {
		return "eps";
	}

	@Override
	public String getName() {
		return "Encapsulated PostScript";
	}

	@Override
	public Icon getIcon() {
		final Icon icon = GraphicsTools.getIcon("pneditor/filechooser/eps.gif");
		return icon;
	}

	@Override
	public Document load(File file) throws FileTypeException {
		throw new UnsupportedOperationException("Loading not supported.");
	}

	@Override
	public BufferedImage getPreview(File file) {
		return null;
	}

	@Override
	public void save(Document document, File file) throws FileTypeException {
		try {
			EPSGraphics2D epsGraphics2d = new EPSGraphics2D();
			DrawingOptions drawingOptions = new DrawingOptions();
			drawingOptions.setMarking(document.getPetriNet().getMarking());
			document.getPetriNet().draw(epsGraphics2d, drawingOptions);
			epsGraphics2d.writeToFile(file);
		} catch (FileNotFoundException ex) {
			throw new FileTypeException(ex.getMessage());
		}
	}
	
}
