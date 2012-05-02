/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Dimension;
import java.io.Serializable;

/**
 *
 * @author Godric
 */
public class Resolution implements Cloneable,Serializable{
    private int width;
    private int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
        

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public Dimension getDimension(){
        return new Dimension(width, height);
    }
    
    @Override
    public String toString(){
        return width+"x"+height;
    }
    
}
