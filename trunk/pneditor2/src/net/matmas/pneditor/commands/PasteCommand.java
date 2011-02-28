package net.matmas.pneditor.commands;

import java.awt.Rectangle;
import java.util.Set;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class PasteCommand implements Command {

	private PetriNet petriNet;
	private Set<Element> elements;

	public PasteCommand(Set<Element> elements, PetriNet petriNet) {
		this.petriNet = petriNet;
		this.elements = elements;

		Point translation = calculateTranslationToCenter(elements);
		for (Element element : elements) {
			element.moveBy(translation.getX(), translation.getY());
		}
	}

	public void execute() {
		petriNet.addAll(elements);
	}

	public void undo() {
		petriNet.removeAll(elements);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Paste";
	}
	
	private Point calculateTranslationToCenter(Set<Element> elements) {
		Canvas canvas = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas();
		Point viewTranslation = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getViewTranslation();
		PetriNet tempPetriNet = new PetriNet();
		tempPetriNet.addAll(elements);
		Rectangle bounds = tempPetriNet.getBounds(new DrawingOptions());

		Point result = new Point();
		result = result.getTranslated(Math.round(-(float)bounds.getCenterX()), Math.round(-(float)bounds.getCenterY()));
		result = result.getTranslated(-viewTranslation.getX(), -viewTranslation.getY());
		result = result.getTranslated(canvas.getWidth() / 2, canvas.getHeight() / 2);
		return result;
	}

}
