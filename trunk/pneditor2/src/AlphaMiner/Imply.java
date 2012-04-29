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
public class Imply {
    
    private ArrayList<MultiRelation> imlply = new ArrayList<MultiRelation>();
    private ArrayList<String> implyalphabet = new ArrayList<String>();
    private NextBack next;
    private NextBack nextback;
    private TwoLoop twoloop;
    private TwoLoopBack twoloopback;

    public Imply(NextBack next, NextBack nextback, TwoLoop twoloop, TwoLoopBack twoloopback) {
        this.next = next;
        this.nextback = nextback;
        this.twoloop = twoloop;
        this.twoloopback = twoloopback;
        
        
    }
    
    
    
}
