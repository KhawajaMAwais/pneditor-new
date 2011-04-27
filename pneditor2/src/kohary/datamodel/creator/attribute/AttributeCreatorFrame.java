/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.home.ListEditorModel;
import kohary.datamodel.creator.util.ListModel;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author Godric
 */
public class AttributeCreatorFrame extends JFrame implements WindowListener{

    private AttributeEditor atbEditor;
    private JList list;
    private ListModel<Attribute> attributes;
    private DataModel dataModel;
    private ListEditorModel listModel;

    public AttributeCreatorFrame(DataModel dataModel, ListEditorModel listModel) {
        this.listModel = listModel;
        this.dataModel = dataModel;
        setSize(600, 300);
        listModel.getDataModelSelectPanel().getPetriNet();
        setTitle("Datamodel creator");
      
        atbEditor = new AttributeEditor(this);
        setLayout(new BorderLayout());

        add(atbEditor);
        setVisible(true);
        repaint();
        addWindowListener(this);

    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public AttributeEditor getAtbEditor() {
        return atbEditor;
    }

    public ListEditorModel getListModel() {
        return listModel;
    }

    public void windowClosing(WindowEvent e) {
        listModel.editableOn();
        getDataModel().setAttributes(atbEditor.getAtPanel().getAttributes());
    }
    
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}    
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
