/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import kohary.datamodel.DatamodelCreator;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class DataModelAction extends Action {

 
    public JFrame frame;
    public DataModelAction(JFrame frame) {
        this.frame=frame;
       
        putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Preferences16.gif"));
        setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
  //      if(PNEditor.getInstance().getDocument().getPetriNet().getProperties().getFilteredByClass(RoleDefinitionProperty.class).size()>0){
     SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.getDefaults().put("ToolTip.hideAccelerator", Boolean.TRUE);
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				try {
					String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
					if (systemLookAndFeel.equals("javax.swing.plaf.metal.MetalLookAndFeel")) {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					}
					else {
						UIManager.setLookAndFeel(systemLookAndFeel);
					}
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (UnsupportedLookAndFeelException ex) {
				}
				DatamodelCreator.getInstance().getMainFrame().setVisible(true);
			}
		});
        }

}

