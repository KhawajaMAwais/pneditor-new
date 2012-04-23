package xesloganalyzer;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author XY
 */
public class XESanalyze {
    private ArrayList<XESEvent> events = new ArrayList<XESEvent>();
    private ArrayList<XESEvent> estarts = new ArrayList<XESEvent>();
    private ArrayList<XESEvent> eends = new ArrayList<XESEvent>();
    private ArrayList<XESResource> resources = new ArrayList<XESResource>();
    private ArrayList<XESResource> rstarts = new ArrayList<XESResource>();
    private ArrayList<XESResource> rends = new ArrayList<XESResource>();
    private int counttrace =0;
    private int countevents =0;
    private int estartcount = 0;
    private int eendcount = 0;
    private int rstart = 0;
    private int rend = 0;
    private int rcount = 0;
    private XLog log = null;
    private Object keyn = new Object();
    private Object keyr = new Object();
    private Object keyt = new Object();
    private Object keyl = new Object();
    private XLogInfo info = null;

    public XESanalyze(XLog log) {
        this.log = log;
        this.info = XLogInfoFactory.createLogInfo(this.log);
        this.counttrace = info.getNumberOfTraces();
        this.countevents = info.getNumberOfEvents();
        this.keyn="concept:name";
        this.keyr="org:resource";
        this.keyt="time:timestamp";
        this.keyl="lifecycle:transition";
        analyze();
    }
    
    private void analyze(){
        
        for (int i = 0; i < info.getNumberOfTraces(); i++) {
            XTrace tracee = log.get(i);
            for (int j = 0; j < tracee.size(); j++) {
             
             int isthere=0;
             int isthereres=0;
                
             for(XESEvent event: events){    
                if(event.getName().equals(tracee.get(j).getAttributes().get(keyn).toString())){    
                    event.setCount(event.getCount()+1);
                    isthere++;
                }
                
             }
             
             if(isthere==0){
                XESEvent newevent = new XESEvent(tracee.get(j).getAttributes().get(keyn).toString(),tracee.get(j).getAttributes().get(keyt).toString(), tracee.get(j).getAttributes().get(keyr).toString(), tracee.get(j).getAttributes().get(keyl).toString());    
                newevent.setCount(newevent.getCount()+1);
                events.add(newevent);  
             }
             
             for(XESResource resource: resources){    
                if(resource.getName().equals(tracee.get(j).getAttributes().get(keyr).toString())){
                    resource.setCount(resource.getCount()+1);
                    isthereres++;
                    rcount++; 
                }
            } 
             
            if(isthereres==0){
                XESResource newresource = new XESResource(tracee.get(j).getAttributes().get(keyr).toString());
                newresource.setCount(newresource.getCount()+1);
                resources.add(newresource);
                rcount++;
            } 
     
             
             if(j==0){
                 isthere=0;
                 for(XESEvent event: estarts){
                   if(event.getName().equals(tracee.get(j).getAttributes().get(keyn).toString())){
                     event.setCounts(event.getCounts()+1);
                     estartcount++;
                     isthere++;
                   }
                 }
                
               if(isthere==0){
                XESEvent newevent = new XESEvent(tracee.get(j).getAttributes().get(keyn).toString(),tracee.get(j).getAttributes().get(keyt).toString(), tracee.get(j).getAttributes().get(keyr).toString(), tracee.get(j).getAttributes().get(keyl).toString());    
                newevent.setCounts(newevent.getCounts()+1);
                estarts.add(newevent);
                estartcount++;
               }                             
             }
             
             
             if(j==0){
                 isthereres=0;
                 for(XESResource resource: rstarts){
                   if(resource.getName().equals(tracee.get(j).getAttributes().get(keyr).toString())){
                     resource.setCounts(resource.getCounts()+1);
                     rstart++;
                     isthereres++;
                   }
                 }
                
               if(isthere==0){
                XESResource newresource = new XESResource(tracee.get(j).getAttributes().get(keyr).toString());
                newresource.setCounts(newresource.getCounts()+1);
                rstarts.add(newresource);
                rstart++;
               }                             
             }
             
             
             if(j==(tracee.size()-1)){
                 isthere=0;
                 for(XESEvent event: eends){
                   if(event.getName().equals(tracee.get(j).getAttributes().get(keyn).toString())){
                     event.setCounte(event.getCounte()+1);
                     eendcount++;
                     isthere++;
                   }
                 }
                
               if(isthere==0){
                XESEvent newevent = new XESEvent(tracee.get(j).getAttributes().get(keyn).toString(),tracee.get(j).getAttributes().get(keyt).toString(), tracee.get(j).getAttributes().get(keyr).toString(), tracee.get(j).getAttributes().get(keyl).toString());    
                newevent.setCounte(newevent.getCounte()+1);
                eends.add(newevent);
                eendcount++;
               }                             
             }
             
             if(j==(tracee.size()-1)){
                 isthereres=0;
                 for(XESResource resource: rends){
                   if(resource.getName().equals(tracee.get(j).getAttributes().get(keyr).toString())){
                     resource.setCounte(resource.getCounte()+1);
                     rend++;
                     isthereres++;
                   }
                 }
                
               if(isthereres==0){
                XESResource newresource = new XESResource(tracee.get(j).getAttributes().get(keyr).toString());
                newresource.setCounte(newresource.getCounte()+1);
                rends.add(newresource);
                rend++;
               }                             
             }
             
             
            }
        }
        
    }
    
    

