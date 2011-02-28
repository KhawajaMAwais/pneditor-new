package net.matmas.pneditor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.features.ArcBreakPointEditorFeature;
import net.matmas.pneditor.features.ArcFeature;
import net.matmas.pneditor.features.DraggingFeature;
import net.matmas.pneditor.features.Feature;
import net.matmas.pneditor.features.FireFeature;
import net.matmas.pneditor.features.ScrollingFeature;
import net.matmas.pneditor.features.PetriNetFeature;
import net.matmas.pneditor.features.PlaceTransitionMakerFeature;
import net.matmas.pneditor.features.PopupMenuFeature;
import net.matmas.pneditor.features.SelectionDrawingFeature;
import net.matmas.pneditor.features.SetJoinFeature;
import net.matmas.pneditor.features.SubnetCollapseExpandFeature;
import net.matmas.pneditor.features.SubnetDrawingFeature;
import net.matmas.pneditor.features.SubnetResizeFeature;
import net.matmas.pneditor.features.TokenFeature;
import net.matmas.pneditor.features.ToolSelectionFeature;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {

	private List<Feature> features = new ArrayList<Feature>();
        public boolean SetOrJoin = false;
        public boolean SetAndJoin = false;
        public boolean SetiterJoin = false;
		public Place pomp;
                public Transition pompt;
	public Canvas(DrawingBoard drawingBoard) {
		this.drawingBoard = drawingBoard;
		this.setBackground(Color.white);

		features.add(new PopupMenuFeature(this));
		features.add(new SelectionDrawingFeature(this));
		features.add(new PetriNetFeature(this));
		features.add(new PlaceTransitionMakerFeature(this));
		features.add(new ScrollingFeature(this));
		features.add(new DraggingFeature(this));
		features.add(new ArcFeature(this));
		features.add(new ArcBreakPointEditorFeature(this));
		features.add(new ToolSelectionFeature(this));
		features.add(new TokenFeature(this));
		features.add(new FireFeature(this));
		features.add(new SubnetDrawingFeature(this));
		features.add(new SubnetResizeFeature(this));
                features.add(new SetJoinFeature(this));
		features.add(new SubnetCollapseExpandFeature(this));
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	// -------------------------------------------------------------------------

	private DrawingBoard drawingBoard;

	public DrawingBoard getDrawingBoard() {
		return drawingBoard;
	}

	// -------------------------------------------------------------------------

	private Cursor activeCursor = Cursor.getDefaultCursor();

	public void setActiveCursor(Cursor activeCursor) {
		this.activeCursor = activeCursor;
		this.setCursor(activeCursor);
	}
	
	// -------------------------------------------------------------------------

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g);

		g.translate(viewTranslation.getX(), viewTranslation.getY());
		for (Feature feature : features) {
			feature.drawBackground(g);
		}
		for (Feature feature : features) {
			feature.drawMainLayer(g);
		}
		for (Feature feature : features) {
			feature.drawForeground(g);
		}
	}

	// -------------------------------------------------------------------------

	private Point viewTranslation = new Point();

	public Point getViewTranslation() {
		return viewTranslation;
	}

	public void setViewTranslation(Point viewTranslation) {
		this.viewTranslation = viewTranslation;
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
		Hashtable<Element, Color> allElementColors = new Hashtable<Element, Color>();
		allElementColors.putAll(staticElementColors);
		allElementColors.putAll(elementColors);
		return allElementColors;
	}

	// -------------------------------------------------------------------------

	private Map<Element, Color> staticElementColors = new Hashtable<Element, Color>();

	public void setStaticElementColor(Element element, Color color) {
		staticElementColors.put(element, color);
	}

	public void removeStaticElementColor(Element element) {
		staticElementColors.remove(element);
	}

	// -------------------------------------------------------------------------

	private Selection selection = new Selection();

	public Selection getSelection() {
		return selection;
	}

	// -------------------------------------------------------------------------

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		elementColors.clear();
		for (Feature feature : features) {
			feature.setElementColors(e, elementColors);
		}
		if (!elementColors.isEmpty()) {
			repaint();
		}
		// ---------------------------------------------------------------------
		for (Feature feature : features) {
			Cursor cursor = feature.getCursor(e);
			if (cursor != null) {
				this.setCursor(cursor);
				return;
			}
		}
		this.setCursor(activeCursor);
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void refresh(MouseEvent e) {
		mouseMoved(e);
	}

	public void mousePressed(MouseEvent e) {
		mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
