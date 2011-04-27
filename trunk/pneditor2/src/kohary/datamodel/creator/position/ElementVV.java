/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.position;

import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;

/**
 *
 * @author Godric
 */
public abstract class ElementVV {
    protected Point centerPoint,nodePoint;
    protected Point start,destination;
    protected int id;
    protected String name;
    

    /////////////////////////////////////
        public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }
//////////////////////////////////////////////
    public Point getCenterPoint() {
        return centerPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Point getNodePoint() {
        return nodePoint;
    }

    public void setNodePoint(Point nodePoint) {
        this.nodePoint = nodePoint;
    }

    /////////////////////////////////////

    public abstract boolean containPoint(Point point);
    public abstract void draw(Graphics g);


}