    public int getCountevents() {
        return countevents;
    }

    public int getCounttrace() {
        return counttrace;
    }

    public int getEendcount() {
        return eendcount;
    }

    public ArrayList<XESEvent> getEends() {
        Collections.sort(eends, new Comparator(){
 
            public int compare(Object o1, Object o2) {
                XESEvent e1 = (XESEvent) o1;
                XESEvent e2 = (XESEvent) o2;
               return e2.getCounte() - e1.getCounte();
            }
 
        });
        return eends;
    }

    public int getEstartcount() {
        return estartcount;
    }

    public ArrayList<XESEvent> getEstarts() {
        Collections.sort(estarts, new Comparator(){
 
            public int compare(Object o1, Object o2) {
                XESEvent e1 = (XESEvent) o1;
                XESEvent e2 = (XESEvent) o2;
               return e2.getCounts() - e1.getCounts();
            }
 
        });
        return estarts;
    }

    public ArrayList<XESEvent> getEvents() {
        Collections.sort(events, new Comparator(){
 
            public int compare(Object o1, Object o2) {
                XESEvent e1 = (XESEvent) o1;
                XESEvent e2 = (XESEvent) o2;
               return e2.getCount() - e1.getCount();
            }
 
        });
       
        return events;
    }

    public int getRcount() {
        return rcount;
    }

    public int getRend() {
        return rend;
    }

    public ArrayList<XESResource> getRends() {
        Collections.sort(rends, new Comparator(){
            public int compare(Object o1, Object o2) {
                XESResource e1 = (XESResource) o1;
                XESResource e2 = (XESResource) o2;
               return e2.getCounte() - e1.getCounte();
            }
 
        });
        return rends;
    }

    public int getRstart() {
        return rstart;
    }

    public ArrayList<XESResource> getRstarts() {
        Collections.sort(rstarts, new Comparator(){
 
            public int compare(Object o1, Object o2) {
                XESResource e1 = (XESResource) o1;
                XESResource e2 = (XESResource) o2;
               return e2.getCounts() - e1.getCounts();
            }
 
        });
        return rstarts;
    }

    public ArrayList<XESResource> getResources() {
        Collections.sort(resources, new Comparator(){
 
            public int compare(Object o1, Object o2) {
                XESResource e1 = (XESResource) o1;
                XESResource e2 = (XESResource) o2;
               return e2.getCount() - e1.getCount();
            }
        });
        return resources;
    }
    
    
}
