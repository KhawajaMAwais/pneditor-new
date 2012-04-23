/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author XY
 */
public class XESTrace {
    
     private String name;
     private String description;
     private String identificator="";
     private ArrayList<XESEvent> events = new ArrayList<XESEvent>();

    public XESTrace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<XESEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<XESEvent> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificator() {
        return identificator;
    }
    
    public void addEventToTrace(XESEvent event){
        this.events.add(event);
    }
    
    public void removeEventFromTraceViaParam(String name, String time, String resource, String lifecycle){
        XESEvent remevent = null;
        for(XESEvent event: this.events){
            if(event.getName().equals(name)&&event.getTime().equals(time)&&event.getResource().equals(resource)&&event.getLifecycle().equals(lifecycle)){
                remevent = event;
            }
        }
        this.events.remove(remevent);
    }
    
    public void removeEventFromTraceViaEvent(XESEvent EventToRemove){
        XESEvent remevent = null;
        for(XESEvent event: this.events){
            if(EventToRemove.equals(event)){
                remevent = event;
            }
        }
        this.events.remove(remevent);
    }
    
    public void createIdentificator(){
        for(XESEvent event: this.events){
            identificator+=event.getName();
        }
    }
     
}
