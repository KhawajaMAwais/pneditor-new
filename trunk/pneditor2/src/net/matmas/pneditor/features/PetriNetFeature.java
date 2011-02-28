package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JScrollBar;
import net.matmas.pnapi.DrawingOptions;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author matmas
 */
public class PetriNetFeature extends Feature {

	public PetriNetFeature(Canvas canvas) {
		super(canvas);
	}

	private DrawingOptions drawingOptions = new DrawingOptions();

	public void drawMainLayer(Graphics g) {
		drawingOptions.setMarking(PNEditor.getInstance().getDocument().getPetriNet().getMarking());
		drawingOptions.setZoom(canvas.getZoom());
		drawingOptions.setElementColors(canvas.getElementColors());

		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		petriNet.draw(g, drawingOptions);
		

		JScrollBar horizontalScrollBar = PNEditor.getInstance().getMainFrame().getDrawingBoard().getHorizontalScrollBar();
		JScrollBar verticalScrollBar = PNEditor.getInstance().getMainFrame().getDrawingBoard().getVerticalScrollBar();

		Rectangle petriNetBounds = PNEditor.getInstance().getDocument().getPetriNet().getBounds(drawingOptions);
		Rectangle canvasBounds = canvas.getBounds();
		canvasBounds.translate(- canvas.getViewTranslation().getX(), - canvas.getViewTranslation().getY());

		if (!petriNetBounds.isEmpty()) {
			petriNetBounds.add(canvasBounds);
		}

		horizontalScrollBar.setMinimum(petriNetBounds.x);
		horizontalScrollBar.setMaximum(petriNetBounds.x + petriNetBounds.width);
		horizontalScrollBar.setVisibleAmount(canvasBounds.width);
		verticalScrollBar.setMinimum(petriNetBounds.y);
		verticalScrollBar.setMaximum(petriNetBounds.y + petriNetBounds.height);
		verticalScrollBar.setVisibleAmount(canvasBounds.height);
	}
	
	public void drawForeground(Graphics g) {}
	public void drawBackground(Graphics g) {}
	
}
