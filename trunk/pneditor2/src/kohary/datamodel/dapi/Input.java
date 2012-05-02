/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.variables.Dvariable;

/**
 *
 * @author Godric
 */
public abstract class Input extends Element {
    
    private Dvariable variable=new Dvariable("undefined", "undefined");
    
    private boolean disabled=false;

    public Dvariable getVariable() {
        return variable;
    }

    public void setVariable(Dvariable variable) {
        this.variable = variable;
    }
    
       

    private DataType dataType = DataType.String;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isDisabled() {
        return disabled;
    }

    private void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public void changeDisabled(){
            disabled= !disabled;                    
    }
    
    
    protected boolean defaultValue = false;

    abstract public void draw(Graphics g, DrawingOptions drawingOptions);
}
