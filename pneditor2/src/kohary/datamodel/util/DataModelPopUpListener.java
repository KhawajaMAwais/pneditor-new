/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Godric
 */
public class DataModelPopUpListener extends MouseAdapter {

    JPopupMenu popup;
    DataModelList list;

    public DataModelPopUpListener(DataModelList list, JPopupMenu popup) {
        this.popup = popup;
        this.list = list;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
       
    }

    private void maybeShowPopup(MouseEvent e) {
        int index = list.locationToIndex(e.getPoint());
        
        if (SwingUtilities.isRightMouseButton(e)&&(index > -1)) {
            if (e.isPopupTrigger()) {               
                list.setSelectedIndex(index);
                list.valueChanged(null);
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }
}
