/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssAttribute;
import kohary.datamodel.CssDocument;
import kohary.datamodel.CssUnit;
import kohary.datamodel.Tag;
import kohary.datamodel.TagType;
import kohary.datamodel.dapi.Text;

/**
 *
 * @author Godric
 */
public class HtmlText {

    private Text text;
    private CssDocument cssDocument;
    private Tag div;
    private CssUnit cssUnit;
  

    public HtmlText(Text text, CssDocument cssDocument, Tag div) {
        this.text = text;
        this.cssDocument = cssDocument;
        this.div = div;
       
        this.setUpCSSPosition();
    }

    
    
    private void setUpCSSPosition() {
        cssUnit = new CssUnit();
        cssUnit.getTags().put("text" + Integer.toString(text.getIdCounter()), TagType.id);
        cssUnit.getAttributes().add(new CssAttribute("position", "absolute"));
        cssUnit.getAttributes().add(new CssAttribute("top", Integer.toString(text.getStart().y-text.getHeight()) + "px"));
        cssUnit.getAttributes().add(new CssAttribute("left", Integer.toString(text.getStart().x) + "px"));
        cssUnit.getAttributes().add(new CssAttribute("font-family", text.getFontSettings().getFamilyFont()));
        cssUnit.getAttributes().add(new CssAttribute("font-size", Integer.toString(text.getFontSettings().getSizeFont())));
        cssUnit.getAttributes().add(new CssAttribute("color", "rgb("+text.getFontSettings().getRGBColor()+")"));
              
        cssDocument.getCssUnit().add(cssUnit);
    }
    
    
    
    
    
}
