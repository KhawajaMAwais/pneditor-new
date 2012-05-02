/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godric
 */
public class Resolutions {
    
    private List<Resolution> resolutions = new ArrayList<Resolution>();

    public Resolutions() {
        resolutions.add(new Resolution(800, 600));
        resolutions.add(new Resolution(1024, 768));
        resolutions.add(new Resolution(1280 , 800));
        resolutions.add(new Resolution(1600 , 1200));
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }
    
    
    
}
