/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.matmas.pneditor.DynSound;

import java.util.ArrayList;

/**
 *
 * @author Samo
 */
public class DirectedGraph {

    private ArrayList<InvariantsMatrix> nodes;
    private ArrayList<GraphArc> arcs;

    public DirectedGraph() {
        nodes = new ArrayList<InvariantsMatrix>();
        arcs = new ArrayList<GraphArc>();
    }

    public boolean addStartNode(InvariantsMatrix node) {
        if (!nodes.isEmpty()) {
            return false;
        }
        nodes.add(node);
        return true;
    }

    public boolean addNode(InvariantsMatrix from, InvariantsMatrix to) {
        int i = 0;
        int j = 0;
        boolean b2 = true;
        for (i = 0; i < nodes.size(); i++) {
            b2 = true;
            for (j = 0; j < nodes.get(i).height(); j++) {
                if (nodes.get(i).getPositionValue(j, 0) != from.getPositionValue(j, 0)) {
                    b2 = false;
                }
            }
            if (b2) {
                break;
            }
        }
        if (!b2) {
            return false;
        }

        nodes.add(to);
        arcs.add(new GraphArc(from, to));
        return true;
    }

    public void log(String s) {
        System.out.println(s);
    }

    public ArrayList<GraphArc> getArcs() {
        return arcs;
    }

    public ArrayList<InvariantsMatrix> getNodes() {
        return nodes;
    }

    public ArrayList<InvariantsMatrix> getParents(InvariantsMatrix a) {
        ArrayList<InvariantsMatrix> result = new ArrayList<InvariantsMatrix>();
        result.add(a);
        for (int i = 0; i < result.size(); i++) {
            for (InvariantsMatrix m : this.getNodes()) {
                for (GraphArc g : getArcs()) {
                    if (g.isArc(m, result.get(i))) {
                        boolean contains = false;
                        for (InvariantsMatrix node : result) {
                            if (node.equals(m)) {
                                contains = true;
                            }
                        }
                        if (!contains) {
                            result.add(m);
                        }
                    }
                }
            }
        }
        return result;
    }
}
