/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Godric
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssAttribute;
import kohary.datamodel.CssDocument;
import kohary.datamodel.CssUnit;
import kohary.datamodel.Tag;
import kohary.datamodel.TagType;
import kohary.datamodel.dapi.Attribute;

public abstract class HtmlElement{

    private Attribute attribute;
    private CssDocument cssDocument;
    private Tag div;
    private CssUnit cssUnit = new CssUnit();

    public HtmlElement(Attribute attribute, CssDocument cssDocument, Tag div) {
        this.attribute = attribute;
        this.cssDocument = cssDocument;
        this.div = div;
        this.setUpCSSPosition();
      
    }
    
    public void setUpLabel(){
        Tag label = new  Tag("div","class=label");
        label.add(attribute.getLabel().getText());
        div.add(label);
    }

    private void setUpCSSPosition() {
        cssUnit.getTags().put("attr" + Integer.toString(attribute.getId()), TagType.id);
        cssUnit.getAttributes().add(new CssAttribute("position", "absolute"));
        cssUnit.getAttributes().add(new CssAttribute("top", Integer.toString(attribute.getInput().getStart().y) + "px"));
        cssUnit.getAttributes().add(new CssAttribute("left", Integer.toString(attribute.getInput().getStart().x-attribute.getLabel().getWidth()) + "px"));
        cssDocument.getCssUnit().add(cssUnit);
    }
    

    public Attribute getAttribute() {
        return attribute;
    }

    public CssDocument getCssDocument() {
        return cssDocument;
    }

    public CssUnit getCssUnit() {
        return cssUnit;
    }

    public Tag getDiv() {
        return div;
    }
    
    
}
