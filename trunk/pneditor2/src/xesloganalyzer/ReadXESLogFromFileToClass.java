/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 *
 * @author XY
 */
public class ReadXESLogFromFileToClass {
    
    private XLog log;
    private XESLog xeslog = new XESLog();
    private Object keyn = new Object();
    private Object keyr = new Object();
    private Object keyt = new Object();
    private Object keyl = new Object();
    private Object keyd = new Object();
    private XLogInfo info = null;

    public ReadXESLogFromFileToClass(XLog log) {
        this.log = log;
        this.info = XLogInfoFactory.createLogInfo(this.log);
        this.keyn="concept:name";
        this.keyr="org:resource";
        this.keyt="time:timestamp";
        this.keyl="lifecycle:transition";
        this.keyd = "description";
        this.xeslog.setName(info.getLog().getAttributes().get(keyn).toString());
        this.xeslog.setXlog(log);
        search();
    }
    
    private void search(){
         
         for (int i = 0; i < info.getNumberOfTraces(); i++) {
                XTrace tracee = log.get(i);
                String description = "UNDEFINED";
                if(tracee.getAttributes().get(keyd)!=null){
                    description = tracee.getAttributes().get(keyd).toString();
                }
                XESTrace trace = new XESTrace(tracee.getAttributes().get(keyn).toString(), description);
                 for (int j = 0; j < tracee.size(); j++) {
                     trace.addEventToTrace(new XESEvent(tracee.get(j).getAttributes().get(keyn).toString(),tracee.get(j).getAttributes().get(keyt).toString(), tracee.get(j).getAttributes().get(keyr).toString(), tracee.get(j).getAttributes().get(keyl).toString()));
                 }
                 this.xeslog.addTraceToLog(trace);
         }
        
    }

    public XESLog getXeslog() {
        return xeslog;
    }
    
    
}
