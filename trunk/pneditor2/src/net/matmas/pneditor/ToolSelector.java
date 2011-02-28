package net.matmas.pneditor;

import java.awt.Cursor;
import net.matmas.pnapi.Marking;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class ToolSelector {

	private enum Tool {
		select, place, transition, arc, token, subnet, fire
	}

	private Tool currentTool = Tool.select;
	private Marking markingBeforeSimulation = null;

	// -------------------------------------------------------------------------

	public void selectTool_Select() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectSelectionToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(Cursor.getDefaultCursor());
		this.currentTool = Tool.select;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Select() {
		return PNEditor.getInstance().getMainFrame().selectSelectionToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Place() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectPlaceToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("pneditor/canvas/place.gif", new java.awt.Point(16, 16)));
		this.currentTool = Tool.place;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Place() {
		return PNEditor.getInstance().getMainFrame().selectPlaceToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Transition() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectTransitionToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("pneditor/canvas/transition.gif", new java.awt.Point(16, 16)));
		this.currentTool = Tool.transition;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Transition() {
		return PNEditor.getInstance().getMainFrame().selectTransitionToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Arc() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectArcToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("pneditor/canvas/arc.gif", new java.awt.Point(0, 0)));
		this.currentTool = Tool.arc;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Arc() {
		return PNEditor.getInstance().getMainFrame().selectArcToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Token() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectTokenToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("pneditor/canvas/token.gif", new java.awt.Point(16, 0)));
		this.currentTool = Tool.token;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Token() {
		return PNEditor.getInstance().getMainFrame().selectTokenToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Subnet() {
		restoreMarkingIfNeeded();
		PNEditor.getInstance().getMainFrame().selectSubnetToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		this.currentTool = Tool.subnet;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Subnet() {
		return PNEditor.getInstance().getMainFrame().selectSubnetToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	public void selectTool_Fire() {
		markingBeforeSimulation = new Marking(PNEditor.getInstance().getDocument().getPetriNet().getMarking());

		PNEditor.getInstance().getMainFrame().selectFireToolToggleButton.setSelected(true);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("pneditor/canvas/fire.gif", new java.awt.Point(15, 15)));
		this.currentTool = Tool.fire;
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	public boolean isSelectedTool_Fire() {
		return PNEditor.getInstance().getMainFrame().selectFireToolToggleButton.isSelected();
	}

	// -------------------------------------------------------------------------

	private void restoreMarkingIfNeeded() {
		if (currentTool == Tool.fire) {
			PNEditor.getInstance().getDocument().getPetriNet().setMarking(markingBeforeSimulation);
		}
	}

}
