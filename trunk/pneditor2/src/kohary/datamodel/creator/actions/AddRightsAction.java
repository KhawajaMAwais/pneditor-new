/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.attribute.AttributeLine;
import kohary.datamodel.creator.attribute.AttributePanel;
import kohary.datamodel.creator.attribute.AttributeRightsLine;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;
import org.omg.CORBA.MARSHAL;

/**
 *
 * @author Godric
 */
public class AddRightsAction extends Action implements ActionListener {

    private Attribute attribute;
    private AttributePanel atbPanel;
    private AttributeLine line;
    private int index, i;

    public AddRightsAction(AttributeLine line, Attribute attribute, AttributePanel atbPanel) {
        this.atbPanel = atbPanel;
        this.attribute = attribute;
        this.line = line;
             //	String name = "Exit";
	//	putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Preferences16.gif"));
	//	putValue(SHORT_DESCRIPTION, name);
        setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {

        //line.newAttributeRightLine(atbPanel,attribute,line);

        index = 0;

        if (line.getMaxRightLines() > line.getRightsLines().size()) {
            for (Component component : atbPanel.getComponents()) {
                if (component.equals(line)) {
                    i = index;
                }
                index++;

            }
            int isSomeLineOnindex = 0;
            AttributeRightsLine rights = new AttributeRightsLine(attribute, atbPanel);
            for (index = 0; index < line.getMaxRightLines(); index++) {
                for (AttributeRightsLine atbLine : line.getRightsLines()) {
                    if (index == atbLine.getTransiotionBox().getSelectedIndex()) {
                        isSomeLineOnindex = 1;
                    }
                }
                if (isSomeLineOnindex == 0) {
                    rights.getTransiotionBox().setSelectedIndex(index);
                }
                isSomeLineOnindex = 0;
            }

            atbPanel.add(rights, i + 1);
            line.getRightsLines().add(rights);
            atbPanel.revalidate();

        }
    }
}
