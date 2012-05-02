/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import kohary.datamodel.Canvas;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public abstract class Feature {
    
    protected Canvas canvas;
    
    public Feature(Canvas canvas){
        this.canvas=canvas;
    }
    public abstract void drawBackground(Graphics g);
    public abstract void drawMainLayer(Graphics g);
    public abstract void drawForeground(Graphics g);
    
    public Point getClickedPoint(MouseEvent e){
        return new Point(e.getX(), e.getY());
    }
    
	protected Element getClickedElement(Point clickedPoint) {
		return canvas.getSelectedDataModel().getElementByLocation(clickedPoint);
	}
   // protected Element getClickedElement(Point clickedPoint){
       // return DatamodelCreator.getInstance().
    //}
}
