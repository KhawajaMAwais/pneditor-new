/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import kohary.datamodel.dapi.Text;
import kohary.datamodel.util.FontSettings;

/**
 *
 * @author Godric
 */
public class ColorToolbarIndicator extends JPanel implements MouseListener, ChangeListener {

    private JColorChooser colorChooser;
    private FontSettings fontSettings;
    private FontSelectorPanel selectorPanel;

    public ColorToolbarIndicator(FontSelectorPanel selectorPanel) {
        this.selectorPanel = selectorPanel;
        setSize(20, 20);
        addMouseListener(this);
        setVisible(true);
       
    }

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    public void mouseClicked(MouseEvent e) {
      
    }

    public void mousePressed(MouseEvent e) {
        fontSettings = selectorPanel.getFontSettings();
        setBackground(fontSettings.getColor());
        colorChooser = new JColorChooser(fontSettings.getColor());
        colorChooser.getSelectionModel().addChangeListener(this);
        colorChooser.setBorder(BorderFactory.createTitledBorder(
                "Choose Text Color"));
        JFrame colorFrame = new JFrame();
        colorFrame.add(colorChooser);
        colorFrame.pack();
        colorFrame.setVisible(true);
        setBackground(colorChooser.getColor());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        System.out.print("Hovnooo");
    }

    public void mouseExited(MouseEvent e) {
    }

    public void stateChanged(ChangeEvent e) {
        fontSettings = selectorPanel.getFontSettings();
        setBackground(colorChooser.getColor());
        fontSettings.setColor(colorChooser.getColor());
    }
}
