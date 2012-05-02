/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.position;

import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;

/**
 *
 * @author Godric
 */
public abstract class Node extends ElementVV{
  
    public abstract boolean containPoint(Point point);
    public abstract void draw(Graphics g);

}
