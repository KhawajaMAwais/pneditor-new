/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.io.Serializable;
import kohary.datamodel.util.DrawingOptions;



/**
 *
 * @author Godric
 */
public interface  Design extends Serializable{
      public void draw(Graphics g,DrawingOptions drawingOptions);
}
