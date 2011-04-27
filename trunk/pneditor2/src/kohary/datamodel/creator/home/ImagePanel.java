/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import javax.swing.JPanel;

/**
 *
 * @author Godric
 */
public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2= (Graphics2D)g ;
        g2.getDeviceConfiguration().createCompatibleImage(100, 100, Transparency.TRANSLUCENT);
        g2.drawImage(img, 0, 0, null);

    }
}
