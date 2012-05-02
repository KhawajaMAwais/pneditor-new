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
public class MultiRelation {
    
    private ArrayList<String> start = new ArrayList<String>();
    private ArrayList<String> end = new ArrayList<String>();
    private String forloop;
    private boolean chcecked = false;

    public MultiRelation() {
    }

    public ArrayList<String> getEnd() {
        return end;
    }

    public void setEnd(ArrayList<String> end) {
        this.end = end;
    }

    public ArrayList<String> getStart() {
        return start;
    }
    
    public boolean isInStart(String word){
        for(String sw: this.start){
            if(sw.equals(word)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isInEnd(String word){
        for(String ew: this.end){
            if(ew.equals(word)){
                return true;
            }
        }
        return false;
    }

    public void setStart(ArrayList<String> start) {
        this.start = start;
    }

    public String getForloop() {
        return forloop;
    }

    public void setForloop(String forloop) {
        this.forloop = forloop;
    }
    
    

    public boolean isChcecked() {
        return chcecked;
    }

    public void setChcecked(boolean chcecked) {
        this.chcecked = chcecked;
    }
    
     public boolean containsAllElements(MultiRelation m){
        
         int sizes = this.getStart().size();
         int sizesm = m.getStart().size();
         int sizee = this.getEnd().size();
         int sizeem = m.getEnd().size();
         int chcecks = 0;
         int chsizes = 0;
         int chcecke = 0;
         int chsizee = 0;
         if(sizes<=sizesm){
             for(String word : this.getStart()){
                 if(m.isInStart(word)){
                     chcecks++;
                 }
             }
             chsizes = this.getStart().size();
         }
         else{
              for(String word : m.getStart()){
                 if(this.isInStart(word)){
                     chcecks++;
                 }
             }
             chsizes = m.getStart().size();
         }
         
         if(sizee<=sizeem){
             for(String word : this.getEnd()){
                 if(m.isInEnd(word)){
                     chcecke++;
                 }
             }
             chsizee = this.getEnd().size();
         }
         else{
              for(String word : m.getEnd()){
                 if(this.isInEnd(word)){
                     chcecke++;
                 }
             }
             chsizee = m.getEnd().size(); 
         }
        if(chcecks==chsizes&&chcecke==chsizee){
            return true;
        }
        return false;
    }
    
}
