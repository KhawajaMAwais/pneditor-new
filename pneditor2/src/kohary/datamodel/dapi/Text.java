/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.FontSettings;
import kohary.datamodel.util.StringTool;

/**
 *
 * @author Godric
 */
public abstract class Text extends Element {

    static int idCounter = 0;

    public Text() {
        idCounter++;
        this.id = idCounter;
    }
    //-------------------------------------
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //------------------------------------
    public int getIdCounter() {
        return id;
    }

    abstract public void draw(Graphics g, DrawingOptions drawingOptions);
    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;

    }
    //----------------------------------------------------
    private FontSettings fontSettings = DatamodelCreator.getInstance().getDefaultSettingsManager().getFontSetings().clone();

    public FontSettings getFontSettings() {
        return fontSettings;
    }

}
