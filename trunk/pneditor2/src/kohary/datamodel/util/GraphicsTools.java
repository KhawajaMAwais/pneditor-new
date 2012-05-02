/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 *
 * @author Godric
 */
public class GraphicsTools {

    private final static String resourcesDir = "/kohary/datamodel/images/";
    private final static String imageDir = "/";

    public static ImageIcon getIcon(String fileName) {
        return new ImageIcon(GraphicsTools.class.getResource(resourcesDir + fileName));
    }

    public static ImageIcon getTemplateIcon(String path, String fileName) {
        return new ImageIcon(GraphicsTools.class.getResource(path + fileName));
    }
    public static ImageIcon getImageIcon(String path) throws MalformedURLException {
        return new ImageIcon(path);
    }

    public static void setDashedStroke(Graphics g) {
        final float dash1[] = {4.0f};
        final BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f);
        ((Graphics2D) g).setStroke(dashed);
    }

    public static void setDefaultStroke(Graphics g) {
        final BasicStroke defaultStroke = new BasicStroke();
        ((Graphics2D) g).setStroke(defaultStroke);
    }

    public static boolean isPointNearSegment(Point from, Point to, Point testPos, int nearTolerance) {
        Rectangle r = new Rectangle((int)testPos.getX() - nearTolerance, (int)testPos.getY() - nearTolerance, 2 * nearTolerance, 2 * nearTolerance);
        return r.intersectsLine(from.getX(), from.getY(), to.getX(), to.getY());
    }
}
