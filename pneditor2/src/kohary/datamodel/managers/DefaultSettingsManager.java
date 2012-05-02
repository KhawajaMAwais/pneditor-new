/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.managers;

import kohary.datamodel.dapi.Resolutions;
import kohary.datamodel.template.basic.image.BasicTemplate;
import kohary.datamodel.template.basic.image.BussinesTemplate;
import kohary.datamodel.template.basic.image.Template;
import kohary.datamodel.util.FontSettings;

/**
 *
 * @author Godric
 */
public class DefaultSettingsManager {
    
    FontSettings fontSetings = new FontSettings();

    public FontSettings getFontSetings() {
        return fontSetings;
    }
    //-------------------------------------------------
    
   Template defaultTemplate = new BussinesTemplate();

    public Template getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(Template defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }
    //-----------------------------------------------------
    
    Resolutions resolutions = new Resolutions();

    public Resolutions getResolutions() {
        return resolutions;
    }
    
    
   
   
    
    
}
