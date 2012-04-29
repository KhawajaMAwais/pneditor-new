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
public class NextRelation {
 
    private ArrayList<DoubleRelation> next = new ArrayList<DoubleRelation>();
    private AlphaLog log;

    public NextRelation(AlphaLog log) {
        this.log = log;
        for(AlphaTrace trace : this.log.getTraces()){
            for(int i =0; i<(trace.getEvents().size()-1);i++){
                String t1 = trace.getEvents().get(i).getName();
                String t2 = trace.getEvents().get(i+1).getName();
                if(!isThere(new DoubleRelation(t1, t2))){
                        this.next.add(new DoubleRelation(t1, t2));
                    }
            }
        }    
    }
    
    public boolean isThere(DoubleRelation dr){
        for(DoubleRelation drnext : this.next){
            if(drnext.getStart().equals(dr.getStart())&&drnext.getEnd().equals(dr.getEnd())){
                return true;
            }
        }
        return false;
    }
    
     public boolean isThereOposit(DoubleRelation dr){
        for(DoubleRelation drnext : this.next){
            if(drnext.getStart().equals(dr.getEnd())&&drnext.getEnd().equals(dr.getStart())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<DoubleRelation> getNext() {
        return next;
    }
    
    
    
}
