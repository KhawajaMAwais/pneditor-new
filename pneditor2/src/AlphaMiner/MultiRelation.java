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
    private String identificator;
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

    public void setStart(ArrayList<String> start) {
        this.start = start;
    }
    

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public boolean isChcecked() {
        return chcecked;
    }

    public void setChcecked(boolean chcecked) {
        this.chcecked = chcecked;
    }
    
    
    
}
