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
public class NextBack {

    private ArrayList<DoubleRelation> nextBack = new ArrayList<DoubleRelation>();
    private NextRelation nextrelation;

    public NextBack(NextRelation nextrelation) {
        this.nextrelation = nextrelation;
        for(DoubleRelation dr : this.nextrelation.getNext()){
            if(!this.nextrelation.isThereOposit(dr)&&!dr.getStart().equals(dr.getEnd())){
                if(!isThere(dr)){
                nextBack.add(dr);
                }
                if(!isThere(new DoubleRelation(dr.getEnd(), dr.getStart()))){
                nextBack.add(new DoubleRelation(dr.getEnd(), dr.getStart()));
                }
            }
        }
    }
    
    public boolean isThere(DoubleRelation dr){
        for(DoubleRelation drnextback : this.nextBack){
            if(drnextback.getStart().equals(dr.getStart())&&drnextback.getEnd().equals(dr.getEnd())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<DoubleRelation> getNextBack() {
        return nextBack;
    }
    
    
    
}
