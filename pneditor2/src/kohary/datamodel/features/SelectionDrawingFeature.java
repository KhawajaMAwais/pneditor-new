package kohary.datamodel.features;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.DTextPane;
import kohary.datamodel.dapi.Design;
import kohary.datamodel.dapi.Element;
import net.matmas.pneditor.PNEditor;

public class SelectionDrawingFeature extends Feature implements MouseListener, MouseMotionListener {

    public SelectionDrawingFeature(Canvas canvas) {
        super(canvas);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }
    private boolean selecting = false;
    private VisualSelection visualSelection = new VisualSelection();
    private Set<Element> newSelection = new HashSet<Element>();

    public void mousePressed(MouseEvent e) {
        Point clickedPoint = getClickedPoint(e);
        Element clickedElement = getClickedElement(clickedPoint);
        int mouseButton = e.getButton();
        
        if(clickedElement instanceof DImage){
            DImage image= (DImage) clickedElement;
            if(image.isFixed())
                clickedElement = null;
        }

        if (DatamodelCreator.getInstance().getSelectionManager().isSelectSelect()) {
            if (mouseButton == MouseEvent.BUTTON1) {
                if (clickedElement == null) {
                    selecting = true;
                    visualSelection.setStart(clickedPoint);
                    visualSelection.setEnd(clickedPoint);
                    canvas.repaint();
                    if (e.isShiftDown()) {
                        newSelection.addAll(canvas.getSelection().getElements());
                    } else {
                        canvas.getSelection().clear();
                        newSelection.clear();
                    }
                }
            }
            if (mouseButton == MouseEvent.BUTTON3) {
                if (clickedElement == null) {
                    canvas.getSelection().clear();
                }
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (selecting) {
            visualSelection.setEnd(getClickedPoint(e));

            canvas.getSelection().clear();
            canvas.getSelection().addAll(newSelection);
            for (Attribute attribute : DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributes()) {

                if (visualSelection.getBounds().contains(attribute.getInput().getBounds())) {
                    canvas.getSelection().add(attribute.getInput());
                }
            }
            for (Design designElement : DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage().getDesignElements()) {
                if (designElement instanceof DTextPane) {
                    DTextPane textPane = (DTextPane) designElement;
                    if (visualSelection.getBounds().contains(textPane.getBounds())) {
                        canvas.getSelection().add(textPane);
                    }
                }
                if (designElement instanceof DImage) {
                    DImage image = (DImage) designElement;
                    if (visualSelection.getBounds().contains(image.getBounds())&& !image.isFixed()) {
                        canvas.getSelection().add(image);
                    }
                }
            }
            canvas.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (selecting) {
            selecting = false;
            canvas.repaint();
        }
    }

    public void drawForeground(Graphics g) {
        if (selecting) {
            visualSelection.draw(g);
        }
    }

    public void drawBackground(Graphics g) {
    }

    public void drawMainLayer(Graphics g) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}