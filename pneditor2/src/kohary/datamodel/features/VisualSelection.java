package kohary.datamodel.features;

import java.awt.Color;
import java.awt.Graphics;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;


/**
 *
 * @author matmas
 */
class VisualSelection extends Element {
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		GraphicsTools.setDashedStroke(g);
		g.drawRect(Math.min((int)getStart().getX(),(int) getEnd().getX()), Math.min((int)getStart().getY(), (int)getEnd().getY()),getWidth(), getHeight());
		GraphicsTools.setDefaultStroke(g);
	}


}
