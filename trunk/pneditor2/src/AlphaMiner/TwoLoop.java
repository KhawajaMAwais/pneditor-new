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
public class TwoLoop {

    AlphaLog log;
    private ArrayList<DoubleRelation> twoloops = new ArrayList<DoubleRelation>();

    public TwoLoop(AlphaLog log) {
        this.log = log;
        for(AlphaTrace trace : this.log.getTraces()){
            for(int i =0; i<(trace.getEvents().size()-2);i++){
                String t1 = trace.getEvents().get(i).getName();
                String t2 = trace.getEvents().get(i+1).getName();
                String t3 = trace.getEvents().get(i+2).getName();
                if(t1.equals(t3)&&!t2.equals(t1)){
                    if(!isThere(new DoubleRelation(t1, t2))){
                        this.twoloops.add(new DoubleRelation(t1, t2));
                    }
                    if(!isThere(new DoubleRelation(t2, t1))){
                        this.twoloops.add(new DoubleRelation(t2, t1));
                    }
                }
            }
        }
    }

    public ArrayList<DoubleRelation> getTwoloops() {
        return twoloops;
    }
    
    public boolean isThere(DoubleRelation dr){
        for(DoubleRelation twoloop : this.getTwoloops()){
            if(twoloop.getStart().equals(dr.getStart())&&twoloop.getEnd().equals(dr.getEnd())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isThereOposit(DoubleRelation dr){
        for(DoubleRelation twoloop : this.getTwoloops()){
            if(twoloop.getStart().equals(dr.getEnd())&&twoloop.getEnd().equals(dr.getStart())){
                return true;
            }
        }
        return false;
    }
    
}
