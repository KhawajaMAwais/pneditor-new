/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.commands.MoveElementsCommand;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class DraggingFeature extends Feature implements MouseListener, MouseMotionListener {

    public DraggingFeature(Canvas canvas) {
        super(canvas);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }
    private Element draggedElement = null;
    private Point deltaPosition;
    private Point prevDrag;
    private boolean beResizing= false;

    public void mousePressed(MouseEvent e) {
        Point clickedPoint = getClickedPoint(e);
        Element clickedElement = canvas.getSelectedDataModel().getElementByLocation(clickedPoint);
        int mouseButton = e.getButton();
        boolean doubleclick = e.getClickCount() == 2;

        if (DatamodelCreator.getInstance().getSelectionManager().isSelectSelect()) {
            if (mouseButton == MouseEvent.BUTTON1 && !doubleclick) {

                if (!canvas.getSelection().contains(clickedElement)) {
                    if(clickedElement != null)
                    canvas.getSelection().replaceWith(clickedElement);

                }
                
                  draggedElement = getClickedElement(clickedPoint); 
                  
                  //ak je fix nastaveny na true tak ho netahat
                  if(draggedElement!=null)
                  if(draggedElement.isFixed())
                  draggedElement=null;
                  
                  
                deltaPosition = new Point();
                prevDrag = clickedPoint;
                
                if(draggedElement != null)
                 if(draggedElement.isOnResizableZone(clickedPoint)){
                     beResizing=true;
                 }else{
                     beResizing=false;
                 }
              
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (draggedElement != null) {           
            Point clickedPoint = getClickedPoint(e);
            if(beResizing){
                 doResizing(clickedPoint);
                 canvas.repaint();
                  deltaPosition = new Point((int)deltaPosition.getX()+(int)clickedPoint.getX() - (int)prevDrag.getX(),(int) deltaPosition.getY()+(int)clickedPoint.getY() - (int)prevDrag.getY());
                 prevDrag = clickedPoint;
             }else{
            doTheMoving(clickedPoint);
            canvas.repaint();  // redraw canvas to show shape in new position
            deltaPosition = new Point((int)deltaPosition.getX()+(int)clickedPoint.getX() - (int)prevDrag.getX(),(int) deltaPosition.getY()+(int)clickedPoint.getY() - (int)prevDrag.getY());
            prevDrag = clickedPoint;
             }
        }
    }

    public void mouseReleased(MouseEvent e) {
        Point clickedPoint = getClickedPoint(e);
        if (draggedElement != null) {
            doTheMoving(clickedPoint);
            deltaPosition = new Point((int)deltaPosition.getX()+(int)clickedPoint.getX() - (int)prevDrag.getX(),(int) deltaPosition.getY()+(int)clickedPoint.getY() - (int)prevDrag.getY());
            saveTheMoving();
            canvas.repaint();
            draggedElement = null;  // Dragging is finished.
        }
    }
    
    private void doResizing(Point mouse){
        draggedElement.resizing((int)mouse.getX() - (int)prevDrag.getX(), (int)mouse.getY() - (int)prevDrag.getY());
    }

    private void doTheMoving(Point mouse) {
        if (!canvas.getSelection().isEmpty()) {
            for (Element selectedElement : canvas.getSelection()) {
                selectedElement.moveBy((int)mouse.getX() - (int)prevDrag.getX(), (int)mouse.getY() - (int)prevDrag.getY());
            }
        } else {
            draggedElement.moveBy((int)mouse.getX() - (int)prevDrag.getX(), (int)mouse.getY() - (int)prevDrag.getY());
        }
    }

    private void saveTheMoving() {
        if (!deltaPosition.equals(new Point(0, 0))) {
            if (!canvas.getSelection().isEmpty()) {
                for (Element selectedElement : canvas.getSelection()) {
                    selectedElement.moveBy((int)-deltaPosition.getX(), (int)-deltaPosition.getY()); //move back to original positions
                }

                DatamodelCreator.getInstance().getUndoManager().executeCommand(new MoveElementsCommand(canvas.getSelection().getElements(), deltaPosition));
            }
        }
    }

    //---------------------------------------------------------  
    @Override
    public void drawBackground(Graphics g) {
    }

    @Override
    public void drawMainLayer(Graphics g) {
    }

    @Override
    public void drawForeground(Graphics g) {
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
