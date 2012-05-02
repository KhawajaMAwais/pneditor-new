/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.Input;

/**
 *
 * @author Godric
 */
public class CheckboxHtmlElement extends HtmlElement {

    public CheckboxHtmlElement(Attribute attribute, CssDocument cssDocument, Tag div) {
        super(attribute, cssDocument, div);
        setUpLabel();
           DCheckBox button = (DCheckBox) attribute.getInput();
         String checked = "";
         if(button.getDefaultValue()){
             checked = "checked=checked";
         }
                  Input input = attribute.getInput();
         String disabled = (input.isDisabled())?"disabled=disabled":"";
        div.add(new Tag("input", "type=checkbox name=" +attribute.getInput().getVariable().getLabel()+ " value=${"+attribute.getInput().getVariable().getLabel()+"} "+checked+" "+disabled, true));

    }
}
