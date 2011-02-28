package net.matmas.pneditor.filechooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import net.matmas.pnapi.Document;
import net.matmas.pnapi.Marking;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class PngFileType extends FileType {

	@Override
	public String getExtension() {
		return "png";
	}

	@Override
	public String getName() {
		return "Portable Network Graphics";
	}

	@Override
	public Icon getIcon() {
		final Icon icon = GraphicsTools.getIcon("pneditor/filechooser/png.gif");
		return icon;
	}

	@Override
	public Document load(File file) throws FileTypeException {
		throw new UnsupportedOperationException("Loading not supported.");
	}

	@Override
	public BufferedImage getPreview(File file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException ex) {
		}
		return image;
	}
	
	@Override
	public void save(Document document, File file) throws FileTypeException {
		try {
			Marking initialMarking = document.getPetriNet().getMarking();
			BufferedImage bufferedImage = document.getPetriNet().getPreview(initialMarking);
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException ex) {
			throw new FileTypeException(ex.getMessage());
		}
	}
}
