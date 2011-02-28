package net.matmas.pneditor;

import java.awt.Color;
import java.util.Set;
import net.matmas.pnapi.Element;

/**
 *
 * @author matmas
 */
public class Selection extends Elements {

	@Override
	public void clear() {
		for (Element element : this) {
			PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().removeStaticElementColor(element);
		}
		super.clear();
		selectionChanged();
	}

	@Override
	public void add(Element element) {
		super.add(element);
		selectionChanged();
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setStaticElementColor(element, Color.lightGray);
	}

	@Override
	public void addAll(Set<? extends Element> elements) {
		super.addAll(elements);
		selectionChanged();
		for (Element element : this) {
			PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setStaticElementColor(element, Color.lightGray);
		}
	}

	// -------------------------------------------------------------------------

	private void selectionChanged() {
		PNEditor.getInstance().getMainFrame().refreshActions();
	}
}
