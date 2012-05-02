/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.Input;

/**
 *
 * @author Godric
 */
public class SelectHtmlElement extends HtmlElement {

    private Tag selectTag;

    public SelectHtmlElement(Attribute attribute, CssDocument cssDocument, Tag div) {
        super(attribute, cssDocument, div);
        setUpLabel();
        Input input = attribute.getInput();
        String disabled = (input.isDisabled()) ? "disabled=disabled" : "";
        selectTag = new Tag("select", "name=${" + attribute.getInput().getVariable().getLabel() + "} "+disabled);

        setUpSelectTag();
        div.add(selectTag);
    }

    private void setUpSelectTag() {
        DComboBox comboBox = (DComboBox) this.getAttribute().getInput();
        for (ComboBoxItem item : comboBox.getItems()) {
            Tag option = new Tag("option", "value=" + item.getValue());
            option.add(item.getItem());
            selectTag.add(option);
        }

    }

    public Tag getSelectTag() {
        return selectTag;
    }
}
