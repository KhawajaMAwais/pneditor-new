/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.util.LinkedList;
import java.util.List;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class CssDocument {

    private CssGlobalCssUnits cssGlobalUnits;
    private List<CssUnit> cssUnits = new LinkedList<CssUnit>();
    private DataModel dataModel;

    public CssDocument(DataModel dataModel) {
        this.dataModel = dataModel;
        cssGlobalUnits = new CssGlobalCssUnits(dataModel);
    }

      

    public List<CssUnit> getCssUnit() {
        
        return cssUnits;
    }

    @Override
    public String toString() {
        String ouptut = "";
        for (CssUnit cssUnit : cssGlobalUnits.getGlobalCssUnits()) {
            ouptut += cssUnit;
            ouptut += "\n";
        }
        for (CssUnit cssUnit : cssUnits) {
            ouptut += cssUnit;
            ouptut += "\n";
        }
        return ouptut;
    }
}
