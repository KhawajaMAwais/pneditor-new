/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.managers;

import java.awt.Cursor;
import java.awt.Point;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SelectionManager {
    
    private enum Tool{
        checkBox,comboBox,radioButton,textField,textArea,button,textPane,select
    }
    
    private Tool currentTool = Tool.select;
    
    private enum Ruler{
        on,off
    }    
    
    private Ruler currentRullerState = Ruler.off;

 
    
    
    public void setRadioButton_selection(){
     //   DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("textField.gif", new java.awt.Point(0,0)));
        this.currentTool=Tool.radioButton;
    }
    public void setComboBox_selection(){
     //   DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("textField.gif", new java.awt.Point(0,0)));
        this.currentTool=Tool.comboBox;
    }
    public void setCheckBox_selection(){
     //   DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("textField.gif", new java.awt.Point(0,0)));
        this.currentTool=Tool.checkBox;
    }
    
    public void setTextField_selection(){
     //   DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().setActiveCursor(GraphicsTools.getCursor("textField.gif", new java.awt.Point(0,0)));
        this.currentTool=Tool.textField;
    }
    
    public void setTextArea_selection(){
        this.currentTool=Tool.textArea;
        
    }
    public void setButton_selection(){
        this.currentTool=Tool.button;
    }
    
    public void setTextPane_selection(){
        this.currentTool=Tool.textPane;
    }
    
    public void setSelect_selection(){
        this.currentTool=Tool.select;
    }
    
    public void switchRuler(){
        if(this.currentRullerState == Ruler.off)
            this.currentRullerState=Ruler.on;
        else
           this.currentRullerState=Ruler.off; 
    }
    
    public boolean isRulerSwitchOn(){
           if(this.currentRullerState == Ruler.on)
               return true;
           else
               return false;
    }
    
    
    public boolean isSelectComboBox(){
        if(this.currentTool==Tool.comboBox)
            return true;
        else
            return false;
    }
    public boolean isSelectCheckBox(){
        if(this.currentTool==Tool.checkBox)
            return true;
        else
            return false;
    }
    public boolean isSelectRadioButton(){
        if(this.currentTool==Tool.radioButton)
            return true;
        else
            return false;
    }
    public boolean isSelectTextField(){
        if(this.currentTool==Tool.textField)
            return true;
        else
            return false;
    }
    public boolean isSelectTextArea(){
        if(this.currentTool==Tool.textArea)
            return true;
        else
            return false;
    }
    public boolean isSelectButton(){
        if(this.currentTool==Tool.button)
            return true;
        else
            return false;
    }
    
    public boolean isSelectSelect(){
        if(this.currentTool==Tool.select)
            return true;
        else
            return false;
    }
    public boolean isSelectTextPane(){
        if(this.currentTool==Tool.textPane)
            return true;
        else
            return false;
    }
    
}
