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
public class OneLoop {
    
    private ArrayList<String> oneloop = new ArrayList<String>();
    private AlphaLog log;

    public OneLoop(AlphaLog log) {
        this.log = log;
         for(AlphaTrace trace : this.log.getTraces()){
             for(int i =0; i<(trace.getEvents().size()-1);i++){
                String t1 = trace.getEvents().get(i).getName();
                String t2 = trace.getEvents().get(i+1).getName();
                if(t1.equals(t2)){
                    if(!isThere(t1)){
                        this.oneloop.add(t1);
                    }
                }
             }
         }
        
    }
    
    public boolean isThere(String alhpa){
        for(String word:this.oneloop){
            if(word.equals(alhpa)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getOneloop() {
        return oneloop;
    }
    
     
}
