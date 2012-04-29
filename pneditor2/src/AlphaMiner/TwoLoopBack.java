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
public class TwoLoopBack {

    private ArrayList<DoubleRelation> twoloopsback = new ArrayList<DoubleRelation>();
    private TwoLoop twoloop;

    public TwoLoopBack(TwoLoop twoloop) {
        this.twoloop = twoloop;
        for(DoubleRelation dr : this.twoloop.getTwoloops()){
           if(twoloop.isThereOposit(dr)){
               if(!isThere(dr)){
               this.twoloopsback.add(dr);
               }
               DoubleRelation opdr = new DoubleRelation(dr.getEnd(), dr.getStart());
               if(!isThere(opdr)){
               this.twoloopsback.add(opdr);
               }
           }
        }
       
    }

    public ArrayList<DoubleRelation> getTwoloopsBack() {
        return twoloopsback;
    }
    
    public boolean isThere(DoubleRelation dr){
        for(DoubleRelation twoloopback : this.twoloopsback){
            if(twoloopback.getStart().equals(dr.getStart())&&twoloopback.getEnd().equals(dr.getEnd())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isThereOposit(DoubleRelation dr){
        for(DoubleRelation twoloop : this.twoloopsback){
            if(twoloop.getStart().equals(dr.getEnd())&&twoloop.getEnd().equals(dr.getStart())){
                return true;
            }
        }
        return false;
    }
    
}
