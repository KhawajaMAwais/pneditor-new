/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;


import kohary.datamodel.creator.home.ListEditorModel;
import kohary.datamodel.creator.util.ListModel;
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
public class AttributePanel extends JPanel  {
    private AttributeEditor atbEditor;
    private ListModel<Attribute> attributes;
    private BoxLayout boxLayOut;
    private List<AttributeLine> attributeLines = new LinkedList<AttributeLine>();

    public AttributePanel(AttributeEditor atbEditor) {
       this.atbEditor=atbEditor;
       Color color = new Color(221,236,244);
       setBackground(color);


        attributes = new ListModel<Attribute>() {
            @Override
            public void addNew() {     
            }
            @Override
            public void addNew(ListEditorModel list) {                
            }
        };
        boxLayOut = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        setLayout(boxLayOut);
        //setBackground(Color.lightGray);
        setVisible(true);

    }

    public ListModel<Attribute> getAttributes() {
        return attributes;
    }

    public List<AttributeLine> getAttributeLines() {
        return attributeLines;
    }

    public void setAttributeLines(List<AttributeLine> attributeLines) {
        this.attributeLines = attributeLines;
    }

    public void setAttributes(ListModel<Attribute> attributes) {
        this.attributes = attributes;
        for(Attribute attribute : attributes)
        {
            AttributeLine line = new AttributeLine(attribute, this);
            attributeLines.add(line);
            add(line);
            for(String transition:attribute.getRights().keySet()) {
               AttributeRightsLine rigLine= new AttributeRightsLine(attribute, this);
               rigLine.getTransiotionBox().setSelectedItem(transition);
               rigLine.getRightsBox().setSelectedItem(attribute.getRights().get(transition));
               
                add(rigLine);
                line.getRightsLines().add(rigLine);
                
            }
            add(Box.createRigidArea(new Dimension(5,10)));
        }
    }

    public AttributeEditor getAtbEditor() {
        return atbEditor;
    }
    
}
