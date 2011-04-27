/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.position;

import kohary.datamodel.creator.util.DataModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class ConnectArc extends Node{
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

    public ConnectArc() {}
    public ConnectArc(Point start,Point destination){
        this.start=start;
        this.destination=destination;
        
    }
    public void draw(Graphics g) {
      //  Graphics2D g2 = (Graphics2D) g;
        g.drawLine(start.x, start.y, destination.x, destination.y);
        
    }

    @Override
    public boolean containPoint(Point point) {
        throw new UnsupportedOperationException("");
    }


    

}
