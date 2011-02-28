package net.matmas.pneditor.features;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import net.matmas.pneditor.Canvas;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author matmas
 */
public class ToolSelectionFeature extends Feature implements MouseWheelListener {

	public ToolSelectionFeature(Canvas canvas) {
		super(canvas);
		canvas.addMouseWheelListener(this);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() == 1) {
			if (PNEditor.getInstance().getToolSelector().isSelectedTool_Place())
				PNEditor.getInstance().getToolSelector().selectTool_Transition();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Transition())
				PNEditor.getInstance().getToolSelector().selectTool_Arc();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Arc())
				PNEditor.getInstance().getToolSelector().selectTool_Token();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Token())
				PNEditor.getInstance().getToolSelector().selectTool_Subnet();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet())
				PNEditor.getInstance().getToolSelector().selectTool_Place();
			else
				PNEditor.getInstance().getToolSelector().selectTool_Place();
		}
		else if (e.getWheelRotation() == -1) {
			if (PNEditor.getInstance().getToolSelector().isSelectedTool_Transition())
				PNEditor.getInstance().getToolSelector().selectTool_Place();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Arc())
				PNEditor.getInstance().getToolSelector().selectTool_Transition();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Token())
				PNEditor.getInstance().getToolSelector().selectTool_Arc();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Subnet())
				PNEditor.getInstance().getToolSelector().selectTool_Token();
			else if (PNEditor.getInstance().getToolSelector().isSelectedTool_Place())
				PNEditor.getInstance().getToolSelector().selectTool_Subnet();
			else
				PNEditor.getInstance().getToolSelector().selectTool_Subnet();
		}
	}

	public void drawForeground(Graphics g) {}
	public void drawMainLayer(Graphics g) {}
	public void drawBackground(Graphics g) {}

}
