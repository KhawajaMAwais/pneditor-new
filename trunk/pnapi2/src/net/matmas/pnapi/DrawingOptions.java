package net.matmas.pnapi;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author matmas
 */
public class DrawingOptions {
	
	public DrawingOptions(Marking marking) {
		this.marking = marking;
	}

	public DrawingOptions() {
	}

	// -------------------------------------------------------------------------

	private Marking marking;

	public Marking getMarking() {
		return marking;
	}

	public void setMarking(Marking marking) {
		this.marking = marking;
	}

	// -------------------------------------------------------------------------

	private double zoom = 1.0;

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	// -------------------------------------------------------------------------

	private Map<Element, Color> elementColors = new Hashtable<Element, Color>();

	public Map<Element, Color> getElementColors() {
		return elementColors;
	}

	public void setElementColors(Map<Element, Color> elementColors) {
		this.elementColors = elementColors;
	}

	// -------------------------------------------------------------------------

	public void setElementColor(Element element, Color color) {
		this.elementColors.put(element, color);
	}
}
