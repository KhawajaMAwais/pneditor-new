/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.position;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class DataModelElement extends Node {
 
    final Color lightGreen = new Color(227, 244, 215);
    
    private DataModel datamodel;
    
    public DataModelElement(Point centerPoint) {
        setCenterPoint(centerPoint);
        setNodePoint(new Point(centerPoint.x+40,centerPoint.y));
        
    }
    public DataModelElement() {}
////////////////////////////////////////////

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.LIGHT_GRAY);
        g2.fillRoundRect(centerPoint.x-40, centerPoint.y-20, 80, 40, 20, 20);
        g2.setPaint(Color.darkGray);
        g2.drawRoundRect(centerPoint.x-40, centerPoint.y-20, 80, 40, 20, 20);
        g2.setPaint(Color.black);
        g2.fillOval(centerPoint.x+40, centerPoint.y, 5, 5);
        g2.drawString(name, centerPoint.x-40, centerPoint.y-20);
        
    }

    @Override
    public boolean containPoint(Point point) {
        Rectangle rectangle = new Rectangle(centerPoint.x-40, centerPoint.y-20, 80, 40);
        if(rectangle.contains(point))
            return true;
        else
            return false;
        
    }

    public DataModel getDatamodel() {
        return datamodel;
    }

    public void setDatamodel(DataModel datamodel) {
        this.datamodel = datamodel;
    }
    
    
}
