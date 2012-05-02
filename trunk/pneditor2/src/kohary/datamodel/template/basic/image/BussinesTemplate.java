/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.template.basic.image;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.DTextArea;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.util.StringTool;

/**
 *
 * @author Godric
 */
public class BussinesTemplate implements Template {

    private String name = "basic";

    public ImageIcon getHeader() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ImageIcon getFooter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void draw(Graphics g, DrawingOptions drawingOptions) {
    }

    public String getName() {
        return name;
    }

    public void drawDButton(Graphics g, DrawingOptions drawingOptions, DButton button) {
        Color color = null;
        ImageIcon image;
        color = drawingOptions.getColorSetting().get(button);
        Color colorBorder = Color.RED;
     

        g.setColor(color);
        if (color == null) {
                color = new Color(240, 240, 240); //lightgreen
            colorBorder = Color.BLACK;
            image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "submit.png");
        } else {
            image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "submit-selected.png");
        }
        image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) button.getStart().getX(), (int) button.getStart().getY());
        g.setColor(colorBorder);
        g.drawString(button.getValue(), button.getCenter().x - (StringTool.getStringWidth(g.getFontMetrics(), button.getValue()) / 2), button.getCenter().y + (StringTool.getStringHeight(g.getFontMetrics()) / 4));

    }

    public void drawDCheckBox(Graphics g, DrawingOptions drawingOptions, DCheckBox checkBox) {
        Color color = null;
        ImageIcon image;
        color = drawingOptions.getColorSetting().get(checkBox);
        Color colorBorder = Color.RED;
     
        g.setColor(color);

        if (checkBox.getDefaultValue()) {
            if (color == null) {
                          color = new Color(240, 240, 240); //lightgreen
                        colorBorder = Color.BLACK;
                image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "checkbox_checked.png");
            } else {
                image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "checkbox_checked-selected.png");
            }
            image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) checkBox.getStart().getX(), (int) checkBox.getStart().getY());

        } else {
            if (color == null) {
                image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "checkbox_unc.png");
            } else {
                image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "checkbox_unc-selected.png");
            }
            image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) checkBox.getStart().getX(), (int) checkBox.getStart().getY());

        }

    }

    public void drawDComboBox(Graphics g, DrawingOptions drawingOptions, DComboBox comboBox) {
        Color color = null;
        ImageIcon image;
        color = drawingOptions.getColorSetting().get(comboBox);
        Color colorBorder = Color.RED;
      
        g.setColor(color);
        if (color == null) {
                 color = new Color(240, 240, 240); //lightgreen
            colorBorder = Color.BLACK;
            image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "combobox.png");
        } else {
            image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "combobox-selected.png");
        }
        image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) comboBox.getStart().getX(), (int) comboBox.getStart().getY());


    }

    public void drawDRadioButton(Graphics g, DrawingOptions drawingOptions, DRadioButton radioButton) {
            Color color = null;
        ImageIcon image;
         color = drawingOptions.getColorSetting().get(radioButton);
        Color colorBorder = Color.RED;
       
        g.setColor(color);

        if (radioButton.getSelected()) {
            if(color == null){
              
             image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "radiobutton_checked.png");
            }else
                image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "radiobutton_checked-selected.png");  
            image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) radioButton.getStart().getX(), (int) radioButton.getStart().getY());

        } else {
            if(color==null){
                    color = new Color(240, 240, 240); //lightgreen
            colorBorder = Color.BLACK;
             image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "radiobutton.png");
            }else
              image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "radiobutton-selected.png");  
            image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) radioButton.getStart().getX(), (int) radioButton.getStart().getY());

        }

    }

    public void drawDTextArea(Graphics g, DrawingOptions drawingOptions, DTextArea textArea) {
        Color color = drawingOptions.getColorSetting().get(textArea);
        Color colorBorder = Color.RED;
        if (color == null) {
            color = Color.WHITE; //lightgreen
            colorBorder = Color.BLACK;
        }
        g.setColor(color);
        g.fillRect((int) textArea.getStart().getX(), (int) textArea.getStart().getY(), textArea.getWidth() - 1, textArea.getHeight() - 1);
        g.setColor(colorBorder);
        g.drawRect((int) textArea.getStart().getX(), (int) textArea.getStart().getY(), textArea.getWidth(), textArea.getHeight());
        g.drawLine((int) textArea.getEnd().x - 10, (int) textArea.getEnd().y, (int) textArea.getEnd().x, (int) textArea.getEnd().y - 10);
    }

    public void drawDTextField(Graphics g, DrawingOptions drawingOptions, DTextField textField) {
          Color color = null;
        ImageIcon image;
         color = drawingOptions.getColorSetting().get(textField);
        Color colorBorder = Color.RED;
       
        if(color == null){
              color = new Color(240, 240, 240); //lightgreen
            colorBorder = Color.BLACK;
         image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "textfield.png");
        }else
            image = GraphicsTools.getTemplateIcon(templateDir + getName() + "/image/", "textfield-selected.png");
        image.paintIcon(DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas(), g, (int) textField.getStart().getX(), (int) textField.getStart().getY());
        String label = (textField.getVariable() != null) ? textField.getVariable().getLabel().toString() : "no variable";
        g.drawString("#{" + label + "}", textField.getStart().x + 10, textField.getStart().y + 17);

    }
}
