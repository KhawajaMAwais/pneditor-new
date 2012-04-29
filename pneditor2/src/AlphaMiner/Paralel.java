/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaMiner;

import java.util.ArrayList;

/**
 *
 * @author XY
 */
public class Paralel {

    private ArrayList<DoubleRelation> paralel = new ArrayList<DoubleRelation>();
    private NextRelation next;
    private NextBack nextback;
    private TwoLoop twoloop;
    private TwoLoopBack twoloopback;

    public Paralel(NextRelation next, NextBack nextback, TwoLoop twoloop, TwoLoopBack twoloopback) {
        this.next = next;
        this.nextback = nextback;
        this.twoloop = twoloop;
        this.twoloopback = twoloopback;
        
        for(DoubleRelation dr : this.next.getNext()){
            if(nextback.isThere(dr)&&(!twoloop.isThere(dr)&&!twoloopback.isThere(dr))){
                this.paralel.add(dr);
            }
        }
        
    }

    public ArrayList<DoubleRelation> getParalel() {
        return paralel;
    }
    
    
    
    
    
}
