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
public class NoWay {
    private ArrayList<DoubleRelation> noway = new ArrayList<DoubleRelation>();
    private ArrayList<String> alphabet;
    private NextRelation next;

    public NoWay(ArrayList<String> alphabet, NextRelation next) {
        this.alphabet = alphabet;
        this.next = next;
        for(int i = 0; i<alphabet.size();i++){
            for(int j = 0; j<alphabet.size();j++){
                DoubleRelation dr = new DoubleRelation(alphabet.get(i), alphabet.get(j));
                if(!next.isThere(dr)){
                    noway.add(dr);
                }
            }
        }
    }

    public ArrayList<DoubleRelation> getNoway() {
        return noway;
    }
    
     public boolean isThere(DoubleRelation dr){
        for(DoubleRelation drnoway : this.noway){
            if(drnoway.getStart().equals(dr.getStart())&&drnoway.getEnd().equals(dr.getEnd())){
                return true;
            }
        }
        return false;
    }
    
    
    
}
