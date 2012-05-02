/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.Input;



/**
 *
 * @author Godric
 */
public class TextFieldHtmlElement extends HtmlElement{

    public TextFieldHtmlElement(Attribute attribute, CssDocument cssDocument, Tag div) {
        super(attribute, cssDocument, div);
         setUpLabel();
         Input input = attribute.getInput();
         String disabled = (input.isDisabled())?"disabled=disabled":"";
         div.add(new Tag("input", "type=text name=${"+attribute.getInput().getVariable().getLabel()+"} value=${"+attribute.getInput().getVariable().getLabel()+"} "+disabled ,true ));
    }
    
   
    
}
