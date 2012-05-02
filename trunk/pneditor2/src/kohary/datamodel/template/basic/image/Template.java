/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.template.basic.image;

import java.awt.Graphics;
import java.io.Serializable;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.DTextArea;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.dapi.DTextPane;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public interface Template extends Serializable{
     public void drawDButton(Graphics g, DrawingOptions drawingOptions, DButton button);
     public void drawDCheckBox(Graphics g, DrawingOptions drawingOptions, DCheckBox checkBox);
     public void drawDComboBox(Graphics g, DrawingOptions drawingOptions, DComboBox comboBox);
     public void drawDRadioButton(Graphics g, DrawingOptions drawingOptions, DRadioButton radioButton);
     public void drawDTextArea(Graphics g, DrawingOptions drawingOptions, DTextArea textArea);
     public void drawDTextField(Graphics g, DrawingOptions drawingOptions, DTextField textField);
    
     
     public void draw(Graphics g, DrawingOptions drawingOptions);
     public String getName();
     final static String templateDir = "/kohary/datamodel/template/";
     
}
