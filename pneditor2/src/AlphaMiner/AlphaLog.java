/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaMiner;

import java.util.ArrayList;
import xesloganalyzer.XESEvent;
import xesloganalyzer.XESLog;
import xesloganalyzer.XESTrace;

/**
 *
 * @author XY
 */
public class AlphaLog {
    
    private XESLog xlog;
    private ArrayList<AlphaTrace> traces = new ArrayList<AlphaTrace>();
    private ArrayList<String> alphabet = new ArrayList<String>();

    public AlphaLog(XESLog xlog) {
        this.xlog = xlog;
        for(XESTrace trace : xlog.getTraces()){
            AlphaTrace atrace = new AlphaTrace();
            for(XESEvent event : trace.getEvents()){
                atrace.getEvents().add(new AlphaEvent(event.getName()));
            }
            this.traces.add(atrace);
        }
        createAlphabet();
    }

    public ArrayList<AlphaTrace> getTraces() {
        return traces;
    }

    public void setTraces(ArrayList<AlphaTrace> traces) {
        this.traces = traces;
    }

    public XESLog getXlog() {
        return xlog;
    }

    public void setXlog(XESLog xlog) {
        this.xlog = xlog;
    }
    
    public void removeEvent(String eventToRemove){
        for(AlphaTrace trace : this.getTraces()){
            trace.removeEvent(eventToRemove);
        }
    }
    
    public void createAlphabet(){
        for(AlphaTrace trace:this.traces){
            for(AlphaEvent event : trace.getEvents()){
                if(!isInAlphabet(event.getName())){
                    alphabet.add(event.getName());
                }
            }
        }
    }

    public ArrayList<String> getAlphabet() {     
        return alphabet;
    }
    
    public boolean isInAlphabet(String newWord){
        for(String word:alphabet){
            if(word.equals(newWord)){
                return true;
            }
        }
        return false;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }
    
    
    
    
}
