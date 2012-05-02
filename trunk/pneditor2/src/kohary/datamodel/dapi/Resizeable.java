/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Point;

/**
 *
 * @author Godric
 */
public interface Resizeable {
    public boolean isResizable();
    public boolean isOnResizableZone(Point point);
}
