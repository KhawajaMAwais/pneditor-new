/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.features.DataModelsFeature;
import kohary.datamodel.features.DraggingFeature;
import kohary.datamodel.features.ElementMakerFeature;
import kohary.datamodel.features.Feature;
import kohary.datamodel.features.InputPopUpFeature;
import kohary.datamodel.features.RulerFeature;
import kohary.datamodel.features.SelectionDrawingFeature;
import kohary.datamodel.features.SetBackGroundFeature;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener ,Scrollable {

    private int maxUnitIncrement = 1;
    private ModellingBoard modelingBoard;
    private List<Feature> features = new ArrayList<Feature>();
    
    public Canvas(ModellingBoard modelingBoard) {
        this.modelingBoard = modelingBoard;
        this.setBackground(Color.white);
        
       
        features.add(new SetBackGroundFeature(this));
        features.add(new ElementMakerFeature(this));
        features.add(new DataModelsFeature(this));
        features.add(new DraggingFeature(this));
        features.add(new SelectionDrawingFeature(this));
        features.add(new RulerFeature(this));
        features.add(new InputPopUpFeature(this));
      

        addMouseMotionListener(this);
        addMouseListener(this);
        setAutoscrolls(true);
    }
    //----------------Selected datamodel------------------------

    public DataModel getSelectedDataModel() {
        return (DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
    }
    //------------------------------------------
    private Selection selection = new Selection();

    public Selection getSelection() {
        return selection;
    }
    //------------------------------------------
    private Elements elements = new Elements();

    public Elements getElements() {
        return elements;
    }
    //-----------------------------------------
    private Cursor activeCursor = Cursor.getDefaultCursor();

    public void setActiveCursor(Cursor activeCursor) {
        this.activeCursor = activeCursor;
        this.setCursor(activeCursor);
    }
    
    //---------------------------------------------------
    
    private DrawingOptions drawingOptions = new DrawingOptions();

    public DrawingOptions getDrawingOptions() {
        return drawingOptions;
    }
    
    //--------------------------------------------------
    public ModellingBoard getModelingBoard() {
        return modelingBoard;
    }
    
    //-------------------------------------------------------------
    	private Point viewTranslation = new Point();

	public Point getViewTranslation() {
		return viewTranslation;
	}

	public void setViewTranslation(Point viewTranslation) {
		this.viewTranslation = viewTranslation;
	}
    
    
    //---------------------------------------------------
    
    

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g);

        //g.translate(viewTranslation.getX(), viewTranslation.getY());
        for (Feature feature : features) {
            feature.drawBackground(g);
        }
        for (Feature feature : features) {
            feature.drawMainLayer(g);
        }
        for (Feature feature : features) {
            feature.drawForeground(g);
        }
    }
    //---------------------------------------------------------
       public Dimension getPreferredScrollableViewportSize() {
        return DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage().getResolution().getDimension();
    }

    // ZDROJ: http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/ScrollDemoProject/src/components/ScrollablePicture.java
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        int currentPosition = 0;
        if (orientation == SwingConstants.HORIZONTAL) {
            currentPosition = visibleRect.x;
        } else {
            currentPosition = visibleRect.y;
        }

        //Return the number of pixels between currentPosition
        //and the nearest tick mark in the indicated direction.
        if (direction < 0) {
            int newPosition = currentPosition
                    - (currentPosition / maxUnitIncrement)
                    * maxUnitIncrement;
            return (newPosition == 0) ? maxUnitIncrement : newPosition;
        } else {
            return ((currentPosition / maxUnitIncrement) + 1)
                    * maxUnitIncrement
                    - currentPosition;
        }
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width - maxUnitIncrement;
        } else {
            return visibleRect.height - maxUnitIncrement;
        }
    }
//---------------------------------------------------------------------

    public boolean getScrollableTracksViewportWidth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getScrollableTracksViewportHeight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //---------------------------------------------------------
  
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}
