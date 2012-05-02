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
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.ImageType;

/**
 *
 * @author Godric
 */
public class ImageHtmlElement {

    private DImage image;
    private CssDocument cssDocument;
    private Tag div;
    private CssUnit cssUnit = new CssUnit();

    public ImageHtmlElement(DImage image, CssDocument cssDocument, Tag div) {
        this.image = image;
        this.cssDocument = cssDocument;
        this.div = div;
        this.setUpCSSPosition();

    }

    private void setUpCSSPosition() {
        cssUnit.getTags().put("image" + image.getId(), TagType.id);
        cssUnit.getAttributes().add(new CssAttribute("position", "absolute"));
        cssUnit.getAttributes().add(new CssAttribute("z-index", "-10"));
        if (image.getType() == ImageType.Background) {
            cssUnit.getAttributes().add(new CssAttribute("width",  "100%"));
            cssUnit.getAttributes().add(new CssAttribute("height",  "100%"));
        }else{
        cssUnit.getAttributes().add(new CssAttribute("top", Integer.toString(image.getStart().y) + "px"));
        cssUnit.getAttributes().add(new CssAttribute("left", Integer.toString(image.getStart().x) + "px"));
        }
        cssDocument.getCssUnit().add(cssUnit);
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
