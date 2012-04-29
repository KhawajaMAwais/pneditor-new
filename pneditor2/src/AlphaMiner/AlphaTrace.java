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
public class AlphaTrace {
    private ArrayList<AlphaEvent> events = new ArrayList<AlphaEvent>();

    public AlphaTrace() {
    }

    public ArrayList<AlphaEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<AlphaEvent> events) {
        this.events = events;
    }
    
    public void removeEvent(String eventToRemove){
        ArrayList<AlphaEvent> eventsToRemove = new ArrayList<AlphaEvent>();
        for(AlphaEvent event : this.getEvents()){
            if(event.equals(eventToRemove)){
                eventsToRemove.add(event);
            }
        }
        this.getEvents().removeAll(eventsToRemove);
    }
}
