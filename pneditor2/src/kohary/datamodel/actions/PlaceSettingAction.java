/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kohary.datamodel.position.PlaceSetFrame;
import kohary.datamodel.util.GraphicsTools;
import net.matmas.pneditor.PNEditor;



/**
 *
 * @author Godric
 */
public class PlaceSettingAction extends Action implements ActionListener {

 

    public PlaceSettingAction() {       
                String name = "Association";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, name);
    }

    public void actionPerformed(ActionEvent e) {
       
            PlaceSetFrame frame = new PlaceSetFrame(PNEditor.getInstance().getDocument().getPetriNet());
            frame.getCanvas().loadArcs();
           

    }
}
