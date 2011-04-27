/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.attribute.AttributeBox;
import kohary.datamodel.creator.attribute.AttributeLine;
import kohary.datamodel.creator.attribute.AttributePanel;
import kohary.datamodel.creator.attribute.AttributeRightsLine;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Godric
 */
public class ConfirmAttributeAction implements ActionListener {

    private AttributePanel atbPanel;
    private AttributeBox atbBox;

    public ConfirmAttributeAction(AttributePanel atbPanel, AttributeBox atbBox) {
        this.atbPanel = atbPanel;
        this.atbBox = atbBox;

    }

    public void actionPerformed(ActionEvent e) {

        for (Attribute attribute : atbPanel.getAttributes()) {


            for (AttributeLine attributeLine : atbPanel.getAttributeLines()) {


                if (attribute.equals(attributeLine.getAttribute())) {

                    for (AttributeRightsLine attributeRightLine : attributeLine.getRightsLines()) {

                        attribute.getRights().put((String) attributeRightLine.getTransiotionBox().getSelectedItem(), (String) attributeRightLine.getRightsBox().getSelectedItem());
                    }
                }
            }
        }

        atbBox.getAtCrFrame().getDataModel().setAttributes(atbPanel.getAttributes());
        atbBox.getAtCrFrame().setVisible(false);
        atbBox.getAtCrFrame().getListModel().editableOn();
        atbBox.getAtCrFrame().getListModel().setupShowPanel();
    }
}
