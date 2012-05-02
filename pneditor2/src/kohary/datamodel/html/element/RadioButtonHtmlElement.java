/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.Input;
import kohary.datamodel.dapi.RadioButtonGroup;

/**
 *
 * @author Godric
 */
public class RadioButtonHtmlElement extends HtmlElement{
    private RadioButtonGroup group;

    public RadioButtonHtmlElement(Attribute attribute, CssDocument cssDocument, Tag div,RadioButtonGroup group) {
        super(attribute, cssDocument, div);
        this.group=group;
         setUpLabel();
         DRadioButton button = (DRadioButton) attribute.getInput();
         String selected = "";
         if(button.getSelected()){
             selected = "checked=checked";
         }
                Input input = attribute.getInput();
         String disabled = (input.isDisabled())?"disabled=disabled":"";
        div.add(new Tag("input", "type=radio name=${" +attribute.getInput().getVariable().getLabel()+ "} value="+button.getValue()+" "+selected +" "+disabled, true));
    }
    
}
