package net.matmas.pneditor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import kohary.datamodel.DatamodelCreator;

/**
 *
 * @author matmas
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                UIManager.getDefaults().put("ToolTip.hideAccelerator", Boolean.TRUE);
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                try {
                    String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
                    if (systemLookAndFeel.equals("javax.swing.plaf.metal.MetalLookAndFeel")) {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    } else {
                        UIManager.setLookAndFeel(systemLookAndFeel);
                    }
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }
                PNEditor.getInstance();
           



//				PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
//
//				CollectionTools.getFirstElement(petriNet.getPlaces().get(0).getConnectedArcs()).is

//				petriNet.getMarking().getMarkingAfterFiring(t)

//				System.out.println("Places:");
//				for (Place place : petriNet.getPlaces()) {
//					System.out.println(place.getId() + place.getLabel());
//				}
//				System.out.println("Transitions:");
//				for (Transition transition : petriNet.getTransitions()) {
//					System.out.println(transition.getId() + transition.getLabel());
//				}
            }
        });
    }
}
