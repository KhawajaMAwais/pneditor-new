/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class DImage extends Element implements Design,Serializable {
    
    private static int idCounter = 0;
    private  File path;

    public DImage(Point start, File path) {
          idCounter++;
        this.id = idCounter;
        setStart(start);       
        this.path = path;
        setSize();
    }
    
        private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //----------------------------------
    private ImageType type;

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
        changeType(type);
    }
    
   

    //-------------------------------------
    
   
    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        try {
            ImageIcon image = GraphicsTools.getImageIcon(path.getPath());           
            image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, getStart().x, getStart().y);
         
        } catch (MalformedURLException ex) {
            Logger.getLogger(DImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setSize() {
        try {
            ImageIcon image = GraphicsTools.getImageIcon(path.getPath());
            setSize(image.getIconWidth(), image.getIconHeight());
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(DImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPath() {
        return path.getPath();
    }
    
    public File getPathFile(){
        return path;
    }

 public String getName(){
     return path.getName();
 }
 
     
    private void changeType(ImageType type){
        
        DataModel curentDatamodel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
            
            switch(type){
                case Header:{
                   Point start = new Point(0,0);
                   Point end = new Point(getWidth(),getHeight());
                   setStart(start);
                   setEnd(end);
                   setFixed(true);
                }
                case Footer:{
                   Point start = new Point(0,curentDatamodel.getPage().getResolution().getHeight()-getHeight());
                   Point end = new Point(curentDatamodel.getPage().getResolution().getWidth(),curentDatamodel.getPage().getResolution().getHeight());
                   setStart(start);
                   setEnd(end);
                   setFixed(true);
                }
                    
            }           
            
        
    }
    
    
}
