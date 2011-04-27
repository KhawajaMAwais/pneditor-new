/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.CanvasPanel;
import kohary.datamodel.creator.home.MainDataModelFrame;
import kohary.datamodel.creator.position.PlaceSetFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class PlaceSettingAction extends Action implements ActionListener {

    private int select;
    private CanvasPanel canvas;

    public PlaceSettingAction(CanvasPanel canvas, int select) {
        this.canvas = canvas;
        this.select = select;
       if (select == 1) {
                String name = "Visual";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/arc.gif"));
		putValue(SHORT_DESCRIPTION, name);
       }
       else {
            	String name = "Manual";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/hamer.gif"));
		putValue(SHORT_DESCRIPTION, name);
       }
    }

    public void actionPerformed(ActionEvent e) {
        if (select == 1) {
            PlaceSetFrame frame = new PlaceSetFrame(canvas.getSelectPanel().getPetriNet(), canvas.getSelectPanel().getListModel());
            frame.getCanvas().loadArcs();
            canvas.getPlacePanel().setVisible(false);
            canvas.getSelectPanel().getShowModelPanel().getPlace().setVisible(false);
        }
        if (select == 2) {
            if (canvas.getPlacePanel().isVisible()) {
                canvas.getPlacePanel().setVisible(false);
                canvas.getSelectPanel().getShowModelPanel().getPlace().setVisible(false);
            } else {
                canvas.getPlacePanel().setVisible(true);
                canvas.getSelectPanel().getShowModelPanel().getPlace().setVisible(true);

            }
        }
    }
}
