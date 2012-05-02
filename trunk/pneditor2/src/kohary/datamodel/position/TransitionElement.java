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
import net.matmas.pnapi.Transition;

/**
 *
 * @author Godric
 */
public class TransitionElement extends Node {
    final Color lightGreen = new Color(227, 244, 215);
    
    private Transition transition;
    
    
    public TransitionElement(Point centerPoint,Transition transition) {
        setCenterPoint(centerPoint);
        setNodePoint(new Point(centerPoint.x-30,centerPoint.y));
        this.transition = transition;

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(lightGreen);
        g2.fillRect(centerPoint.x-30, centerPoint.y-30, 60, 60);
        g2.setPaint(Color.black);
        g2.drawRect(centerPoint.x-30, centerPoint.y-30, 60, 60);
        g2.drawString(getName(),centerPoint.x-30, centerPoint.y-30);
    }

    @Override
    public boolean containPoint(Point point) {
        Rectangle rectangle = new Rectangle(centerPoint.x-30, centerPoint.y-30, 60, 60);
        if(rectangle.contains(point))
            return true;
        else
            return false;

    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }
    
    
}
