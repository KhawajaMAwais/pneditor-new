/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;



import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Godric
 */
public class VariablePanel extends JPanel  {
    private VariableEditor atbEditor;
    private List<Dvariable> variables;
    private BoxLayout boxLayOut;
    private List<VariableLine> attributeLines = new LinkedList<VariableLine>();

    public VariablePanel(VariableEditor atbEditor) {
       this.atbEditor=atbEditor;
       Color color = new Color(221,236,244);
       setBackground(color);


        variables = new LinkedList<Dvariable>();
        boxLayOut = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        setLayout(boxLayOut);
        //setBackground(Color.lightGray);
        setVisible(true);

    }


    public List<VariableLine> getAttributeLines() {
        return attributeLines;
    }

    public void setAttributeLines(List<VariableLine> attributeLines) {
        this.attributeLines = attributeLines;
    }

    public void setVariables(List<Dvariable> variables) {
        this.variables = variables;
        for(Dvariable attribute : variables)
        {
            VariableLine line = new VariableLine(attribute, this);
            attributeLines.add(line);
            add(line);        
            add(Box.createRigidArea(new Dimension(5,10)));
        }
    }

    public List<Dvariable> getVariables() {
        return variables;
    }
    
    

    public VariableEditor getAtbEditor() {
        return atbEditor;
    }
    
}
