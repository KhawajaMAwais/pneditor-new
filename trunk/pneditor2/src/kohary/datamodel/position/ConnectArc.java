/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.position;

import java.awt.Graphics;
import java.awt.Point;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.util.GraphicsTools;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;


/**
 *
 * @author Godric
 */
public class ConnectArc extends Node {

    private DataModel dataModel;
    private Transition transition;
    private RoleDefinitionProperty role;
////////////////////////////////////////////////////////////

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public RoleDefinitionProperty getRole() {
        return role;
    }

    public void setRole(RoleDefinitionProperty role) {
        this.role = role;
    }

    ///////////////////////////////////////////////////////
    public ConnectArc() {
    }

    public ConnectArc(Point start, Point destination) {
        this.start = start;
        this.destination = destination;

    }

    public void draw(Graphics g) {
        //  Graphics2D g2 = (Graphics2D) g;
        g.drawLine(start.x, start.y, destination.x, destination.y);

    }
    public static final int nearTolerance = 5;

    public boolean containPoint(Point point) {
        Point previous = getStart();

        return GraphicsTools.isPointNearSegment(previous, destination, point, nearTolerance);
    }
}
