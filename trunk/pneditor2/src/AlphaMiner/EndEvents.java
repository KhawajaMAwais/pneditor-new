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
public class EndEvents {
    
    private ArrayList<String> endevents = new ArrayList<String>();
    private AlphaLog log;

    public EndEvents(AlphaLog log) {
        this.log = log;
        for(int i=0;i<log.getTraces().size();i++){
            if(!isInAlphabet(log.getTraces().get(i).getEvents().get(log.getTraces().get(i).getEvents().size()-1).getName())){
                this.endevents.add(log.getTraces().get(i).getEvents().get(log.getTraces().get(i).getEvents().size()-1).getName());
            }
        } 
    }   

    public ArrayList<String> getEndEvents() {     
        return endevents;
    }
    
    public boolean isInAlphabet(String newWord){
        for(String word:endevents){
            if(word.equals(newWord)){
                return true;
            }
        }
        return false;
    }
}
