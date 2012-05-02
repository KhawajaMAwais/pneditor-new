/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Godric
 */
public abstract class Element implements Serializable{

    private Point start = new Point();

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }
//----------------------------------------------
    private Point end = new Point();

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
//---------------------------------------------

    public int getWidth() {
        return (int) Math.abs(getEnd().getX() - getStart().getX());
    }

    public int getHeight() {
        return (int) Math.abs(getEnd().getY() - getStart().getY());
    }

    public Point getSize() {
        return new Point(getWidth(), getHeight());
    }

    public void setSize(Point point) {
        setSize((int) point.getX(), (int) point.getY());
    }

    public void setSize(int width, int height) {

        setEnd(new Point((int) getStart().getX() + width, (int) getStart().getY() + height));

    }
    
    public Point getCenter() {
		return new Point(
				(int)getStart().getX() + ((int)getEnd().getX() - (int)getStart().getX()) / 2,
				(int)getStart().getY() + ((int)getEnd().getY() - (int)getStart().getY()) / 2
				);
	}

	
   

    public boolean containsPoint(Point point) {
        int l = Math.min((int) getStart().getX(), (int) getEnd().getX());
        int t = Math.min((int) getStart().getY(), (int) getEnd().getY());
        int w = Math.abs(getWidth());
        int h = Math.abs(getHeight());
        if (point.getX() >= l && point.getX() < l + w && point.getY() >= t && point.getY() < t + h) {
            return true;
        } else {
            return false;
        }
    }

    public void moveBy(int dx, int dy) {
        start = new Point((int) getStart().getX() + dx, (int) getStart().getY() + dy);
        end = new Point((int) getEnd().getX() + dx, (int) getEnd().getY() + dy);
    }

    public void moveBy(Point point) {
        moveBy((int) point.getX(), (int) point.getY());
    }
//-----------------------------------------------------------------
    public void resizing(Point point) {
        resizing((int) point.getX(), (int) point.getY());
    }
    
    private boolean isSizeOnMinHeight(){
        if((getHeight()<15))
            return true;
        else return false;
    }
    private boolean isSizeOnMinWidth(){
        if((getHeight()<40))
            return true;
        else return false;
    }

    public void resizing(int dx, int dy) {
        if(!isSizeOnMinHeight()||dy>0)
        end = new Point((int) getEnd().getX(), (int) getEnd().getY() + dy);
        if(!isSizeOnMinWidth()||dx>0)
        end = new Point((int) getEnd().getX() + dx, (int) getEnd().getY() );
    }
    private boolean resizable = false;

    public boolean isResizable() {
        return resizable;
    }

    public void setIsResizable(boolean isResizable) {
        this.resizable = isResizable;
    }

    public boolean isOnResizableZone(Point point) {
        if(!isResizable()){
            return false;
        }
        if ((Math.abs(getEnd().y - point.y) < 10) && (Math.abs(getEnd().x - point.x) < 10))
        {
            System.out.println("Sizable");
            return true;
        } else {
            return false;
        }
    }
    
    	public Rectangle getBounds() {
		int x = Math.min((int)getStart().getX(), (int)getEnd().getX());
		int y = Math.min((int)getStart().getY(),(int) getEnd().getY());
		int width = getWidth();
		int height = getHeight();
		Rectangle bounds = new Rectangle(x, y, width, height);
		return bounds;
	}
        
            private boolean fixed=false;

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public void changeFix(){
        if(isFixed())
            fixed=false;
        else
            fixed=true;
        
        
    }
}
