package xesloganalyzer;

import java.util.ArrayList;
import org.deckfour.xes.model.XLog;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author XY
 */
public class XESLog {
    
    private String name;
    private ArrayList<XESTrace> traces = new ArrayList<XESTrace>();
    private XLog xlog;

    public XESLog() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<XESTrace> getTraces() {
        return traces;
    }

    public void setTraces(ArrayList<XESTrace> traces) {
        this.traces = traces;
    }

    public XLog getXlog() {
        return xlog;
    }

    public void setXlog(XLog xlog) {
        this.xlog = xlog;
    }
    
    public void addTraceToLog(XESTrace trace){
        this.traces.add(trace);
    }
    
    public void removeTraceFromLogViaParam(String name, String description){
        XESTrace remtrace = null;
        for(XESTrace trace: this.traces){
            if(trace.getName().equals(name)&&trace.getDescription().equals(description)){
                remtrace = trace;
            }
        }
        this.traces.remove(remtrace);
    }
    
    public void removeTraceFromLogViaTrace(XESTrace traceToRemove){
        XESTrace remtrace = null;
        for(XESTrace trace: this.traces){
            if(trace.equals(traceToRemove)){
                remtrace = trace;
            }
        }
        this.traces.remove(remtrace);
    }
    
    public void removeEventFromLog(String enventname){
        
        for(XESTrace trace : this.getTraces()){
           ArrayList<XESEvent> eventrem = new ArrayList<XESEvent>();
           for(XESEvent event : trace.getEvents()){
               if(event.getName().equals(enventname)){
                   eventrem.add(event);
               }
           }
          trace.getEvents().removeAll(eventrem); 
        }
    }
    
     public void createIndentificatorsForTraces(){
         for(XESTrace trace: this.getTraces()){
             trace.createIdentificator();
         }
     }
     
     public  void createIdentities(){
         ArrayList<XESTrace> identities = new ArrayList<XESTrace>();
         XESTrace actualtrace = null;
         for(int i=0;i<this.getTraces().size();i++){
             actualtrace = this.getTraces().get(i);
             int isthere = 0;
             for(int j=i+1;j<this.getTraces().size();j++){
                 if(actualtrace.getIdentificator().equals(this.getTraces().get(j).getIdentificator())){
                   isthere++;  
                 }
             }
             if(isthere==0){
                 identities.add(actualtrace);
             }
         }
         this.traces=identities;
     }
    
}
