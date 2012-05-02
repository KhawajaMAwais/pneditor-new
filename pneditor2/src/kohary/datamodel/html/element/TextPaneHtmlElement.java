/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.html.element;

import kohary.datamodel.CssDocument;
import kohary.datamodel.Tag;
import kohary.datamodel.dapi.DTextPane;

/**
 *
 * @author Godric
 */
public class TextPaneHtmlElement extends HtmlText {

    public TextPaneHtmlElement(DTextPane textPane, CssDocument cssDocument, Tag div) {
        super(textPane, cssDocument, div);     
       
       div.add(textPane.getText());
    }
}
