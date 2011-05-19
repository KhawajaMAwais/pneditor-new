/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.DynSound;

/**
 *
 * @author Samo
 */
public class GraphArc {
    private InvariantsMatrix from;
    private InvariantsMatrix to;

    public GraphArc(InvariantsMatrix from, InvariantsMatrix to) {
        this.from = from;
        this.to = to;
    }

    public InvariantsMatrix getFrom() {
        return from;
    }

    public void setFrom(InvariantsMatrix from) {
        this.from = from;
    }

    public InvariantsMatrix getTo() {
        return to;
    }

    public void setTo(InvariantsMatrix to) {
        this.to = to;
    }

    public boolean isArc(InvariantsMatrix from, InvariantsMatrix to){
        if (this.from.equals(from) && this.to.equals(to)) return true;
        else return false;
    }

    

}
