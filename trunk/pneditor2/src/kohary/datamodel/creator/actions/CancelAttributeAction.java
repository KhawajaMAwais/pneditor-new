/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.attribute.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Godric
 */
public class CancelAttributeAction implements ActionListener{
    private AttributePanel atbPanel;
    private AttributeLine atbLine;
    
    public CancelAttributeAction(AttributeLine atbLine,AttributePanel atbPanel) {
        this.atbLine=atbLine;
        this.atbPanel=atbPanel;

    }

    public void actionPerformed(ActionEvent e) {
        for(Component component:atbPanel.getComponents()) {
                for(AttributeRightsLine rightLine:atbLine.getRightsLines()) {
                    if(component.equals(rightLine))
                        atbPanel.remove(component);
                }

            }
        int index = atbPanel.getComponentZOrder(atbLine);       
        atbPanel.remove(atbLine);
        if(index>=1)
        atbPanel.remove(index-1);
        atbPanel.getAttributes().deleteComponent(atbLine.getAttribute());
        atbPanel.revalidate();
        atbPanel.repaint();
        
    }

}
