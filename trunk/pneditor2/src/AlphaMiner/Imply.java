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
    private NextRelation next;
    private NextBack nextback;
    private TwoLoop twoloop;
    private TwoLoopBack twoloopback;

    public Imply(NextRelation next, NextBack nextback, TwoLoop twoloop, TwoLoopBack twoloopback) {
        this.next = next;
        this.nextback = nextback;
        this.twoloop = twoloop;
        this.twoloopback = twoloopback;
        
        for(DoubleRelation dr : this.next.getNext()){
            if(!nextback.isThere(dr)||twoloop.isThere(dr)||twoloopback.isThere(dr)){
                MultiRelation mr = new MultiRelation();
                mr.getStart().add(dr.getStart());
                mr.getEnd().add(dr.getEnd());
                this.imlply.add(mr);
            }
        }
        createAlphabet();
    }
    
    public boolean isThere(String start, String end){
       
            for(MultiRelation mr : this.imlply){
                if(mr.getStart().get(0).equals(start)&&mr.getEnd().get(0).equals(end)){
                    return true;
                }
                
            }
            return false;
        }

    public ArrayList<MultiRelation> getImlply() {
        return imlply;
    }

    public ArrayList<String> getImplyalphabet() {
        
        return implyalphabet;
    }
    
    private void createAlphabet(){
        for(MultiRelation mr:this.imlply){
            for(String starte: mr.getStart()){
                if(!isInAlphabet(starte)){
                    implyalphabet.add(starte);
                }   
            }
            for(String ende: mr.getEnd()){
                if(!isInAlphabet(ende)){
                    implyalphabet.add(ende);
                }   
            }
        }
    }
    
    public boolean isInAlphabet(String newWord){
        for(String word:implyalphabet){
            if(word.equals(newWord)){
                return true;
            }
        }
        return false;
    }
    
    
}
