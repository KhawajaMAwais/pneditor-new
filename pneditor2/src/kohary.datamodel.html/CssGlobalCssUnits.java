/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.util.LinkedList;
import java.util.List;
import kohary.datamodel.dapi.DPage;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class CssGlobalCssUnits {

    private List<CssUnit> globalCssUnits = new LinkedList<CssUnit>();
    private DPage page;

    public CssGlobalCssUnits(DataModel dataModel) {
        this.page = dataModel.getPage();
        setUpGlobalUnits();
        setUpDesignUnits();
    }

    private void setUpGlobalUnits() {
        //-----------------------label css-------------------------------
        CssUnit labelClassCssUnit = new CssUnit();
        labelClassCssUnit.getTags().put("label", TagType.clasz);
        labelClassCssUnit.getAttributes().add(new CssAttribute("position", "relative"));
        labelClassCssUnit.getAttributes().add(new CssAttribute("float", "left"));
        labelClassCssUnit.getAttributes().add(new CssAttribute("margin-right", "10px"));
        globalCssUnits.add(labelClassCssUnit);

        //-----------------------input css-------------------------------
        CssUnit inputClassCssUnit = new CssUnit();
        inputClassCssUnit.getTags().put("div.input", TagType.normal);
        inputClassCssUnit.getAttributes().add(new CssAttribute("position", "relative"));
        inputClassCssUnit.getAttributes().add(new CssAttribute("float", "left"));
        globalCssUnits.add(inputClassCssUnit);
        //--------------------------------------------------------

    }
    
    private void setUpDesignUnits(){
                //-----------------------body css-------------------------------
        CssUnit backgroundCssUnit = new CssUnit();
        backgroundCssUnit.getTags().put("body", TagType.normal);
        backgroundCssUnit.getAttributes().add(new CssAttribute("background-color","rgb("+page.getBackGroundColor().getRed()+""
                + ","+page.getBackGroundColor().getGreen()+","+page.getBackGroundColor().getBlue()+")"));
        backgroundCssUnit.getAttributes().add(new CssAttribute("width","100%"));
        backgroundCssUnit.getAttributes().add(new CssAttribute("height","100%"));
        
        globalCssUnits.add(backgroundCssUnit);
        //--------------------------------------------------------
        
    }

    public List<CssUnit> getGlobalCssUnits() {
        return globalCssUnits;
    }
    
    
    
}
