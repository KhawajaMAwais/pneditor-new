package xesloganalyzer;


import java.sql.Timestamp;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author XY
 */
public class XESEvent{
    
    private String name = "";
    private String time;
    private String resource = null;
    private String lifecycle = null;
    private int count=0;
    private int counts=0;
    private int counte=0;

    public XESEvent(String name,String time, String resource, String lifecycle) {
        this.name = name;
        this.time = time;
        this.resource = resource;
        this.lifecycle = lifecycle;
    }

    public XESEvent(String name,String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCounte() {
        return counte;
    }

    public void setCounte(int counte) {
        this.counte = counte;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
    
    
    
    
    
    
}
