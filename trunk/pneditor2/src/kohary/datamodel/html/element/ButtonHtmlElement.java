/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.Attribute;

/**
 *
 * @author Godric
 */
public class ButtonHtmlElement extends HtmlElement {

    public ButtonHtmlElement(Attribute attribute, CssDocument cssDocument, Tag div) {
        super(attribute, cssDocument, div);
         setUpLabel();
          div.add(new Tag("input", "type=submit name=" + attribute.getLabel().getText() + attribute.getId(), true));
         
    }


    
    
}
