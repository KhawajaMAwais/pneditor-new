/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.PropertiesPanel;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.DataType;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.dapi.ImageType;

/**
 *
 * @author Godric
 */
public class SetImageTypeAction extends Action {

    private DImage image = null;

    public SetImageTypeAction() {
        String name = "Set type";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        putValue(SHORT_DESCRIPTION, "Set type");

    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        image = (DImage) selectedElement;



        ImageType s = (ImageType) JOptionPane.showInputDialog(
                mainFrame,
                "Choose image type:\n",
                "Set image type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                ImageType.values(),
                image.getType());



        image.setType(s);
        
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
}
