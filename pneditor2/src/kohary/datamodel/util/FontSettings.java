/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

/**
 *
 * @author Godric
 */
public class FontSettings implements Cloneable,Serializable{
    private int sizeFont=12;
    private int styleFont=Font.PLAIN;
    private String FamilyFont=  "Times New Roman";
    private Color color = Color.BLACK;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
   
               
    
    public String getFamilyFont() {
        return FamilyFont;
    }

    public void setFamilyFont(String FamilyFont) {
        this.FamilyFont = FamilyFont;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    public int getStyleFont() {
        return styleFont;
    }

    public void setStyleFont(int styleFont) {
        this.styleFont = styleFont;
    }
    
    public String getRGBColor(){
        return Integer.toString(color.getRed())+","+Integer.toString(color.getGreen())+","+Integer.toString(color.getBlue());
    }
    
    @Override
  public FontSettings clone() {
        try {
            return (FontSettings) super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }

    
    
    
}
