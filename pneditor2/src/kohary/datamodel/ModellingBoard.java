/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

/**
 *
 * @author Godric
 */
public class ModellingBoard extends JPanel {

    private Canvas canvas = new Canvas(this);
    private FontSelectorPanel fontSelectorPanel = new FontSelectorPanel();
  

    public ModellingBoard() {
       
        this.setLayout(new BorderLayout());

        this.add(fontSelectorPanel, BorderLayout.NORTH);

        this.add(canvas, BorderLayout.CENTER);




    }



    public Canvas getCanvas() {
        return canvas;
    }

    public FontSelectorPanel getFontSelectorPanel() {
        return fontSelectorPanel;
    }

    public void setFontSelectorPanel(FontSelectorPanel fontSelectorPanel) {
        this.fontSelectorPanel = fontSelectorPanel;
    }
}
